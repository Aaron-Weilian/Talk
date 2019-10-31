// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-07-13 9:32:36
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ServletResourceManager.java

package com.aiyun.common.util;

import java.io.*;
import java.util.HashMap;
import javax.servlet.ServletContext;

// Referenced classes of package mycrm.pub.util:
//            ResourceManager

public class ServletResourceManager
    implements ResourceManager
{

    public ServletResourceManager()
    {
        map = new HashMap();
        path = "";
    }

    public ServletResourceManager(ServletContext servletcontext)
    {
        map = new HashMap();
        path = "";
        context = servletcontext;
    }

    public void setServletContext(ServletContext servletcontext)
    {
        context = servletcontext;
    }

    public void setPath(String s)
    {
        if(!s.endsWith("\\") && !s.endsWith("/"))
            s = s + "/";
        path = s;
        path = path.replace('\\', '/');
    }

    public String getPath()
    {
        return path;
    }

    public void init()
    {
        map.clear();
    }

    public boolean shouldReload(String s)
        throws IOException, FileNotFoundException
    {
        if(s == null)
            throw new NullPointerException(this + "# File Name is null.");
        String s1 = path + s;
        s1 = s1.replace('\\', '/');
        return map.get(s1) == null;
    }

    public InputStream getResourceAsStream(String s)
        throws IOException, FileNotFoundException
    {
        if(s == null)
            throw new NullPointerException(this + "# File Name is null.");
        if(context == null)
            throw new NullPointerException(this + "# ServletContext is null.");
        String s1 = path + s;
        s1 = s1.replace('\\', '/');
        InputStream inputstream = context.getResourceAsStream(s1);
        if(inputstream == null)
        {
            throw new FileNotFoundException(this + "# " + s1 + " is not Found.");
        } else
        {
            map.put(s1, "");
            return inputstream;
        }
    }

    private HashMap map;
    private ServletContext context;
    private String path;
}