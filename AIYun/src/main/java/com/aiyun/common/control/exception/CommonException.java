package com.aiyun.common.control.exception;

import java.io.*;

public class CommonException extends RuntimeException
    implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3559528178582941887L;
	
	public CommonException(String s)
    {
        super(s);
        t = null;
        stackTrace = null;
        writeStackTrace(this);
    }

    public CommonException(String s, Throwable throwable)
    {
        super(s);
        t = null;
        stackTrace = null;
        t = throwable;
        writeStackTrace(t);
    }

    public String getThrowable()
    {
        return "Received throwable with Message: ".concat(String.valueOf(String.valueOf(t.getMessage())));
    }

    public Throwable getNestedThrowable()
    {
        return t;
    }

    public void printStackTrace()
    {
        super.printStackTrace();
        if(t != null)
        {
            System.err.println("-----nested throwable-----");
            t.printStackTrace();
        }
    }

    private void writeStackTrace(Throwable throwable)
    {
        StringWriter stringwriter = new StringWriter();
        PrintWriter printwriter = new PrintWriter(stringwriter);
        Throwable throwable1 = null;
        if(throwable instanceof CommonException)
            throwable1 = ((CommonException)throwable).getNestedThrowable();
        if(throwable1 != null)
            throwable1.printStackTrace(printwriter);
        else
            throwable.printStackTrace(printwriter);
        stackTrace = stringwriter.toString();
    }

    private Throwable t;
    private String stackTrace;
}