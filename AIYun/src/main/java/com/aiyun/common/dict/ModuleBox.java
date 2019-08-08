package com.aiyun.common.dict;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import com.aiyun.common.bo.DBUtil;

public class ModuleBox extends DBUtil {
    
    public enum ModuleType
    {
        TST,SYS,ORDER;
    }
    
    public enum ModuleValue
    {
        TST("Test","aiyun-i-24 i-cog"),SYS("Setting","aiyun-i-24 i-cog"), ORDER("Order","aiyun-i-24 i-cog");
        
        private final String name;
        private final String linkClass;
        
        ModuleValue(String name,String linkClass){
            this.name = name;
            this.linkClass = linkClass;
        }

        public String getName() {
            return name;
        }

        public String getLinkClass() {
            return linkClass;
        }
        
        
        
    }
    
    public static Map<String,ModuleValue> moduleMap = new HashMap<String,ModuleValue>();
    
    static {
        moduleMap.put("TST",ModuleValue.TST);
        moduleMap.put("SYS",ModuleValue.SYS);
        moduleMap.put("ORDER",ModuleValue.ORDER);
    }
    

    public static void main(String[] args) {
        System.out.println(moduleMap.get(ModuleType.ORDER));
    }
	
}

interface EnumCommon {
    public String getkey();
    public String getValue();
}