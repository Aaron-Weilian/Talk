package com.aiyun.sys.user.controller;

import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.vo.CommonBean;
import com.aiyun.sys.user.bean.PersonalInfoBean;

public class PersonalInfoEditSaveRequestHandler extends RequestHandlerSupport {
	public void processRequest(HttpServletRequest request) throws CommonException {
		CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
		String userID = parameter.getParameter("id");
		String sname = parameter.getParameter("sname");
		String dep = parameter.getParameter("dep");
		String duty = parameter.getParameter("duty");

		CommonBean userBean = new CommonBean();
		userBean.addValue("id", userID);
		userBean.addValue("sname", sname);
		userBean.addValue("dep", dep);
		userBean.addValue("duty", duty);

		PersonalInfoBean pibean = new PersonalInfoBean();
		if (pibean.saveUser(userBean)) {
			request.setAttribute("forwardurl", "/grxx");
		}
	}
}