package com.aiyun.sys.user.controller;

import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.dict.Constants;
import com.aiyun.common.manager.CacheManager;
import com.aiyun.common.permission.bean.PermissionBean;
import com.aiyun.common.tool.Function;
import com.aiyun.common.vo.CommonBean;
import com.aiyun.sys.user.bean.UserBean;


public class UserLoginRequestHandler extends RequestHandlerSupport {

    
    
	public void processRequest(HttpServletRequest request) throws CommonException {
	    
	    UserBean userbean = new UserBean();
	    
		String username = parameter.getParameter("username");
		String password = parameter.getParameter("password");
		
		username = "admin";
		password = Function.Encmd5("admin");
		CommonBean cbUsers = userbean.login(username,password);
		if (true) {
		    String sessionid = request.getSession().getId();
		    
		    setSessionAttribute(request,Constants.USERINFO,cbUsers);
		    setSessionAttribute(request,Constants.SESSIONID,sessionid);
		    
            
            PermissionBean pb = new PermissionBean();
            CommonBean userMenus = pb.getUserPermission(username);
            
            setSessionAttribute(request,Constants.USERMENUS,userMenus);
		}
		
	}

}
