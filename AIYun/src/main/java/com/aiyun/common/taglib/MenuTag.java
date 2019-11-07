package com.aiyun.common.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.aiyun.common.dict.ModuleBox;
import com.aiyun.common.dict.ModuleBox.ModuleType;
import com.aiyun.common.taglib.model.MenuModel;
import com.aiyun.common.taglib.model.MenuModel.MenuInfo;
import com.aiyun.common.tool.Log;
import com.aiyun.common.vo.CommonBean;

public class MenuTag extends BodyTagSupport {
    private static final long serialVersionUID = -1967315274595528339L;
    
    private static final String PROD_PREFIX = "AIYun/Prod";
    
    private CommonBean menus = null;
    
	public int doStartTag() throws JspException {
	    MenuModel menuMoudel = new MenuModel(menus);
	    Map<String, List<MenuInfo>> menuMap = menuMoudel.getMenuMap();
		JspWriter jspWriter = pageContext.getOut();
		boolean first = true;
		try {
			jspWriter.println("<ul>");
			for (String key : menuMap.keySet()) {
			    if (first) {
			        jspWriter.println("<li class=\"active\" >");
			    }
			    else {
			        jspWriter.println("<li>");
			    }
			    first = false;
			    
			    
			    jspWriter.println("<a href=\"#\" class=\""+ModuleBox.moduleMap.get(key).getLinkClass()+"\">"+ModuleBox.moduleMap.get(key).getName()+"</a>");
			    if(menuMap.get(key)!=null) {
			        jspWriter.println("<ul class=\"closed \">");
    			    for(MenuInfo menu: (List<MenuInfo>)menuMap.get(key)) {
    			        jspWriter.println("<li>");
                        jspWriter.println("<a href=\""+PROD_PREFIX+menu.getHref()+"\">"+menu.getDisplayName()+"</a>");
                        jspWriter.println("</li>");
    			    }
    			    jspWriter.println("</ul>");
			    }
			    jspWriter.println("</li>");
				
			}
			jspWriter.println("</ul>");
		} catch (IOException e) {
			Log.error(this, e.getMessage());
		}

		return 0;
	}

    public CommonBean getMenus() {
        return menus;
    }

    public void setMenus(CommonBean menus) {
        this.menus = menus;
    }
	
	

}
