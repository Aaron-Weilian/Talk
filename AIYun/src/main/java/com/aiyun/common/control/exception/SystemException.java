package com.aiyun.common.control.exception;

import java.io.*;

public class SystemException extends Exception
    implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8821432727977886456L;
	
	public SystemException()
    {
        stackTrace = null;
        writeStackTrace(this);
    }

    public SystemException(Exception exception)
    {
        stackTrace = null;
        nestedException = exception;
        writeStackTrace(exception);
    }

    public SystemException(String s)
    {
        super(s);
        stackTrace = null;
        writeStackTrace(this);
    }

    public SystemException(String s, Exception exception)
    {
        super(s);
        stackTrace = null;
        nestedException = exception;
        writeStackTrace(exception);
    }

    public Exception getNestedException()
    {
        return nestedException;
    }

    private void writeStackTrace(Exception exception)
    {
        StringWriter stringwriter = new StringWriter();
        PrintWriter printwriter = new PrintWriter(stringwriter);
        Exception exception1 = null;
        if(exception instanceof SystemException)
            exception1 = ((SystemException)exception).getNestedException();
        if(exception1 != null)
            exception1.printStackTrace(printwriter);
        else
            exception.printStackTrace(printwriter);
        stackTrace = stringwriter.toString();
    }

    protected Exception nestedException;
    private String stackTrace;
}