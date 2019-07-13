// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-07-13 9:36:54
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PreRequestHandler.java

package com.aiyun.common.control.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;

public interface PreRequestHandler
{

    public abstract void init(ServletContext servletcontext);

    public abstract void doStart(HttpServletRequest httpservletrequest);

    public abstract void processRequest(HttpServletRequest httpservletrequest)
        throws CommonException;

    public abstract void doEnd(HttpServletRequest httpservletrequest);
}