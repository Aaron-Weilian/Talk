// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-07-13 9:39:23
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   URLMapping.java

package com.aiyun.common.control.web;

import java.io.Serializable;
import java.util.HashMap;

public class URLMapping implements Serializable {

	public URLMapping(String url, String screen) {
		useRequestHandler = false;
		useFlowHandler = false;
		useWebController = false;
		usePreProcessHandler = false;
		useEventClass = false;
		flowHandler = null;
		requestHandler = null;
		webController = null;
		preProcessHandler = null;
		eventClass = null;
		this.url = url;
		this.screen = screen;
		object = null;
	}

	public URLMapping(String url, 
					  String screen, 
					  boolean useRequestHandler, 
					  boolean useFlowHandler,
					  boolean useWebController, 
					  boolean usePreProcessHandler, 
					  boolean useEventClass, 
					  String requestHandler,
					  String flowHandler, 
					  String webController, 
					  String preProcessHandler, 
					  HashMap resultMappings,
					  String applicationExceptionHandler, 
					  String systemExceptionHandler, 
					  String eventClass, 
					  String object) {
		this.url = url;
		this.screen = screen;
		this.flowHandler = flowHandler;
		this.webController = webController;
		this.requestHandler = requestHandler;
		this.preProcessHandler = preProcessHandler;
		this.useRequestHandler = useRequestHandler;
		this.useFlowHandler = useFlowHandler;
		this.useWebController = useWebController;
		this.useEventClass = useEventClass;
		this.usePreProcessHandler = usePreProcessHandler;
		this.resultMappings = resultMappings;
		this.applicationExceptionHandler = applicationExceptionHandler;
		this.systemExceptionHandler = systemExceptionHandler;
		this.eventClass = eventClass;
		this.object = object;
	}

	public String getPreProcessHandler() {
		return preProcessHandler;
	}

	public String getFlowHandler() {
		return flowHandler;
	}

	public String getWebController() {
		return webController;
	}

	public String getRequestHandler() {
		return requestHandler;
	}

	public String getEventClass() {
		return eventClass;
	}

	public HashMap getResultMappings() {
		return resultMappings;
	}

	public String getResultScreen(String s) {
		if (resultMappings != null)
			return (String) resultMappings.get(s);
		else
			return null;
	}

	public String getScreen() {
		return screen;
	}

	public String toString() {
		return String.valueOf(String.valueOf((new StringBuffer("[URLMapping: url=")).append(url)
				.append(", useRequestHandler=").append(useRequestHandler).append(", useFlowHandler=")
				.append(useFlowHandler).append(", requestHandler=").append(requestHandler).append(", flowHandler=")
				.append(flowHandler).append(", resultMappings=").append(resultMappings).append("]")));
	}

	public boolean usePreProcessHandler() {
		return usePreProcessHandler;
	}

	public boolean useFlowHandler() {
		return useFlowHandler;
	}

	public boolean useWebController() {
		return useWebController;
	}

	public boolean useRequestHandler() {
		return useRequestHandler;
	}

	public String getApplicationExceptionHandler() {
		return applicationExceptionHandler;
	}

	public String getSystemExceptionHandler() {
		return systemExceptionHandler;
	}

	public boolean useEventClass() {
		return useEventClass;
	}

	protected String url;
	protected boolean useRequestHandler;
	protected boolean useFlowHandler;
	protected boolean useWebController;
	protected boolean usePreProcessHandler;
	protected boolean useEventClass;
	protected String flowHandler;
	protected String requestHandler;
	protected String webController;
	protected String preProcessHandler;
	protected HashMap resultMappings;
	protected String screen;
	protected String applicationExceptionHandler;
	protected String systemExceptionHandler;
	protected String eventClass;
	protected String object;

	/**
	 * @return
	 */
	public String getObject() {
		return object;
	}

}