package com.aiyun.common.control.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aiyun.common.tool.Log;

// Referenced classes of package com.ec_one.cBank.framework.control.web:
//			  MainServlet

public class RequestParameter implements Filter{

    public RequestParameter(HttpServletRequest httpservletrequest) {
        /*
         * ResourceManager resourcemanager = BaseServlet.getResourceManager();
         * Log.info(this,
         * "1this.charset is ".concat(String.valueOf(String.valueOf(charset))));
         */
        charset = "UTF-8";
        init(httpservletrequest);
    }

    protected void init(HttpServletRequest httpservletrequest) {
        /*
         * if (charset == null) Log.info(this,
         * "no charset is specified in cpackage.xml"); else if
         * (charset.equalsIgnoreCase(CHAR_SET_NO_CONVERT)) charset = null; else if
         * (charset.equalsIgnoreCase(CHAR_SET_IN_REQUEST)) charset =
         * httpservletrequest.getCharacterEncoding();
         */
        if (charset == null) {
            Log.info(this, "no charset is specified in cpackage.xml");
        }
        Log.info(this,
                String.valueOf(String.valueOf((new StringBuffer("charset ")).append(charset).append(" will be used"))));
        if (httpservletrequest.getAttribute("_requestparameter") != null) {
            parameters = (HashMap) httpservletrequest.getAttribute("_requestparameter");
        } else {
            parse(httpservletrequest);
            httpservletrequest.setAttribute("_requestparameter", this.parameters);
        }
    }

    protected void parse(HttpServletRequest httpservletrequest) {
        Enumeration enumeration = httpservletrequest.getParameterNames();
        do {
            if (!enumeration.hasMoreElements())
                break;
            String s = (String) enumeration.nextElement();
            String as[] = httpservletrequest.getParameterValues(s);
            if (as != null) {
                if (parameters == null)
                    parameters = new HashMap();
                String as1[] = new String[as.length];
                for (int i = 0; i < as.length; i++)
                    if (as[i] != null && charset != null) {
                        try {
                            as1[i] = new String(as[i].getBytes("UTF-8"), charset);
                        } catch (UnsupportedEncodingException unsupportedencodingexception) {
                            Log.error(this, "fail to parse request");
                            return;
                        }
                        as1[i] = as1[i].trim();
                    } else {
                        as1[i] = as[i];
                    }

                parameters.put(s, as1);
            }
        } while (true);
    }

    public void setParameter(String key, String value) {
        if (key == null)
            return;
        HashMap hm = getParameters();
        hm.put(key, new String[] { value });
    }

    public String getParameter(String s) {
        String param = "";
        if (parameters != null) {
            String as[] = (String[]) parameters.get(s);
            if (as != null && as[0] != null) {
                param = as[0];
            }
        }
        return param;
    }

    public HashMap getParameters() {
        return parameters;
    }

    public String[] getParameterValues(String s) {
        if (parameters == null) {
            return null;
        } else {
            String[] arr = (String[]) parameters.get(s);
            if (arr == null) {
                arr = new String[0];
            }
            return arr;
        }

    }

    protected HashMap parameters;
    protected String charset;
    protected String REQUEST_CHAR_SET = "request-charset";
    protected String CHAR_SET_NO_CONVERT = "none";
    protected String CHAR_SET_IN_REQUEST = "request";
    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;

        String uri=request.getRequestURI();
        uri=uri.substring(uri.lastIndexOf("/")+1);
        String actionname=uri.substring(0,uri.indexOf("."));
        //ActionMessage am=ActionMapping.actions.get(actionname);
        //String classname=am.getClassname();

        
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        
    }
}
