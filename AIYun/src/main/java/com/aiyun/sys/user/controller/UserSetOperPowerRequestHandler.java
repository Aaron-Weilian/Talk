/*
 * �������� 2004-8-4
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
public class UserSetOperPowerRequestHandler extends RequestHandlerSupport {

	public void processRequest(HttpServletRequest request) throws CommonException {

		String userid = parameter.getParameter("id");
		CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
		UserBean bean = new UserBean();
		CommonBean op = bean.getOperationPower(cbUser, userid);
		if (bean.getErrMsgBean() != null && bean.getErrMsgBean().getCommonMessage() != null) {
			request.setAttribute("_errmsgbean", bean.getErrMsgBean());
			request.setAttribute("forwardurl", "/applicationException");
		} else {
			request.setAttribute("databean", op);
			request.setAttribute("userid", userid);
		}
	}

}
