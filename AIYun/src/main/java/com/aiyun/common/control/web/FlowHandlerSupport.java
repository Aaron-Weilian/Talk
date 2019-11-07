// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-07-13 9:36:18
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FlowHandlerSupport.java

package com.aiyun.common.control.web;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.tool.Log;


public abstract class FlowHandlerSupport implements FlowHandler
{

    public FlowHandlerSupport()
    {
        context = null;
        Log.info(this, "initializing...");
    }

    public void init(ServletContext servletcontext)
    {
        context = servletcontext;
    }

    public void doStart(HttpServletRequest httpservletrequest)
    {
    }

    public void doEnd(HttpServletRequest httpservletrequest)
    {
    }

    public HashMap getParameters(HttpServletRequest httpservletrequest)
    {
        if(parameter == null)
            parameter = new RequestParameter(httpservletrequest);
        return parameter.getParameters();
    }

    public String getParameter(HttpServletRequest httpservletrequest, String s)
    {
        if(parameter == null)
            parameter = new RequestParameter(httpservletrequest);
        return parameter.getParameter(s);
    }

    public abstract String processFlow(HttpServletRequest httpservletrequest)
        throws CommonException;

    protected ServletContext context;
    protected RequestParameter parameter;
}