package com.aiyun.common.power.bean;

import com.aiyun.common.bo.BusinessObject;
import com.aiyun.common.bo.DataBaseObject;
import com.aiyun.common.util.Log;
import com.aiyun.common.vo.CommonBean;

public class PowerBean extends BusinessObject {

	public CommonBean getUserPower(String userid) {
		try {
			StringBuffer sbSQL = new StringBuffer();
			sbSQL.append("SELECT SYS_MODULE.ID AS MODULEID,SYS_BUSNODE.ID AS BUSNODEID,'1' AS LVALUE ");
			sbSQL.append("FROM SYS_MODULE ");
			sbSQL.append("LEFT JOIN SYS_BUSNODE ON SYS_MODULE.ID=SYS_BUSNODE.MODULEID ");
			sbSQL.append("LEFT JOIN USER_AUTH ON USER_AUTH.BUSNODEID=SYS_BUSNODE.ID AND USER_AUTH.MODULEID=SYS_MODULE.ID AND USER_AUTH.USERID='"+userid+"' ");
			sbSQL.append("ORDER BY SYS_MODULE.ID");
			DataBaseObject dbo = getDataBaseObject();
			CommonBean cb = dbo.getData(sbSQL.toString());
			
			return cb;
		} catch (Exception e) {
			Log.error(this, "ȡ���û�Ȩ�޷�������" + e.getMessage());
			getErrMsgBean().addCommonMessage("ȡ���û�Ȩ�޷�������" + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			rollback();
		}
	}
}
