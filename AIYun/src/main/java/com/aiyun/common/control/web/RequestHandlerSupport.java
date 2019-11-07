
package com.aiyun.common.control.web;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.tool.Log;

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

	public Object getSessionAttribute(HttpServletRequest request, String name){
	  //Assert.notNull(request, "Request must not be null");
	  HttpSession session =request.getSession(false);
	  return (session != null ?session.getAttribute(name) : null);
	}
	
    public void setSessionAttribute(HttpServletRequest request, String name, Object value) {
        if (value != null) {
            request.getSession().setAttribute(name, value);
        } else {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(name);
            }
        }
    }
	
	public abstract void processRequest(HttpServletRequest request) throws CommonException;

	protected ServletContext context;
	protected RequestParameter parameter;
}