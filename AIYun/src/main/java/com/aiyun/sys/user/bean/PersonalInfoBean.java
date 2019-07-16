package com.aiyun.sys.user.bean;

import com.aiyun.common.bo.DBUtil;
import com.aiyun.common.bo.DataBaseObject;
import com.aiyun.common.tool.Function;
import com.aiyun.common.util.Log;
import com.aiyun.common.vo.CommonBean;

/**
 */
public class PersonalInfoBean extends DBUtil {
	public boolean saveUser(CommonBean cbUser) {
		try {
			cbUser.setBeanName("USERS");
			cbUser.setAttribute("update");
			
			boolean bFlag = true;
			DataBaseObject dbo = getDataBaseObject();
			bFlag = bFlag && dbo.execute(cbUser);
		
			if (bFlag) 
				commit();
			else
				throw new Exception("δ֪�Ĵ���");
			
			return bFlag;
		} catch (Exception e) {
			rollback();
			Log.error(this, "���������Ϣ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("���������Ϣ��������" + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	//�鲢�ϼ�
	public CommonBean getPersonalInfo(CommonBean cbUser) {
		try {
			DataBaseObject dbo = getDataBaseObject();
			boolean bFlag = true;
			
			//����ɾ��ԭ�еĹ鲢����
			String sql = "SELECT USERS.id,USERS.sname,USERS.loginid,USERS.duty,dept.sname as dep FROM USERS LEFT JOIN DEPT ON DEPT.ID=USERS.DEP WHERE USERS.ID = '"+cbUser.getValue("id")+"'";
			CommonBean cb = dbo.getData(sql);
			release();
			return cb;
		} catch (Exception e) {
			rollback();
			Log.error(this, "ȡ�û���Ϣʱ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("ȡ�û���Ϣʱ��������" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	//�༭�����ϼ�
	public boolean savePwd(CommonBean cbUser) {
		try {
			//���ñ���ʱ�ı�Ҫ��Ϣ
			cbUser.setBeanName("USERS");
			cbUser.setAttribute("update");
			
			boolean bFlag = true;
			DataBaseObject dbo = getDataBaseObject();
			
			//У��
			String sql = "SELECT spassword FROM USERS WHERE ID = '"+cbUser.getValue("id")+"'";
			CommonBean cbTemp = dbo.getData(sql);
			if (cbTemp.getValue("spassword")!=null && !cbTemp.getValue("spassword").equals(Function.Encmd5(cbUser.getValue("soldpassword")))) {
				getErrMsgBean().addCommonMessage("�������������,��û�и��������Ȩ��");
				bFlag = false;
			}
			if (cbUser.getValue("spassword")!=null && cbUser.getValue("scfmpassword")!=null && !cbUser.getValue("spassword").equals(cbUser.getValue("scfmpassword"))) {
				getErrMsgBean().addCommonMessage("������������벻ƥ��,����������");
				bFlag = false;
			}
			if (!bFlag) return false;
			
			cbUser.removeColumn("soldpassword");
			cbUser.removeColumn("scfmpassword");
			cbUser.setCellObj(0, "spassword", Function.Encmd5(cbUser.getCellStr(0,"spassword")));
			
			bFlag = bFlag && dbo.execute(cbUser);
		
			if (bFlag) 
				commit();
			else
				throw new Exception("δ֪�Ĵ���");
			
			return bFlag;
		} catch (Exception e) {
			rollback();
			Log.error(this, "���������Ϣ��������" + e.getMessage());
			getErrMsgBean().addCommonMessage("���������Ϣ��������" + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * @cbFieldBox �½��༭ʱУ����bean,����Ϣ����Ϣ
	 * @cbData ���뱣��Ļ�������bean
	 */
	public boolean validData(CommonBean cbFieldBox, CommonBean cbData, String oper) throws Exception {
		boolean bValid = true;
		
		return bValid;
	}
}
