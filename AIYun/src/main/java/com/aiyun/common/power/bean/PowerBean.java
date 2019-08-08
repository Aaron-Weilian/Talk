package com.aiyun.common.power.bean;

import com.aiyun.common.bo.DBUtil;
import com.aiyun.common.bo.DataBaseObject;
import com.aiyun.common.util.Log;
import com.aiyun.common.vo.CommonBean;

public class PowerBean extends DBUtil {

	public CommonBean getUserPower(String userid) {
		try {
			StringBuffer sbSQL = new StringBuffer();
			sbSQL.append("SELECT u.id,p.module,p.name,p.url ");
			sbSQL.append("FROM u_user u, u_user_role ur, u_role r, u_role_permission rp, u_permission p ");
			sbSQL.append("WHERE	u.id = ur.uid AND ur.rid = rp.rid and rp.pid = p.id and ur.rid = r.id and r.status = 1 and u.status = 1 and p.status=1");
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
