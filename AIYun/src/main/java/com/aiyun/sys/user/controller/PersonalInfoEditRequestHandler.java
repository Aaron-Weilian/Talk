package com.aiyun.sys.user.controller;

import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.vo.CommonBean;
import com.aiyun.sys.user.bean.PersonalInfoBean;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class PersonalInfoEditRequestHandler extends RequestHandlerSupport {

	/* ���� Javadoc��
	 * @see com.aiyun.common.control.web.RequestHandler#processRequest(javax.servlet.http.HttpServletRequest)
	 */
	public void processRequest(HttpServletRequest request) throws CommonException {
		PersonalInfoBean pibean = new PersonalInfoBean();
		CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
		
		
		CommonBean cbData = pibean.getPersonalInfo(cbUser);
		
		request.setAttribute("databean", cbData);
	}
}
