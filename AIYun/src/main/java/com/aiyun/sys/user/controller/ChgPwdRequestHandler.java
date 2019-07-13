package com.aiyun.sys.user.controller;

import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.vo.CommonBean;
import com.aiyun.sys.user.bean.PersonalInfoBean;

/**
 * @author Administrator
 *
 */
public class ChgPwdRequestHandler extends RequestHandlerSupport {

	public void processRequest(HttpServletRequest request) throws CommonException {
		PersonalInfoBean pibean = new PersonalInfoBean();
		CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
		
		
		CommonBean cbData = pibean.getPersonalInfo(cbUser);
		
		request.setAttribute("databean", cbData);
	}
}
