// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-07-13 9:38:10
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RequestProcessor.java

package com.aiyun.common.control.web;

import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.exception.SystemException;

// Referenced classes of package mycrm.pub.control.web:
//            ScreenFlowManager, RequestToEventTranslator

public class RequestProcessor
    implements Serializable
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
        if(screenflowmanager == null)
            throw new CommonException("ScreenFlowManager is not found.");
        if(eventTranslator == null)
            eventTranslator = new RequestToEventTranslator();
        eventTranslator.processRequest(servletcontext, httpservletrequest);
    }

    protected RequestToEventTranslator eventTranslator;
    public static String KEY_NAME = "RequestProcessor";
    public static final String REQUEST_EVENT_KEY_NAME = "eventName";

}