package com.aiyun.sys.user.controller;

import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.vo.CommonBean;
import com.aiyun.sys.user.bean.PersonalInfoBean;
import com.aiyun.sys.user.bean.UserBean;

public class UserInfoRequestHandler extends RequestHandlerSupport {

	public void processRequest(HttpServletRequest request) throws CommonException {
	    
	    String userid = parameter.getParameter("userid");
	    
	    if(userid!=null) {
	        UserBean userBean = new UserBean();
	        CommonBean ubData = userBean.getUserInfo(userid);
	        if (userBean.getErrMsgBean().getCommonMessage() != null) {
	            request.setAttribute("_errmsgbean", userBean.getErrMsgBean());
	            request.setAttribute("databean", ubData);
	        } else {
	            request.setAttribute("databean", ubData);
	        }
	    }
	}
}
