package com.aiyun.common.control.web;

import java.io.Serializable;

public class Parameter
    implements Serializable
{

    public Parameter(String s, String s1, boolean flag)
    {
        key = s;
        value = s1;
        direct = flag;
        attribute = "";
    }

    public Parameter(String s, String s1, String s2, String s3, boolean flag, String s4)
    {
        key = s;
        value = s3;
        direct = flag;
        attribute = s1;
        templatePath = s2;
        templateFile = s4;
    }

    public boolean isDirect()
    {
        return direct;
    }

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }

    public String getAttribute()
    {
        return attribute;
    }

    public String getTemplatePath()
    {
        return templatePath;
    }

    public String getTemplateFile()
    {
        return templateFile;
    }

    public String toString()
    {
        return String.valueOf(String.valueOf((new StringBuffer("[Parameter: key=")).append(key).append(", value=").append(value).append(", direct=").append(direct).append("]")));
    }

    protected String key;
    protected String attribute;
    protected String templatePath;
    protected String value;
    protected boolean direct;
    protected String templateFile;
}