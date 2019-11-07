package com.aiyun.common.taglib.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aiyun.common.tool.Function;
import com.aiyun.common.vo.CommonBean;

public class MenuModel {

	private CommonBean dataBean = null;
	private int rowNum = 0;
	private static String MODULE = "module";
	private static String HREF = "url";
	private static String LINKCLASS = "class";
	private static String DISPLAYNAME = "name";
	
	public MenuModel(CommonBean dataBean) {
		this.dataBean = dataBean;
		this.rowNum = dataBean.getRowNum();
	}

	public String getModule(int row) {
        return Function.trimString(this.dataBean.getCellStr(row, MODULE));
    }
	
    public String getHref(int row) {
        return Function.trimString(this.dataBean.getCellStr(row, HREF));
    }

    public String getLinkClass(int row) {
        return Function.trimString(this.dataBean.getCellStr(row, LINKCLASS));
    }

    public String getDisplayName(int row) {
        return Function.trimString(this.dataBean.getCellStr(row, DISPLAYNAME));
    }

    public Map<String, List<MenuInfo>> getMenuMap() {
        Map<String, List<MenuInfo>> menuMap = new HashMap<String, List<MenuInfo>>();

        for (int row = 0; row < rowNum; row++) {
            String key = Function.trimString(this.dataBean.getCellStr(row, MODULE));
            List menuList = menuMap.get(key);
            if (menuList == null) {
                menuList = new ArrayList();
                menuMap.put(key, menuList);
            }
            menuList.add(new MenuInfo(getHref(row),getLinkClass(row),getDisplayName(row)));
            
        }

        return menuMap;
    }
    
    public int getRowNum() {
        return rowNum;
    }

    public class MenuInfo {
        
        private String href;
        private String linkClass;
        private String displayName;
        
        MenuInfo(String href,String linkClass,String displayName) {
            this.href = href;
            this.linkClass = linkClass;
            this.displayName = displayName;
        }
        
        public String getHref() {
            return href;
        }
        public void setHref(String href) {
            this.href = href;
        }
        public String getLinkClass() {
            return linkClass;
        }
        public void setLinkClass(String linkClass) {
            this.linkClass = linkClass;
        }
        public String getDisplayName() {
            return displayName;
        }
        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
        
        
        
        
    }
}
