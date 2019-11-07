package com.aiyun.sys.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.web.ExceptionHandler;
import com.aiyun.common.po.ErrorMessageBean;

/**
 * @author Aaron
 *
 */
public class ApplicationExceptionHandler implements ExceptionHandler {

	protected ServletContext servletcontext = null;

	public void init(ServletContext servletcontext) {
		this.servletcontext = servletcontext;

	}

	public void handleException(HttpServletRequest request) {
		ErrorMessageBean error = (ErrorMessageBean) request.getAttribute("_errmsgbean");
		String message = "û�в��񵽴�����Ϣ��";
		if (error != null && error.getCommonMessage() != null) {
			message = error.getCommonMessage();
		}
		Exception exception = (Exception)request.getAttribute("exception");
		if (exception != null) {
			message = exception.getMessage();
		}
		request.setAttribute("message", message);

	}

}
