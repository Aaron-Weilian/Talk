package com.aiyun.common.bo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.aiyun.common.util.Log;
import com.aiyun.common.vo.ColumnBean;
import com.aiyun.common.vo.CommonBean;

public abstract class BusinessObject {

	private ErrorMessageBean errMsgBean = new ErrorMessageBean();
	private static Context context = null;
	private static DataSource dataSource = null;
	 
	private DataBaseObject dbo = null;
	private Connection con = null;
	//protected final String DATA_SOURCE = "jdbc/bmc";
	protected final String DATA_SOURCE = "java:comp/env/jdbc/bmc";

	public void commit() {

		if (con != null) {
			try {
				con.commit();
			} catch (SQLException e) {
				Log.error(this, "�ύ����������" + e.getMessage());
				errMsgBean.addCommonMessage("�ύ����������" + e.getMessage());
			} finally {
				try {
					con.close();
					con = null;
					dbo = null;
				} catch (SQLException se) {
					Log.error(this, "���ܷ������ӣ�" + se.getMessage());
				}
			}
		}
	}
	
   public void multicommit() {

	   if (con != null) {
		   try {
			   con.commit();
		   } catch (SQLException e) {
			   Log.error(this, "�ύ����������" + e.getMessage());
			   errMsgBean.addCommonMessage("�ύ����������" + e.getMessage());
		   }
	   }
   }
	
	public Connection getConnection() throws SQLException {

		if (con != null && !con.isClosed()) {
			return con;
		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		con = DriverManager.getConnection("jdbc:oracle:thin:@10.155.3.65:1521:oradb", "Bmcnew", "wj1209");
		con.setAutoCommit(false);
		return con;
	}
	
	
	public void release() throws SQLException {
		rollback();
	}

	protected void rollback() {
		if (con != null) {
			try {
				con.rollback();

			} catch (SQLException e) {
				Log.error(this, "�ع�����������" + e.getMessage());
				errMsgBean.addCommonMessage("�ύ����������" + e.getMessage());
			} finally {
				try {
					con.close();
					con = null;
					dbo = null;
				} catch (SQLException se) {
					Log.error(this, "���ܷ������ӣ�" + se.getMessage());
				}
			}
		}
	}
	
   public void multirollback() {

	   if (con != null) {
		   try {
			   con.rollback();
		   } catch (SQLException e) {
			   Log.error(this, "�ع�����������" + e.getMessage());
			   errMsgBean.addCommonMessage("�ع�����������" + e.getMessage());
		   }
	   }
   }

	public void setConnection(Connection con) {
		this.con = con;
	}
	public BusinessObject() {
		super();
	}

	public ErrorMessageBean getErrMsgBean() {
		return errMsgBean;
	}

	public void setErrMsgBean(ErrorMessageBean newErrMsgBean) {
		errMsgBean = newErrMsgBean;
	}

	public DataBaseObject getDataBaseObject() throws SQLException {
		if (dbo == null) {
			dbo = new DataBaseObject(getConnection());
		}
		return dbo;
	}

	public CommonBean getYWList(String[] ywType) {
		try {
			String sql = "SELECT rownum,YWLIST.ID,to_char(YWLIST.SPDATE,'yyyy-mm-dd') AS spdate,to_char(YWLIST.SBDATE,'yyyy-mm-dd') AS sbdate, BGD_ZC_MX.MXDNO, CL_STATUS.F_START||CL_STATUS.sName||CL_STATUS.F_END AS STATUSNAME, CL_YWTYPE.sName AS TYPEIDNAME, CL_YWTYPE.YWLINK || YWLIST.ID AS YWLINK, CL_YWTYPE.TYWLINK || YWLIST.ID AS TYWLINK "
						+"FROM YWLIST "
						+"LEFT JOIN BGD_ZC_MX ON BGD_ZC_MX.YWID=YWLIST.ID "
						+"LEFT JOIN CL_STATUS ON CL_STATUS.ID=YWLIST.STATUS "
						+"LEFT JOIN CL_YWTYPE ON CL_YWTYPE.ID=YWLIST.TYPEID "
						+"WHERE YWLIST.STATUS<>3 ";
						
			if (ywType != null) {
				sql += "AND CL_YWTYPE.ID IN('0',";
				for (int i = 0; i < ywType.length; i++) {
					sql += "'" + ywType[i] + "',";
				}
				sql = sql.substring(0, sql.length() - 1) + ") ";
			}
			sql = sql + " ORDER BY YWLIST.ADDDATE DESC ";
			DataBaseObject dbo = getDataBaseObject();
			CommonBean cb = dbo.getData(sql);

			return cb;
		} catch (Exception e) {
			Log.error(this, "ȡ��ҵ����Ϣ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("ȡ��ҵ����Ϣ��������" + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			rollback();
		}
	}

	public CommonBean getJKSBList(String[] ywType) {
		try {
			String sql = "SELECT rownum,YWLIST.ID,to_char(YWLIST.SPDATE,'yyyy-mm-dd') AS spdate,to_char(YWLIST.SBDATE,'yyyy-mm-dd') AS sbdate, BGD_ZC_MX.MXDNO,CL_STATUS.F_START||CL_STATUS.sName||CL_STATUS.F_END AS STATUSNAME, CL_YWTYPE.sName AS TYPEIDNAME, CL_YWTYPE.YWLINK || YWLIST.ID AS YWLINK, CL_YWTYPE.TYWLINK || YWLIST.ID AS TYWLINK "
						+"FROM YWLIST "
						+"LEFT JOIN BGD_ZC_MX ON BGD_ZC_MX.YWID=YWLIST.ID "
						+"LEFT JOIN CL_STATUS ON CL_STATUS.ID=YWLIST.STATUS "
						+"LEFT JOIN CL_YWTYPE ON CL_YWTYPE.ID=YWLIST.TYPEID "
						+"WHERE YWLIST.STATUS<>3 ";
						
			if (ywType != null) {
				sql += "AND CL_YWTYPE.ID IN('0',";
				for (int i = 0; i < ywType.length; i++) {
					sql += "'" + ywType[i] + "',";
				}
				sql = sql.substring(0, sql.length() - 1) + ") ";
			}
			sql = sql + " ORDER BY YWLIST.ADDDATE DESC ";
			DataBaseObject dbo = getDataBaseObject();
			CommonBean cb = dbo.getData(sql);

			return cb;
		} catch (Exception e) {
			Log.error(this, "ȡ��ҵ����Ϣ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("ȡ��ҵ����Ϣ��������" + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			rollback();
		}
	}

	public boolean isDuplicate(String table, String field, String id, String value, String type) throws SQLException {

		String strSql = "SELECT Count(*) AS num FROM " + table + " WHERE " + field + "='" + value + "' ";
		if (type.equalsIgnoreCase("edit")) {
			strSql += "AND ID<>'" + id + "' ";
		}
		DataBaseObject dbo = getDataBaseObject();
		CommonBean cb = dbo.getData(strSql);
		if (cb.getValue("num").equals("0")) {
			return false;
		} else {
			return true;
		}

	}
	
	public void dumpBANO(CommonBean cbData, String sfield, String soper) throws Exception {
		CommonBean cbBase = (CommonBean)cbData.getCellObj(0,"fieldbean");
		if (cbBase.getColIndex(sfield)==-1) return;
		
		CommonBean cbSys = (CommonBean)cbData.getCellObj(cbData.getRowNum()-1,"fieldbean");
		DataBaseObject dbo = getDataBaseObject();
		String sql = "SELECT * FROM BGD_ZC_MX WHERE ROWNUM=1 AND MXDNO='"+(cbBase.getValue(sfield)==null?"":cbBase.getValue(sfield).toUpperCase())+"'";
		if (soper.equalsIgnoreCase("edit"))
			sql = "SELECT * FROM BGD_ZC_MX WHERE ROWNUM=1 AND YWID<>'"+cbSys.getValue("id")+"' AND MXDNO='"+(cbBase.getValue(sfield)==null?"":cbBase.getValue(sfield).toUpperCase())+"'";
		CommonBean cb = dbo.getData(sql);
		if (cb!=null && cb.getRowNum()>0) {
			ColumnBean columnbean = cbBase.getColumnBean(sfield);
			columnbean.setErrMsg("��ϸ���Ų����ظ�,����������");
			cbData.setAttribute("false");
		}
		return;
	}
}
