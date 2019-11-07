package com.aiyun.common.permission;

import java.util.HashMap;
import java.util.Map;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.permission.bean.PermissionBean;
import com.aiyun.common.vo.CommonBean;

public class PermissionServer {

	private static PermissionServer instance = new PermissionServer();
	private Map map = null;

	public static PermissionServer getInstance() {
		if (instance == null) {
			instance = new PermissionServer();
		}
		return instance;
	}

	private PermissionServer() {
		map = new HashMap();
	}

	public boolean authorized(String userid, String moduleid, String busnodeid) {
		Map userPower = (Map) map.get(userid);
		userPower=null;
		if (userPower == null) {
			PermissionBean bean = new PermissionBean();
			CommonBean cbPower = bean.getUserPermission(userid);
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
