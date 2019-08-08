package com.aiyun.sys.user.controller;

import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.vo.CommonBean;
import com.aiyun.sys.user.bean.UserBean;

public class UserEditRequestHandler extends RequestHandlerSupport {

	public void processRequest(HttpServletRequest request) throws CommonException {

		String userID = parameter.getParameter("id");
		CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
		UserBean userBean = new UserBean();
		CommonBean userInfo = userBean.getUserInfo(cbUser, userID);
		if (userBean.getErrMsgBean().getCommonMessage() != null) {
			CommonBean userList = userBean.getUserList(cbUser);
			request.setAttribute("_errmsgbean", userBean.getErrMsgBean());
			request.setAttribute("databean", userList);
		} else {
			CommonBean userList = userBean.getUserList(cbUser);
			request.setAttribute("databean", userList);
			request.setAttribute("userbean", userInfo);
			request.setAttribute("id", userID);
		}
	}

}
