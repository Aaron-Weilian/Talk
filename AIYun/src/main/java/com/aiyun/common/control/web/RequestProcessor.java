package com.aiyun.common.control.web;

import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.exception.SystemException;

public class RequestProcessor implements Serializable
{

    public RequestProcessor()
    {
    }

    public void init(ServletContext servletcontext)
    {
    }

    public void processRequest(ServletContext servletcontext, HttpServletRequest httpservletrequest)
        throws SystemException
    {
        ScreenFlowManager screenflowmanager = (ScreenFlowManager)servletcontext.getAttribute(ScreenFlowManager.KEY_NAME);
        if(screenflowmanager == null) {
            throw new CommonException("ScreenFlowManager is not found.");
        }
            
        if(eventTranslator == null) {
            eventTranslator = new RequestToEventTranslator();
        }
            
        eventTranslator.processRequest(servletcontext, httpservletrequest);
    }

    protected RequestToEventTranslator eventTranslator;
    public static String KEY_NAME = "RequestProcessor";
    public static final String REQUEST_EVENT_KEY_NAME = "eventName";

}