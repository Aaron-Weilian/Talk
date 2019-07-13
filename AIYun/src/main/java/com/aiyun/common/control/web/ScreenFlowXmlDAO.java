// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-07-13 9:39:06
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ScreenFlowXmlDAO.java

package com.aiyun.common.control.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.util.Log;

// Referenced classes of package mycrm.pub.control.web:
//            URLMapping, Screen, Parameter

public class ScreenFlowXmlDAO {

	public ScreenFlowXmlDAO() {
	}

	public static Element getRootElement(String s) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File(s));
			return doc.getRootElement();
		} catch (DocumentException e) {
			Log.error(ScreenFlowXmlDAO.class, "Fail to parse xml : " + s);
		}
		return null;
	}

	public static List getChildren(String s, Element element) {
		return element.element(s).elements();
	}

	public static Element getRootElement(String s, InputStream inputStream) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(inputStream);
			return doc.getRootElement();
		} catch (DocumentException e) {
			Log.error(new ScreenFlowXmlDAO(), "Fail to parse xml : " + s);
		}
		return null;
	}

	public static HashMap loadRequestMappings(String s) {
		Element element = getRootElement(s);
		return getRequestMappings(element);
	}

	public static HashMap loadRequestMappings(String s, InputStream inputstream) {
		Element element = getRootElement(s, inputstream);
		return getRequestMappings(element);
	}

	public static Map getScreenDefinitionFileNames(String s, InputStream inputstream) {
		HashMap hashmap = new HashMap();
		Element element = getRootElement(s, inputstream);
		List list = getChildren("screen-definition", element);
		for (int i = 0; list != null && i < list.size(); i++) {
			Element element1 = (Element)list.get(i);
			String s1 = element1.element("language").getText();
			String s2 = element1.element("url").getText();
			hashmap.put(s1, s2);
		}

		return hashmap;
	}

	public static HashMap loadScreenDefinitions(String s) {
		Element element = getRootElement(s);
		Element element1 = element.element("template-read-type");
		String s1 = "LOCAL_RESOURCE";
		if (element1 != null) {
			s1 = element1.getTextTrim();
			if (s1 == null || s1.length() == 0)
				s1 = "LOCAL_RESOURCE";
		}
		Log.info(ScreenFlowXmlDAO.class, "Template Read Type:".concat(String.valueOf(String.valueOf(s1))));
		return getScreens(element, s1);
	}

	public static HashMap loadScreenDefinitions(String s, InputStream inputstream) {
		Element element = getRootElement(s, inputstream);
		Element element1 = element.element("template-read-type");
		String s1 = "LOCAL_RESOURCE";
		if (element1 != null) {
			s1 = element1.getTextTrim();
			if (s1 == null || s1.length() == 0)
				s1 = "LOCAL_RESOURCE";
		}
		Log.info(ScreenFlowXmlDAO.class, "Template Read Type:".concat(String.valueOf(String.valueOf(s1))));
		return getScreens(element, s1);
	}

	public static HashMap loadScreenDefinitionMappings(String s) {
		Element element = getRootElement(s);
		return getScreenDefinitions(element);
	}

	public static HashMap loadScreenDefinitionMappings(String s, InputStream inputstream) {
		Element element = getRootElement(s, inputstream);
		return getScreenDefinitions(element);
	}

	protected static HashMap getRequestMappings(Element element) {
		HashMap hashmap = new HashMap();
		String preprocessHandlerClass = getSubTagValue(element, "preprocess-handler-class");
		String subWebcontrollerClass = getSubTagValue(element, "webcontroller-class");
		Element exceptionHandler = element.element("exception-handler");
		List list = exceptionHandler.elements("handler");
		String applicationClass = null;
		String systemClass = null;
		for (int i = 0; i < list.size(); i++) {
			Element handlerClass = (Element)list.get(i);
			String type = handlerClass.attributeValue("type");
			if (type.equals("application"))
				applicationClass = handlerClass.attributeValue("class");
			else if (type.equals("system"))
				systemClass = handlerClass.attributeValue("class");
			else
				throw new CommonException(
					String.valueOf(String.valueOf((new StringBuffer("invalid exception handler type[")).append(systemClass).append("]"))));
		}

		Iterator iterator = element.elementIterator("url-mapping");
		for (int j = 0; iterator.hasNext(); j++) {
			Element utlMapping = (Element)iterator.next();
			if (utlMapping != null) {
				String flowHandlerClass = null;
				String requestHandlerClass = null;
				String s7 = null;
				String childWebcontrollerClass = subWebcontrollerClass;
				String eventClass = null;
				boolean isUseFlowHandler = false;
				boolean isUseRequestHandler = false;
				boolean isUsePreProcessHandler = false;
				boolean isUseWebController = true;
				boolean isUseEventClass = false;
				HashMap resultHandlerHashmap = null;
				String url = utlMapping.attributeValue("url");
				String screen = utlMapping.attributeValue("screen");
				String useRequestHandler = utlMapping.attributeValue("useRequestHandler");
				String useFlowHandler = utlMapping.attributeValue("useFlowHandler");
				String usePreProcessHandler = utlMapping.attributeValue("usePreProcessHandler");
				String useEventClass = utlMapping.attributeValue("event-class");
				String object = utlMapping.attributeValue("object");
				if (usePreProcessHandler != null && usePreProcessHandler.equals("true")) {
					isUsePreProcessHandler = true;
					//s7 = preprocessHandler;
				}
				if (useRequestHandler != null && useRequestHandler.equals("true")) {
					isUseRequestHandler = true;
					requestHandlerClass = getSubTagValue(utlMapping, "request-handler-class");
				}
				if (useFlowHandler != null && useFlowHandler.equals("true")) {
					isUseFlowHandler = true;
					List flowHandlerList = utlMapping.elements("flow-handler");
					if (flowHandlerList != null && flowHandlerList.size() > 0) {
						Element flowHandler = (Element)flowHandlerList.get(0);
						flowHandlerClass = flowHandler.attributeValue("class");
						List resultList = flowHandler.elements("handler-result");
						if (resultList != null && resultList.size() > 0) {
							resultHandlerHashmap = new HashMap();
							Iterator iterator1 = resultList.iterator();
							do {
								if (!iterator1.hasNext())
									break;
								Element element5 = (Element)iterator1.next();
								String result = element5.attributeValue("result");
								String forwardscreen = element5.attributeValue("screen");
								if (!resultHandlerHashmap.containsKey("result"))
									resultHandlerHashmap.put(result, forwardscreen);
							} while (true);
						}
					} else {
						throw new CommonException("error no flow handler definitions");
					}
				}
				String webcontrollerClass = getSubTagValue(utlMapping, "webcontroller-class");
				if (webcontrollerClass != null && webcontrollerClass.length() > 0)
					childWebcontrollerClass = webcontrollerClass;
				if (useEventClass != null && useEventClass.length() > 0) {
					isUseEventClass = true;
					eventClass = getSubTagValue(utlMapping, "event-class");
				}
				URLMapping urlmapping = new URLMapping(url, screen, isUseRequestHandler, isUseFlowHandler, isUseWebController, isUsePreProcessHandler, isUseEventClass, requestHandlerClass, flowHandlerClass, childWebcontrollerClass, preprocessHandlerClass, resultHandlerHashmap, applicationClass, systemClass, eventClass,object);
				if (hashmap.containsKey(url))
					throw new CommonException(
						String.valueOf(String.valueOf((new StringBuffer("dupricated url(")).append(url).append(") in requestmappings.xml"))));
				hashmap.put(url, urlmapping);
			}
		}

		return hashmap;
	}

	public static String getSubTagValue(Element element, String s) {
		String s1;
		label0 : {
			s1 = "";
			if (element == null)
				break label0;
			List list = element.elements(s);
			Iterator iterator = list.iterator();
			Element element1;
			do {
				if (!iterator.hasNext())
					break label0;
				element1 = (Element)iterator.next();
			} while (element1 == null);
			return element1.getTextTrim();
		}
		return s1;
	}

	protected static Element loadDocument(String s) {
		try {
			Log.info(ScreenFlowXmlDAO.class, "loading xml file ");
			URL url = new URL(s);
			SAXReader reader = new SAXReader();
			Document document = reader.read(url.openStream());
			Element element = document.getRootElement();
			return element;
		} catch (MalformedURLException malformedurlexception) {
			throw new CommonException(
				String.valueOf(String.valueOf((new StringBuffer("fail to load [")).append(s).append("]"))),
				malformedurlexception);
		} catch (DocumentException jdomexception) {
			throw new CommonException(String.valueOf(String.valueOf((new StringBuffer("fail to load [")).append(s).append("]"))), jdomexception);
		} catch (IOException ioexception) {
			throw new CommonException(String.valueOf(String.valueOf((new StringBuffer("fail to load [")).append(s).append("]"))), ioexception);
		}
	}

	protected static Element loadDocument(InputStream inputstream) {
		try {
			Log.info(ScreenFlowXmlDAO.class, "loading xml file ");
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputstream);
			Element element = document.getRootElement();
			return element;
		} catch (DocumentException jdomexception) {
			throw new CommonException("fail to load.", jdomexception);
		}
	}

	protected static HashMap getScreens(Element element, String s) {
		HashMap hashmap = new HashMap();
		List list = element.elements("screen");
		Iterator iterator = list.iterator();
		do {
			if (!iterator.hasNext())
				break;
			Element element1 = (Element)iterator.next();
			String s1 = getSubTagValue(element1, "screen-name");
			String s2 = getSubTagValue(element1, "content-type");
			String s3 = getSubTagValue(element1, "template");
			String s4 = getSubTagValue(element1, "template-file");
			String s5 = getSubTagValue(element1, "template-path");
			HashMap hashmap1 = getParameters(element1);
			Screen screen = new Screen(s1, s2, s3, s5, s4, hashmap1, s);
			if (!hashmap.containsKey(screen))
				hashmap.put(s1, screen);
		} while (true);
		return hashmap;
	}

	protected static HashMap getParameters(Element element) {
		HashMap hashmap = new HashMap();
		if (element != null) {
			List list = element.elements("parameter");
			Iterator iterator = list.iterator();
			do {
				if (!iterator.hasNext())
					break;
				Element element1 = (Element)iterator.next();
				if (element1 != null) {
					String s = element1.attributeValue("key");
					String s1 = element1.attributeValue("value");
					String s2 = element1.attributeValue("direct");
					String s3 = element1.attributeValue("attribute");
					String s4 = element1.attributeValue("template-path");
					String s5 = element1.attributeValue("template-file");
					boolean flag = false;
					if (s2 != null && s2.equals("true"))
						flag = true;
					if (!hashmap.containsKey(s))
						hashmap.put(s, new Parameter(s, s3, s4, s1, flag, s5));
				}
			} while (true);
		}
		return hashmap;
	}

	protected static HashMap getScreenDefinitions(Element element) {
		HashMap hashmap = new HashMap();
		Iterator iterator = element.elementIterator("screen-definition");
		do {
			if (!iterator.hasNext())
				break;
			Element element1 = (Element)iterator.next();
			if (element1 != null) {
				String s = element1.attributeValue("language");
				String s1 = element1.attributeValue("url");
				if (s != null && s1 != null && !hashmap.containsKey(s))
					hashmap.put(s, s1);
			}
		} while (true);
		return hashmap;
	}

	public static final String URL_MAPPING = "url-mapping";
	public static final String SCREEN_DEFINITION = "screen-definition";
	public static final String URL = "url";
	public static final String LANGUAGE = "language";
	public static final String TEMPLATE = "template";
	public static final String TEMPLATE_FILE = "template-file";
	public static final String RESULT = "result";
	public static final String NEXT_SCREEN = "screen";
	public static final String USE_REQUEST_HANDLER = "useRequestHandler";
	public static final String USE_FLOW_HANDLER = "useFlowHandler";
	public static final String USE_PREPROCESS_HANDLER = "usePreProcessHandler";
	public static final String FLOW_HANDLER_CLASS = "class";
	public static final String REQUEST_HANDLER_CLASS = "request-handler-class";
	public static final String PREPROCESS_HANDLER_CLASS = "preprocess-handler-class";
	public static final String HANDLER_RESULT = "handler-result";
	public static final String FLOW_HANDLER = "flow-handler";
	public static final String KEY = "key";
	public static final String ATTRIBUTE = "attribute";
	public static final String TEMPLATE_PATH = "template-path";
	public static final String VALUE = "value";
	public static final String DIRECT = "direct";
	public static final String SCREEN = "screen";
	public static final String SCREEN_NAME = "screen-name";
	public static final String PARAMETER = "parameter";
	public static final String CONTENT_TYPE = "content-type";
	public static final String TEMPLATE_READ_TYPE = "template-read-type";
	public static final String EXCEPTION_HANDLER = "exception-handler";
	public static final String HANDLER = "handler";
	public static final String TYPE = "type";
	public static final String CLASS = "class";
	public static final String APPLICATION = "application";
	public static final String SYSTEM = "system";
	public static final String WEBCONTROLLER_CLASS = "webcontroller-class";
	public static final String EVENT_CLASS = "event-class";
}