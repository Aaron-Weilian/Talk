package com.aiyun.common.util;

import java.io.*;
import java.util.HashMap;

// Referenced classes of package mycrm.pub.util:
//            ResourceManager

public class ClassResourceManager
    implements ResourceManager
{

    public ClassResourceManager()
    {
        map = new HashMap();
        path = "";
        cls = getClass();
    }

    public ClassResourceManager(Class class1)
    {
        map = new HashMap();
        path = "";
        cls = class1;
    }

    public void setClass(Class class1)
    {
        if(class1 != null)
            cls = class1;
    }

    public void setPath(String s)
    {
        if(!s.endsWith("\\") && !s.endsWith("/"))
            s = s + "/";
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
        if(cls == null)
            throw new NullPointerException(this + "# Class is null.");
        String s1 = path + s;
        s1 = s1.replace('\\', '/');
        InputStream inputstream = cls.getResourceAsStream(s1);
        if(inputstream == null)
        {
            throw new FileNotFoundException(this + "# " + s1 + " is not Found.");
        } else
        {
            map.put(s1, "");
            return inputstream;
        }
    }

    private static final String FILE_SEPARATOR = "/";
    private HashMap map;
    private Class cls;
    private String path;
}