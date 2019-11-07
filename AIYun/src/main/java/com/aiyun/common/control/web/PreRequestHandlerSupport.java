package com.aiyun.common.control.web;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.tool.Log;

public abstract class PreRequestHandlerSupport implements PreRequestHandler {

	public PreRequestHandlerSupport() {
		context = null;
		Log.info(this, "initializing...");
	}

	public HashMap getParameters(HttpServletRequest httpservletrequest) {
		if (parameter == null)
			parameter = new RequestParameter(httpservletrequest);
		return parameter.getParameters();
	}

	public String getParameter(HttpServletRequest httpservletrequest, String s) {
		if (parameter == null)
			parameter = new RequestParameter(httpservletrequest);
		return parameter.getParameter(s);
	}

	public void doStart(HttpServletRequest httpservletrequest) {
		parameter = new RequestParameter(httpservletrequest);
	}

	public void doEnd(HttpServletRequest httpservletrequest) {
	}

	public void init(ServletContext servletcontext) {
		context = servletcontext;
	}

	public abstract void processRequest(HttpServletRequest httpservletrequest) throws CommonException;

	protected RequestParameter parameter;
	protected ServletContext context;
}