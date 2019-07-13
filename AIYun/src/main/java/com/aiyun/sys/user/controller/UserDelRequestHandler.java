/*
 * �������� 2004-9-3
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
public class UserDelRequestHandler extends RequestHandlerSupport {

	public void processRequest(HttpServletRequest request) throws CommonException {

		String userID = parameter.getParameter("id");
		CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
		UserBean userBean = new UserBean();
		userBean.delUser(cbUser, userID);
		if (userBean.getErrMsgBean().getCommonMessage() != null) {
			request.setAttribute("_errmsgbean", userBean.getErrMsgBean());
		}
		CommonBean userList = userBean.getUserList(cbUser);
		request.setAttribute("databean", userList);

	}

}
