// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-07-13 9:37:49
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RequestHandlerSupport.java

package com.aiyun.common.control.web;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.util.Log;

// Referenced classes of package mycrm.pub.control.web:
//            FlowHandler, RequestParameter

public abstract class RequestHandlerSupport implements RequestHandler {

	public RequestHandlerSupport() {
		context = null;
		Log.info(this, "initializing...");
	}

	public void init(ServletContext servletcontext) {
		context = servletcontext;
	}

	public void doStart(HttpServletRequest request) {
		parameter = new RequestParameter(request);
	}

	public void doEnd(HttpServletRequest request) {
	}

	public HashMap getParameters(HttpServletRequest request) {
		if (parameter == null)
			parameter = new RequestParameter(request);
		return parameter.getParameters();
	}

	public String getParameter(HttpServletRequest request, String s) {
		if (parameter == null)
			parameter = new RequestParameter(request);
		return parameter.getParameter(s);
	}

	public abstract void processRequest(HttpServletRequest request) throws CommonException;

	protected ServletContext context;
	protected RequestParameter parameter;
}