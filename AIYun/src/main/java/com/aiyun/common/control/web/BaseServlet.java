package com.aiyun.common.control.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Element;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.exception.SystemException;
import com.aiyun.common.dict.Constants;
import com.aiyun.common.manager.FileResourceManager;
import com.aiyun.common.manager.ResourceManager;
import com.aiyun.common.manager.ServletResourceManager;
import com.aiyun.common.tool.Log;
import com.aiyun.common.vo.CommonBean;

public class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BaseServlet() {
	}

	public void init() throws ServletException {
		super.init();
		
		resourceManager = null;

		if (getServletContext().getAttribute(ScreenFlowManager.KEY_NAME) != null) {
			getServletContext().removeAttribute(ScreenFlowManager.KEY_NAME);
		}
		
		if (getServletContext().getAttribute(RequestProcessor.KEY_NAME) != null) {
			getServletContext().removeAttribute(RequestProcessor.KEY_NAME);
		}
		
		System.setProperty(PROPERTY_FOLDER_KEY_NAME, Thread.currentThread().getContextClassLoader().getResource("").getPath()); 
		
		createResourceManager();
		getLogger();
		Class self = getClass();
		
		getServletContext().setAttribute(DOMAIN_CLASS, self);
		getScreenFlowManager();
		getRequestProcessor();

	}

	public void service(HttpServletRequest request, HttpServletResponse response) {

		String pathInfo = request.getPathInfo();
		if (pathInfo == null) {
			pathInfo = "";
		}
		
		
		
		if (pathInfo.indexOf(".") != -1) {
			try {
				request.getRequestDispatcher(pathInfo).forward(request, response);
			} catch (ServletException e) {
				Log.error(this, "the page" + pathInfo + "can not loading");
				throw new CommonException("the page" + pathInfo + "can not loading");
			} catch (IOException e) {
				Log.error(this, "the page" + pathInfo + "forward fail");
				throw new CommonException("the page" + pathInfo + "forward fail");
			}
		}
		
		
		
		
		
		request.setAttribute("pathInfo", pathInfo);
		ServletContext servletContext = getServletContext();

		try {
			ScreenFlowManager screenFlowManager = getScreenFlowManager();
			Log.info(this, "requesting...");
			getRequestProcessor().processRequest(servletContext, request);
			String forwardUrl = (String)request.getAttribute("forwardurl");
			while (forwardUrl != null) {
				Log.info(this, "get forward url: " + forwardUrl + "");
				request.setAttribute("pathInfo", forwardUrl);
				request.removeAttribute("forwardurl");
				getRequestProcessor().processRequest(servletContext, request);
				forwardUrl = (String)request.getAttribute("forwardurl");
			}
			screenFlowManager.getNextScreen(servletContext, request);
			processResponce(request, response, screenFlowManager);
			servletContext.setAttribute(ScreenFlowManager.KEY_NAME, screenFlowManager);
		} catch (Exception exception) {
			exception.printStackTrace();
			try {
				Log.error(this, "handling exception");
				request.setAttribute("exception", exception);
				ScreenFlowManager screenFlowManager = getScreenFlowManager();
				URLMapping urlmapping = screenFlowManager.getURLMapping(pathInfo);
				String exceptionHandler;
				String exceptionURL;
				if (exception instanceof SystemException) {
					Exception systemException = (SystemException)exception;
					if (systemException != null) {
						Log.error(this, "nested exception");
					}
					exceptionHandler = urlmapping.getSystemExceptionHandler();
					exceptionURL = "/systemException";
				} else {
					if (exception instanceof CommonException) {
						Throwable throwable = ((CommonException)exception).getNestedThrowable();
						if (throwable != null) {
							Log.error(this, "nested Throwable");
						}
					}
					exceptionHandler = urlmapping.getApplicationExceptionHandler();
					exceptionURL = "/applicationException";
				}
				if (exceptionHandler != null && exceptionHandler.length() > 0) {
					ExceptionHandler exceptionhandler = createExceptionHandler(exceptionHandler);
					exceptionhandler.init(servletContext);
					exceptionhandler.handleException(request);
				}
				screenFlowManager.getNextScreen(servletContext, request, exceptionURL);
				processResponce(request, response, screenFlowManager);
				servletContext.setAttribute(ScreenFlowManager.KEY_NAME, screenFlowManager);
			} catch (Exception error) {
				Log.error(this, "caught exception during handling exception or processing error view.");
				throw new CommonException("exception during handling exception");
			}
		}
	}

	/**
     * @return
     */
    private boolean validateLogin() {
        // TODO Auto-generated method stub
        
        return false;
    }

    /**
     * @param pathInfo
     * @return
     */
    private boolean validatePermission(String pathInfo) {
        // TODO Auto-generated method stub
        return false;
    }

    protected void processResponce(HttpServletRequest httpservletrequest,HttpServletResponse httpservletresponse,ScreenFlowManager screenflowmanager)
		throws ServletException, IOException
    {
		if (httpservletresponse.isCommitted())
			return;
		Locale locale = (Locale)httpservletrequest.getSession().getAttribute("language");
		if (locale == null)
			locale = Locale.US;
		String currentScreen = (String)httpservletrequest.getAttribute("currentScreen");
		String ScreenTemplate = screenflowmanager.getTemplate(locale, currentScreen);
		Log.info(this, "currentScreenName=".concat(String.valueOf(String.valueOf(currentScreen))));
		Log.info(this, "forwardUrl=".concat(String.valueOf(String.valueOf(ScreenTemplate))));
		if (ScreenTemplate == null || ScreenTemplate.equals(""))
			throw new CommonException("there is no forwardUrl");
		RequestDispatcher requestdispatcher = getServletConfig().getServletContext().getRequestDispatcher(ScreenTemplate);
		if (requestdispatcher == null) {
			throw new CommonException(
				String.valueOf(String.valueOf((new StringBuffer("there is no request dispatcher for [")).append(ScreenTemplate).append("]"))));
		} else {
			requestdispatcher.forward(httpservletrequest, httpservletresponse);
			return;
		}
	}

	protected ExceptionHandler createExceptionHandler(String s) {
		try {
			Class class1 = (Class)getServletContext().getAttribute(DOMAIN_CLASS);
			ClassLoader classloader = class1.getClassLoader();
			ExceptionHandler exceptionhandler = (ExceptionHandler)classloader.loadClass(s).newInstance();
			ExceptionHandler exceptionhandler1 = exceptionhandler;
			return exceptionhandler1;
		} catch (IllegalAccessException illegalaccessexception) {
			throw new CommonException(
				String.valueOf(String.valueOf((new StringBuffer("Cannot create exception handler of class [")).append(s).append("]"))));
		} catch (InstantiationException instantiationexception) {
			throw new CommonException(
				String.valueOf(String.valueOf((new StringBuffer("Cannot create exception handler of class [")).append(s).append("]"))));
		} catch (ClassNotFoundException classnotfoundexception) {
			throw new CommonException(
				String.valueOf(String.valueOf((new StringBuffer("Cannot create exception handler of class [")).append(s).append("]"))));
		}
	}

	protected RequestProcessor getRequestProcessor() {
		Log.info(this, "MainServlet.getRequestProcessor()");
		RequestProcessor requestprocessor = (RequestProcessor)getServletContext().getAttribute(RequestProcessor.KEY_NAME);
		if (requestprocessor == null) {
			Log.info(this, "RequestProcessor is initializing..");
			requestprocessor = new RequestProcessor();
			requestprocessor.init(getServletContext());
			getServletContext().setAttribute(RequestProcessor.KEY_NAME, requestprocessor);
		}
		return requestprocessor;
	}

	protected ScreenFlowManager getScreenFlowManager() {
		Log.info(this, "MainServlet.getScreenFlowManager()");
		ScreenFlowManager screenflowmanager = (ScreenFlowManager)getServletContext().getAttribute(ScreenFlowManager.KEY_NAME);
		if (screenflowmanager == null) {
			Log.info(this, "ScreenFlowManager is initializing..");
			screenflowmanager = new ScreenFlowManager();
			getServletContext().setAttribute(ScreenFlowManager.KEY_NAME, screenflowmanager);
		}
		screenflowmanager.init(getServletContext());
		return screenflowmanager;
	}

	protected void createResourceManager() {
		ServletContext servletcontext = getServletContext();
		String configPath = servletcontext.getInitParameter("appl.config.path");
		if (configPath == null) {
		    configPath = System.getProperty(PROPERTY_FOLDER_KEY_NAME);
        }  
		if (configPath == null || configPath.length() == 0) {
		    configPath = "/WEB-INF/props";
        }
		resourceManager = new ServletResourceManager(getServletContext());
		resourceManager.setPath(configPath);
		System.out.println("ResourceManager is ".concat(String.valueOf(String.valueOf(resourceManager))));
		System.out.println("Property path is ".concat(String.valueOf(String.valueOf(configPath))));
	}

	public static ResourceManager getResourceManager() {
		return resourceManager;
	}

//	private List getChildElements(Element element) {
//		ArrayList arraylist = new ArrayList();
//		if (element != null) {
//			Element element1;
//			for (Iterator iterator = element.elementIterator(); iterator.hasNext(); arraylist.add(element1))
//				element1 = (Element)iterator.next();
//		}
//		return arraylist;
//	}

	private void getLogger() {
		Properties p = new Properties();
		try {
			p.load(getResourceManager().getResourceAsStream("log4j.properties"));
			PropertyConfigurator.configure(p);
		} catch (FileNotFoundException e) {
			System.err.println("Can not found the log4j.properties");
		} catch (IOException e) {
			System.err.println("Can not found the log4j.properties");
		} catch (Exception e) {
			System.err.println("Can not found the log4j.properties");
		}

	}
	public static final String DOMAIN_CLASS = "DOMAIN_CLASS";
	public static final String RESOURCE_WAR = "WEB_RESOURCE";
	private static final String PROPERTY_FOLDER_KEY_NAME = "appl.props.path";
	private static ResourceManager resourceManager = null;
}