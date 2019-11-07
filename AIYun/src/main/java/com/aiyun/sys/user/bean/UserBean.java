package com.aiyun.sys.user.bean;

import java.sql.SQLException;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.manager.CacheManager;
import com.aiyun.common.permission.PermissionServer;
import com.aiyun.common.po.DBUtil;
import com.aiyun.common.po.DataBaseObject;
import com.aiyun.common.po.IBusnessObject;
import com.aiyun.common.tool.Function;
import com.aiyun.common.tool.Log;
import com.aiyun.common.tool.Oid;
import com.aiyun.common.vo.CommonBean;

/**
 */
public class UserBean extends DBUtil implements IBusnessObject {

	public static final String OBJECT_NAME = "users";

	public CommonBean login(String username, String password) {
	    StringBuffer bsSQL = new StringBuffer();
        bsSQL.append("SELECT U.* \n")
             .append("FROM U_USER U \n")
             .append("where 1=1 \n")
             .append("  and U.USERNAME='").append(username).append("' \n")
             .append("  and U.PSWD='").append(password).append("' \n")
             .append("  and U.STATUS=1 \n");
        String strSQL = bsSQL.toString();
        
        try {
            DataBaseObject dbo = getDataBaseObject();
            CommonBean op = dbo.getData(strSQL);
            return op;
        } catch (Exception e) {
            Log.error(this, "User login fail," + e.getMessage());
            getErrMsgBean().addCommonMessage("\"User login fail," + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            rollback();
        }
	}
	
	public CommonBean getPermission() {
		StringBuffer bsSQL = new StringBuffer();
		bsSQL.append("SELECT M.ID AS M_ID,M.NAME AS M_SNAME,URL AS B_POS \n");
		bsSQL.append("FROM U_PERMISSION M \n");
		String strSQL = bsSQL.toString();
		
		try {
			DataBaseObject dbo = getDataBaseObject();
			CommonBean op = dbo.getData(strSQL);
			return op;
		} catch (Exception e) {
			Log.error(this, "get user CB object fail," + e.getMessage());
			getErrMsgBean().addCommonMessage("get user CB object fail," + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			rollback();
		}
	}
	
	
	public CommonBean getOperationPower(CommonBean cbUser, String userid) {
		StringBuffer bsSQL = new StringBuffer();
		bsSQL.append("SELECT M.ID AS M_ID,M.NAME AS M_SNAME,URL AS B_POS \n");
		bsSQL.append("FROM U_PERMISSION M \n");
		bsSQL.append("LEFT JOIN SYS_BUSNODE B ON B.MODULEID=M.ID AND B.LAUTH=1 \n");
		bsSQL.append("LEFT JOIN (SELECT DISTINCT BUSNODEID FROM USER_AUTH WHERE USERID='"+userid+"') AB ON AB.BUSNODEID=B.ID \n");
		bsSQL.append("LEFT JOIN (SELECT DISTINCT MODULEID FROM USER_AUTH WHERE USERID='"+userid+"') AM ON AM.MODULEID=M.ID \n");
		bsSQL.append("WHERE M.LAUTH=1 AND M.LUSE=1 \n");
		bsSQL.append("ORDER BY M_POS,B_POS");
		String strSQL = bsSQL.toString();
		
		try {
			DataBaseObject dbo = getDataBaseObject();
			CommonBean op = dbo.getData(strSQL);
			return op;
		} catch (Exception e) {
			Log.error(this, "ȡ���û�Ȩ�޷�������" + e.getMessage());
			getErrMsgBean().addCommonMessage("ȡ���û�Ȩ�޷�������" + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			rollback();
		}
	}

	public boolean saveOperationPower(CommonBean cbUser, String userid, CommonBean cbAuth) {
		try {
			//ɾ��ҳͷ�Ļ���
			String key = "sys-other-headdefination_" + userid;
			CacheManager.clearCache(key);
			
			DataBaseObject dbo = getDataBaseObject();
			String sql = null;
			sql = "DELETE FROM USER_AUTH WHERE USERID='" + userid + "'";
			cbAuth.setBeanName("USER_AUTH");
			cbAuth.setAttribute("insert");
			boolean bFlag = dbo.execute(sql) && dbo.execute(cbAuth);
			
			//��������ҳ
			sql = "SELECT * FROM SYS_MODULE WHERE ID IN (SELECT MODULEID FROM USER_AUTH WHERE USERID = '"+userid+"') AND LHOME=1";
			CommonBean cbTemp = dbo.getData(sql);
			if (cbTemp!=null && cbTemp.getRowNum()>0) {
				sql = "INSERT INTO USER_AUTH (USERID,MODULEID,BUSNODEID) VALUES('"+userid+"','01','')";
				dbo.execute(sql);
			}
			//ÿһ���˶���ʹ�ø�����Ϣ
			sql = "INSERT INTO USER_AUTH (USERID,MODULEID,BUSNODEID) VALUES('"+userid+"','13','38')";
			dbo.execute(sql);
			
			if (bFlag) {
				commit();
				PermissionServer.getInstance().removeCache(userid);
			} else {
				throw new CommonException("δ֪����");
			}
			return bFlag;
		} catch (Exception e) {
			rollback();
			getErrMsgBean().addCommonMessage("�����û�Ȩ�޷�������" + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public CommonBean getUserList(CommonBean cbUser) {
		try {
			String strSql = "select u.username,r.name,u.status from u_user u, u_role r , u_user_role ur where u.id = ur.uid and r.id = ur.rid and u.orgid='"+cbUser.getValue("orgid")+"' ";
			DataBaseObject dbo = getDataBaseObject();
			CommonBean cb = dbo.getData(strSql);
			cb = Function.formatBean(cb);
//			String strSql1 = "select id,sname from dept";
//			CommonBean cbDept = dbo.getData(strSql1);
//			String[][] arrDept = Function.bean2arr(cbDept);
//			cb.setColListValue("dep",arrDept);

			return cb;
		} catch (Exception e) {
			Log.error(this, "getUserList:" + e.getMessage());
			getErrMsgBean().addCommonMessage("getUserList:" + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			rollback();
		}
	}

	public boolean saveUser(CommonBean cbUser, CommonBean userInfo) {
		try {
			
			boolean bFlag = true;
			userInfo.setBeanName("users");
			String userID = userInfo.getValue("id");
			if (userID.equals("")) {
				if (isDuplicate("users", "loginid", null, userInfo.getValue("loginid"), "new")) {
					throw new CommonException("saveUser");
				}
				userInfo.setAttribute("insert");
				userID = Oid.getOid();
				userInfo.setCellObj(0, "id", userID);
				userInfo.setCellObj(0, "spassword", Function.Encmd5(userInfo.getCellStr(0,"spassword")));
			} else {
				if (isDuplicate("users", "loginid", userID, userInfo.getValue("loginid"), "edit")) {
					throw new CommonException("saveUser");
				}
				userInfo.setAttribute("update");
				userInfo.removeColumn("spassword");
			}
			DataBaseObject dbo = getDataBaseObject();
			bFlag = bFlag && dbo.execute(userInfo);
			//ÿһ���˶���ʹ�ø�����Ϣ
			String sql = "INSERT INTO USER_AUTH (USERID,MODULEID,BUSNODEID) VALUES('"+userID+"','13','38')";
			bFlag = bFlag && dbo.execute(sql);
			
			if (bFlag) {
				commit();
			} else {
				throw new CommonException("saveUser");
			}
			return bFlag;
		} catch (Exception e) {
			rollback();
			getErrMsgBean().addCommonMessage("saveUser:" + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public CommonBean getUserInfo(String userID) {
		try {
			String strSql = "SELECT ID,sName,LoginID,Dep,Duty FROM Users WHERE ID='" + userID + "' ";
			DataBaseObject dbo = getDataBaseObject();
			CommonBean cb = dbo.getData(strSql);
			cb = Function.formatBean(cb);
			return cb;
		} catch (Exception e) {
			Log.error(this, "getUserInfo:" + e.getMessage());
			getErrMsgBean().addCommonMessage("getUserInfo:" + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			rollback();
		}
	}

	public boolean delUser(CommonBean cbUser, String userID) {
		try {
			CommonBean cbUserDel = new CommonBean();
			cbUserDel.addValue("ID", userID);
			cbUserDel.setBeanName("Users");
			cbUserDel.setAttribute("delete");
			DataBaseObject dbo = getDataBaseObject();
			boolean bFlag = true;
			bFlag = bFlag && dbo.execute(cbUserDel);
			String strSql = "DELETE FROM USER_AUTH WHERE UserID='" + userID + "' ";
			bFlag = bFlag && dbo.execute(strSql);
			if (bFlag) {
				commit();
			} else {
				throw new CommonException("delUser");
			}
			return bFlag;
		} catch (SQLException e) {
			rollback();
			getErrMsgBean().addCommonMessage("delUser:" + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean bHasPower (CommonBean userbean, String obj) {
		try {
			DataBaseObject dbo = getDataBaseObject();
			StringBuffer sbSQL = new StringBuffer();
            sbSQL.append("SELECT p.module ");
            sbSQL.append("FROM u_user u, u_user_role ur, u_role r, u_role_permission rp, u_permission p ");
            sbSQL.append("WHERE u.id = ur.uid AND ur.rid = rp.rid and rp.pid = p.id and ur.rid = r.id and r.status = 1 and u.status = 1 ");
            sbSQL.append(" AND u.id = '").append(userbean.getValue("id")).append("'");
            sbSQL.append(" AND p.buscode = '").append(obj.toString()).append("'");
			CommonBean cbPower = dbo.getData(sbSQL.toString());
			if (cbPower.getRowNum() == 0) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			rollback();
		}
	}
	
	public static void main (String[] args) {
		UserBean ub = new UserBean();
		CommonBean cb = ub.getPermission();
		cb.printData();
		
	}
}
