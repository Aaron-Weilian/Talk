package com.aiyun.common.power;

import java.util.HashMap;
import java.util.Map;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.power.bean.PowerBean;
import com.aiyun.common.vo.CommonBean;

public class PowerSrv {

	private static PowerSrv instance = new PowerSrv();
	private Map map = null;

	public static PowerSrv getInstance() {
		if (instance == null) {
			instance = new PowerSrv();
		}
		return instance;
	}

	private PowerSrv() {
		map = new HashMap();
	}

	public boolean authorized(String userid, String moduleid, String busnodeid) {
		Map userPower = (Map) map.get(userid);
		userPower=null;
		if (userPower == null) {
			PowerBean bean = new PowerBean();
			CommonBean cbPower = bean.getUserPower(userid);
			userPower = new HashMap();
			for (int i = 0; i < cbPower.getRowNum(); i++) {
				String mid = cbPower.getCellStr(i, "moduleid");
				String bid = cbPower.getCellStr(i, "busnodeid");
				String key = "m" + mid + ((bid!=null && !bid.equals(""))?"-b" + bid:"");
				userPower.put(key, cbPower.getCellStr(i, "lvalue"));
			}
			map.put(userid, userPower);
		}
		String key = "m" + moduleid + ((busnodeid!=null && !busnodeid.equals(""))?"-b" + busnodeid:"");
		if ("1".equals(userPower.get(key))) {
			return true;
		} else if ("0".equals(userPower.get(key))) {
			return false;
		} else {
			throw new CommonException("Ȩ�޲�����");
		}
	}

	public void removeCache(String userid) {
		map.remove(userid);
	}
}
