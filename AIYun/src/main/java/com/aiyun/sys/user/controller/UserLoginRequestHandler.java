package com.aiyun.sys.user.controller;

import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.power.bean.PowerBean;
import com.aiyun.common.vo.CommonBean;


public class UserLoginRequestHandler extends RequestHandlerSupport {

	public void processRequest(HttpServletRequest request) throws CommonException {
		String userID = parameter.getParameter("username");
		String userPwd = parameter.getParameter("password");
		
		
		PowerBean pb = new PowerBean();
		CommonBean userMenus = pb.getUserPower(userID);
		
		request.setAttribute("userMenus", userMenus);
		
		//
		
	}

}
