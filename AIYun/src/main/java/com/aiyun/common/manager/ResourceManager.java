package com.aiyun.common.manager;

import java.io.*;

public interface ResourceManager
{

    public abstract InputStream getResourceAsStream(String s)
        throws IOException, FileNotFoundException;

    public abstract boolean shouldReload(String s)
        throws IOException, FileNotFoundException;

    public abstract void init();

    public abstract void setPath(String s);

    public abstract String getPath();
}