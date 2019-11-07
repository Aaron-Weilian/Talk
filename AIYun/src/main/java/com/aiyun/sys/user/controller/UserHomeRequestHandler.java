package com.aiyun.sys.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.dict.Constants;
import com.aiyun.common.taglib.model.TableModel;
import com.aiyun.common.vo.CommonBean;
import com.aiyun.sys.user.bean.UserBean;

public class UserHomeRequestHandler extends RequestHandlerSupport {

    private static String isAdmin = "1";
    
	public void processRequest(HttpServletRequest request) throws CommonException {

		UserBean userBean = new UserBean();
		CommonBean cbUser = (CommonBean) getSessionAttribute(request,Constants.USERINFO);
		
		CommonBean userList = cbUser;
		
		//判断用户是不是管理员账户
		if(isAdmin.equals(cbUser.getValue("isAdmin"))) {
		    userList = userBean.getUserList(cbUser);
		}
		
		if (userList == null) {
			request.setAttribute("_errmsgbean", userBean.getErrMsgBean());
			request.setAttribute("forwardurl", "/applicationException");
		} else {
		    TableModel tb = new TableModel(userList);
	        tb.setSName(userList.getColNames());
	        tb.setChName(new String[] { "用户名", "角色","状态"});
	        tb.setTableClass("aiyun-table");
	        for(int i = 0; i < userList.getRowNum(); i++) {
	            Map trClass = new HashMap();
	            trClass.put(i, "gradeA");
	            tb.setRowClass(trClass);
	            for(int j = 0; j<userList.getColumnNum(); j++) {
	                Map tdClass = new HashMap();
	                tdClass.put(i, "center");
	                tb.setColClass(tdClass);
	            }
	        }
		    
			setSessionAttribute(request,"userList",tb);
		}
	}

}
