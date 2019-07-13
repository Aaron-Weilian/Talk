// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-07-13 9:38:40
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Screen.java

package com.aiyun.common.control.web;

import java.io.Serializable;
import java.util.HashMap;

// Referenced classes of package mycrm.pub.control.web:
//            Parameter

public class Screen
    implements Serializable
{

    public Screen(String s, HashMap hashmap)
    {
        name = null;
        contentType = null;
        parameters = null;
        template = null;
        templateFile = null;
        templatePath = null;
        templateReadType = null;
        name = s;
        contentType = null;
        parameters = hashmap;
    }

    public Screen(String s, String s1, HashMap hashmap)
    {
        name = null;
        contentType = null;
        parameters = null;
        template = null;
        templateFile = null;
        templatePath = null;
        templateReadType = null;
        name = s;
        contentType = s1;
        parameters = hashmap;
    }

    public Screen(String s, String s1, String s2, String s3, String s4, HashMap hashmap, String s5)
    {
        name = null;
        contentType = null;
        parameters = null;
        template = null;
        templateFile = null;
        templatePath = null;
        templateReadType = null;
        name = s;
        contentType = s1;
        parameters = hashmap;
        template = s2;
        templatePath = s3;
        templateFile = s4;
        templateReadType = s5;
    }

    public HashMap getParameters()
    {
        return parameters;
    }

    public Parameter getParameter(String s)
    {
        return (Parameter)parameters.get(s);
    }

    public String getContentType()
    {
        return contentType;
    }

    public String getTemplate()
    {
        return template;
    }

    public String getTemplatePath()
    {
        return templatePath;
    }

    public String getTemplateFile()
    {
        return templateFile;
    }

    public String getTemplateReadType()
    {
        return templateReadType;
    }

    public String toString()
    {
        return String.valueOf(String.valueOf((new StringBuffer("[Screen: name=")).append(name).append(", parameters=").append(parameters).append("]")));
    }

    private String name;
    private String contentType;
    private HashMap parameters;
    private String template;
    private String templateFile;
    private String templatePath;
    private String templateReadType;
}