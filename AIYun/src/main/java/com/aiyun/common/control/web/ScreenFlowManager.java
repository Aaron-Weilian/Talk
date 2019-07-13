package com.aiyun.common.control.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.exception.SystemException;
import com.aiyun.common.util.Log;
import com.aiyun.common.util.ResourceManager;

// Referenced classes of package mycrm.pub.control.web:
//            BaseServlet, ScreenFlowXmlDAO, Screen, URLMapping, 
//            FlowHandler, Parameter

public class ScreenFlowManager implements Serializable {
 
	public ScreenFlowManager() {
		REQUEST_MAPPING_RESOURCE_NAME = "requestmappings.xml";
		if (screens == null) {
			screens = new HashMap();
		}
	}

	public void init(ServletContext servletcontext) {
		try {
			Log.info(this, "initializing...");
			ResourceManager resourceManager = BaseServlet.getResourceManager();
			if (BaseServlet.getResourceManager().shouldReload(REQUEST_MAPPING_RESOURCE_NAME)) {
				urlMappings =
					ScreenFlowXmlDAO.loadRequestMappings(
						REQUEST_MAPPING_RESOURCE_NAME,
						resourceManager.getResourceAsStream(REQUEST_MAPPING_RESOURCE_NAME));
				screenDefinitionMappings =
					ScreenFlowXmlDAO.loadScreenDefinitionMappings(
						REQUEST_MAPPING_RESOURCE_NAME,
						resourceManager.getResourceAsStream(REQUEST_MAPPING_RESOURCE_NAME));
			}
			Log.info(this, "initialized Screens and URL mappings");
		} catch (IOException ioexception) {
			throw new CommonException(ioexception.getMessage());
		}
	}

	public HashMap getScreens(Locale locale) {
		try {
			ResourceManager resourcemanager = BaseServlet.getResourceManager();
			String s =
				String.valueOf(
					String.valueOf((new StringBuffer(String.valueOf(String.valueOf(locale.getLanguage())))).append("_").append(locale.getCountry())));
			boolean flag = true;
			Log.info(this, "Language location:".concat(String.valueOf(String.valueOf(s))));
			if (screens.containsKey(s)) {
				String s1 = (String)screenDefinitionMappings.get(s);
				if (s1 == null)
					throw new CommonException(
						String.valueOf(
							String.valueOf(
								(new StringBuffer("not supported locale(")).append(locale).append(
									"). see requestmappings.xml screen-definition tag"))));
				flag = resourcemanager.shouldReload(s1);
			}
			if (flag) {
				Log.info(this, "Screendefinisions reload.");
				String s2 = (String)screenDefinitionMappings.get(s);
				java.io.InputStream inputstream = resourcemanager.getResourceAsStream(s2);
				HashMap hashmap = ScreenFlowXmlDAO.loadScreenDefinitions(s2, inputstream);
				screens.put(s, hashmap);
			}
			HashMap hashmap1 = (HashMap)screens.get(s);
			return hashmap1;
		} catch (IOException ioexception) {
			Log.error(this, "Resource is not found.");
			throw new CommonException(ioexception.getMessage());
		}
	}

	public String getTemplate(Locale locale, String s) {
		HashMap hashmap = getScreens(locale);
		if (hashmap == null)
			return null;
		String s1 = null;
		Screen screen = (Screen)hashmap.get(s);
		if (screen != null)
			s1 = screen.getTemplate();
		else
			throw new CommonException(String.valueOf(String.valueOf((new StringBuffer("no such screen(")).append(s).append(")"))));
		if (s1 == null || s1.length() == 0) {
			Screen screen1 = (Screen)hashmap.get("default");
			Log.info(this, "use default screen");
			s1 = screen1.getTemplate();
		}
		return s1;
	}

	public String getTemplateFile(Locale locale) {
		HashMap hashmap = getScreens(locale);
		if (hashmap == null)
			return null;
		else
			return (String)hashmap.get("template-file");
	}

	public URLMapping getURLMapping(String s) {
		if (urlMappings != null && urlMappings.containsKey(s))
			return (URLMapping)urlMappings.get(s);
		else
			return null;
	}

	public void getNextScreen(ServletContext servletcontext, HttpServletRequest httpservletrequest, String s)
	//throws SystemException
	{
		Class class1 = (Class)servletcontext.getAttribute("DOMAIN_CLASS");
		ClassLoader classloader = class1.getClassLoader();
		Log.info(this, "pathInfo is ".concat(String.valueOf(String.valueOf(s))));
		URLMapping urlmapping = getURLMapping(s);
		if (urlmapping == null) {
			Log.info(
				this,
				String.valueOf(
					String.valueOf((new StringBuffer("no url mapping for PathInfo(")).append(s).append("). use default PathInfo(/default)"))));
			urlmapping = getURLMapping("/default");
		}
		if (urlmapping == null)
			throw new CommonException(
				String.valueOf(String.valueOf((new StringBuffer("no url mapping entry for [")).append(s).append("] in requestmappings.xml"))));
		String s1;
		if (!urlmapping.useFlowHandler()) {
			s1 = urlmapping.getScreen();
			if (s1 == null)
				throw new CommonException(
					String.valueOf(
						String.valueOf((new StringBuffer("no corresponding screen for (PathInfo=[")).append(s).append("]) in requestmappings.xml"))));
		} else {
			Log.info(this, "using flow handler for:".concat(String.valueOf(String.valueOf(s))));
			Object obj = null;
			String s2 = urlmapping.getFlowHandler();
			try {
				FlowHandler flowhandler = (FlowHandler)classloader.loadClass(s2).newInstance();
				flowhandler.init(servletcontext);
				flowhandler.doStart(httpservletrequest);
				String s3 = flowhandler.processFlow(httpservletrequest);
				Log.info(this, "flow handler processing result=".concat(String.valueOf(String.valueOf(s3))));
				flowhandler.doEnd(httpservletrequest);
				s1 = urlmapping.getResultScreen(s3);
				if (s1 == null)
					throw new CommonException(
						String.valueOf(
							String.valueOf(
								(new StringBuffer("no corresponding screen for flow result[")).append(s3).append(
									"] in requestmappings.xml(PathInfo=[").append(
									s).append(
									"])"))));
			} catch (ClassNotFoundException classnotfoundexception) {
				Log.error(this, "fail to FlowHandler");
				throw new CommonException("fail to FlowHandler", classnotfoundexception);
			} catch (InstantiationException instantiationexception) {
				Log.error(this, "fail to RequestHandler");
				throw new CommonException("fail to FlowHandler");
			} catch (IllegalAccessException illegalaccessexception) {
				Log.error(this, "fail to RequestHandler");
				throw new CommonException("fail to FlowHandler");
			}
		}
		Log.info(this, "set next screen:".concat(String.valueOf(String.valueOf(s1))));
		httpservletrequest.setAttribute("currentScreen", s1);
	}

	public void getNextScreen(ServletContext servletcontext, HttpServletRequest httpservletrequest) throws SystemException {
		String s = (String)httpservletrequest.getAttribute("pathInfo");
		getNextScreen(servletcontext, httpservletrequest, s);
	}

	public void setDefaultScreen(String s) {
		defaultScreen = s;
	}

	public Parameter getParameter(String s, HttpSession httpsession, HttpServletRequest httpservletrequest) {
		Log.info(this, "getParameter key is ".concat(String.valueOf(String.valueOf(s))));
		String s1 = (String)httpservletrequest.getAttribute("currentScreen");
		Locale locale = (Locale)httpsession.getAttribute("language");
		if (locale == null)
			locale = Locale.US;
		Screen screen = (Screen)getScreens(locale).get(s1);
		Log.info(this, "getParameter screen is ".concat(String.valueOf(String.valueOf(screen))));
		if (screen == null)
			return null;
		else
			return screen.getParameter(s);
	}

	public String getCurrentScreen(HttpServletRequest httpservletrequest) {
		return (String)httpservletrequest.getAttribute("currentScreen");
	}

	public boolean reloadNeeded() {
		try {
			return BaseServlet.getResourceManager().shouldReload(REQUEST_MAPPING_RESOURCE_NAME);
		} catch (FileNotFoundException e) {
			Log.error(this, "reloadNeeded() File " + REQUEST_MAPPING_RESOURCE_NAME + " is not exist.");
			return true;
		} catch (IOException e) {
			Log.error(this, "reloadNeeded() Error occur while reading file " + REQUEST_MAPPING_RESOURCE_NAME + ".");
		}
		return true;
	}

	protected HashMap screens;
	protected HashMap urlMappings;
	protected HashMap screenDefinitionMappings;
	protected String defaultScreen;
	protected String REQUEST_MAPPING_RESOURCE_NAME;
	public static String KEY_NAME = "ScreenFlowManager";

}