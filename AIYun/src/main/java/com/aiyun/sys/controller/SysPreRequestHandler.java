/*
 * �������� 2004-7-22
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.aiyun.sys.controller;

import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.bo.ErrorMessageBean;
import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.PreRequestHandlerSupport;
import com.aiyun.common.vo.CommonBean;
import com.aiyun.sys.user.bean.UserBean;

/**
 * @author Aaron
 *
 */
public class SysPreRequestHandler extends PreRequestHandlerSupport {
	public void processRequest(HttpServletRequest request) throws CommonException {
		if (request.getSession() == null || request.getSession().getAttribute("_userinfo") == null) {
			ErrorMessageBean errBean = new ErrorMessageBean();
			errBean.addCommonMessage("Session disconnected, Please reconnect!");
			request.setAttribute("_errmsgbean", errBean);
			throw new CommonException("Session disconnected, Please reconnect!");
		}
		else {
			String obj = (String)request.getAttribute("currentobject");
			CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
			UserBean userbean = new UserBean();
			if (!userbean.bHasPower(cbUser, obj)) {
				ErrorMessageBean errBean = new ErrorMessageBean();
				errBean.addCommonMessage("Sorry, you are not allowed to access this page, please contact the administrator");
				request.setAttribute("_errmsgbean", errBean);
				throw new CommonException("Sorry, you are not allowed to access this page, please contact the administrator");
			}
		}
	}
}
