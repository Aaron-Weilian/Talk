package com.aiyun.sys.user.controller;

import javax.servlet.http.HttpServletRequest;


import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.vo.CommonBean;
import com.aiyun.sys.user.bean.PersonalInfoBean;

public class ChgPwdSaveRequestHandler extends RequestHandlerSupport {
	public void processRequest(HttpServletRequest request) throws CommonException {
		CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
		String userid = parameter.getParameter("id");
		String soldpwd = parameter.getParameter("soldpassword");
		String spasspwd = parameter.getParameter("spassword");
		String scfmpwd = parameter.getParameter("scfmpassword");
		
		CommonBean userBean = new CommonBean();
		userBean.addValue("id", userid);
		userBean.addValue("soldpassword", soldpwd);
		userBean.addValue("spassword", spasspwd);
		userBean.addValue("scfmpassword", scfmpwd);
		
		PersonalInfoBean pibean = new PersonalInfoBean();
		if (pibean.savePwd(userBean)) {
			request.setAttribute("forwardurl", "/grxx");
		}
		else {
			request.setAttribute("_errmsgbean", pibean.getErrMsgBean());
			request.setAttribute("databean", userBean);
		}
	}
}