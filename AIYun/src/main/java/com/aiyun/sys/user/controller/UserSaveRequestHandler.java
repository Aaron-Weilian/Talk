/*
 * �������� 2004-9-2
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.aiyun.sys.user.controller;

import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.vo.CommonBean;
import com.aiyun.sys.user.bean.UserBean;

/**
 * @author Liun
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class UserSaveRequestHandler extends RequestHandlerSupport {

	public void processRequest(HttpServletRequest request) throws CommonException {
		UserBean userBean = new UserBean();
		CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
		String userID = parameter.getParameter("id");
		String sname = parameter.getParameter("sname");
		String loginID = parameter.getParameter("loginid");
		String dep = parameter.getParameter("dep");
		String duty = parameter.getParameter("duty");
		String psw1 = parameter.getParameter("psw1");

		CommonBean userInfo = new CommonBean();
		userInfo.addValue("id", userID);
		userInfo.addValue("sname", sname);
		userInfo.addValue("loginid", loginID);
		userInfo.addValue("dep", dep);
		userInfo.addValue("duty", duty);
		userInfo.addValue("spassword", psw1);

		if (loginID.equals("")) {
			CommonBean userList = userBean.getUserList(cbUser);
			userBean.getErrMsgBean().addCommonMessage("��½ID����Ϊ�գ�");
			request.setAttribute("_errmsgbean", userBean.getErrMsgBean());
			request.setAttribute("databean", userList);
			request.setAttribute("userbean", userInfo);
			request.setAttribute("id", userID);
			return;
		}
		userBean.saveUser(cbUser, userInfo);
		if (userBean.getErrMsgBean().getCommonMessage() != null) {
			CommonBean userList = userBean.getUserList(cbUser);
			request.setAttribute("_errmsgbean", userBean.getErrMsgBean());
			request.setAttribute("databean", userList);
			request.setAttribute("userbean", userInfo);
			request.setAttribute("id", userID);
		} else {
			request.setAttribute("forwardurl", "/yhqx");
		}
	}

}
