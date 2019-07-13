// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-07-13 9:38:26
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RequestToEventTranslator.java

package com.aiyun.common.control.web;

import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.exception.SystemException;
import com.aiyun.common.util.Log;

// Referenced classes of package mycrm.pub.control.web:
//            ScreenFlowManager, URLMapping, PreRequestHandler, RequestHandler

public class RequestToEventTranslator implements Serializable {

	public RequestToEventTranslator() {
	}

	public void processRequest(ServletContext servletcontext, HttpServletRequest httpservletrequest)
		throws SystemException {
		Class class1 = (Class) servletcontext.getAttribute("DOMAIN_CLASS");
		ClassLoader classloader = class1.getClassLoader();
		String s = (String) httpservletrequest.getAttribute("pathInfo");
		ScreenFlowManager screenflowmanager =
			(ScreenFlowManager) servletcontext.getAttribute(ScreenFlowManager.KEY_NAME);
		URLMapping urlmapping = screenflowmanager.getURLMapping((String) httpservletrequest.getAttribute("pathInfo"));
		if (urlmapping == null) {
			Log.info(
				this,
				String.valueOf(
					String.valueOf(
						(new StringBuffer("no url mapping for PathInfo(")).append(s).append(
							"). use default PathInfo(/default)"))));
			urlmapping = screenflowmanager.getURLMapping("/default");
		}
		Log.info(this, "pathInfo is ".concat(String.valueOf(String.valueOf(s))));
		if (urlmapping == null) {
			throw new CommonException(
				String.valueOf(
					String.valueOf(
						(new StringBuffer("no url mapping entry for [")).append(s).append(
							"] in requestmappings.xml"))));
		}
		httpservletrequest.setAttribute("currentobject", urlmapping.getObject());
		if (urlmapping.usePreProcessHandler()) {
			String s1 = urlmapping.getPreProcessHandler();
			try {
				PreRequestHandler prerequethandler = null;
				Log.info(this, "loading PreProcessHandler ".concat(String.valueOf(String.valueOf(s1))));
				prerequethandler = (PreRequestHandler) classloader.loadClass(s1).newInstance();
				prerequethandler.init(servletcontext);
				prerequethandler.doStart(httpservletrequest);
				prerequethandler.processRequest(httpservletrequest);
				prerequethandler.doEnd(httpservletrequest);
			} catch (ClassNotFoundException classnotfoundexception) {
				Log.error(
					this,
					String.valueOf(
						String.valueOf((new StringBuffer("fail to load PreProcessHandler[")).append(s1).append("]"))));
				throw new CommonException("fail to load PreProcessHandler", classnotfoundexception);
			} catch (InstantiationException instantiationexception) {
				Log.error(
					this,
					String.valueOf(
						String.valueOf((new StringBuffer("fail to load PreProcessHandler[")).append(s1).append("]"))));
				throw new CommonException("fail to load PreProcessHandler", instantiationexception);
			} catch (IllegalAccessException illegalaccessexception) {
				Log.error(
					this,
					String.valueOf(
						String.valueOf((new StringBuffer("fail to load PreProcessHandler[")).append(s1).append("]"))));
				throw new CommonException("fail to load PreProcessHandler", illegalaccessexception);
			}
		}
		if (urlmapping.useRequestHandler()) {
			String s2 = screenflowmanager.getURLMapping(s).getEventClass();
			Log.info(
				this,
				String.valueOf(
					String.valueOf(
						(new StringBuffer("Event Class Name(path info is:")).append(s).append("):").append(s2))));
			if (s2 == null || s2.length() <= 0)
				Log.info(
					this,
					String.valueOf(
						String.valueOf(
							(new StringBuffer("Event class is not created.(Path-Info:")).append(s).append(")"))));
			String s3 = urlmapping.getRequestHandler();
			try {
				RequestHandler requesthandler = null;
				Log.info(this, "loading RequestHandler ".concat(String.valueOf(String.valueOf(s3))));
				requesthandler = (RequestHandler) classloader.loadClass(s3).newInstance();
				requesthandler.init(servletcontext);
				requesthandler.doStart(httpservletrequest);
				requesthandler.processRequest(httpservletrequest);
				requesthandler.doEnd(httpservletrequest);
			} catch (ClassNotFoundException classnotfoundexception2) {
				Log.error(
					this,
					String.valueOf(
						String.valueOf((new StringBuffer("fail to load RequestHandler[")).append(s3).append("]"))));
				throw new CommonException("fail to load RequestHandler[" + s3 + "]", classnotfoundexception2);
			} catch (InstantiationException instantiationexception2) {
				Log.error(
					this,
					String.valueOf(
						String.valueOf((new StringBuffer("fail to load RequestHandler[")).append(s3).append("]"))));
				throw new CommonException("fail to load RequestHandler[" + s3 + "]", instantiationexception2);
			} catch (IllegalAccessException illegalaccessexception2) {
				Log.error(
					this,
					String.valueOf(
						String.valueOf((new StringBuffer("fail to load RequestHandler[")).append(s3).append("]"))));
				throw new CommonException("fail to load RequestHandler[" + s3 + "]", illegalaccessexception2);
			}
		}
	}
}