package com.aiyun.common.control.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public interface ExceptionHandler
{

    public abstract void init(ServletContext servletcontext);

    public abstract void handleException(HttpServletRequest httpservletrequest);
}