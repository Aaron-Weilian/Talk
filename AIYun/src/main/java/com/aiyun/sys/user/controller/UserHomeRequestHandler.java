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
public class UserHomeRequestHandler extends RequestHandlerSupport {

	public void processRequest(HttpServletRequest request) throws CommonException {

		UserBean userBean = new UserBean();
		CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
		CommonBean userList = userBean.getUserList(cbUser);
		if (userList == null) {
			request.setAttribute("_errmsgbean", userBean.getErrMsgBean());
			request.setAttribute("forwardurl", "/applicationException");
		} else {
			request.setAttribute("databean", userList);
		}
	}

}
