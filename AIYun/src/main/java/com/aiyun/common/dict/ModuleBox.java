package com.aiyun.common.dict;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import com.aiyun.common.po.DBUtil;

public class ModuleBox extends DBUtil {
    
    public enum ModuleType
    {
        DASHBOARD,SYS,ORDER,PURCHASE,INVENTORY,MATERIAL,FORWARD,VENDOR,CUSTOMER,REPORT,PLAN,COMPANY;
    }
    
    public enum ModuleValue
    {
        DASHBOARD("Dashboard","aiyun-i-24 i-home",0),
        SYS("System Setting","aiyun-i-24 i-cog",1), 
        ORDER("Sales","aiyun-i-24 i-shopping-cart-2",2),
        PURCHASE("Purchase","aiyun-i-24 i-ladys-purse",3),
        INVENTORY("Inventory","aiyun-i-24 i-table-1",4),
        MATERIAL("Material","aiyun-i-24 i-companies",5),
        FORWARD("Forwarder","aiyun-i-24 i-truck-2",6),
        VENDOR("Vendor","aiyun-i-24 i-truck-2",7),
        CUSTOMER("Customer","aiyun-i-24 i-truck-2",8),
        REPORT("Report","aiyun-i-24 i-truck-2",9),
        PLAN("Plan","aiyun-i-24 i-truck-2",10),
        COMPANY("Company","aiyun-i-24 i-cog",11)
        ;
        
        private final String name;
        private final String linkClass;
        private final int seq;
        
        ModuleValue(String name,String linkClass,int seq){
            this.name = name;
            this.linkClass = linkClass;
            this.seq = seq;
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
        moduleMap.put("DASHBOARD",ModuleValue.DASHBOARD);
        moduleMap.put("SYS",ModuleValue.SYS);
        moduleMap.put("ORDER",ModuleValue.ORDER);
        moduleMap.put("PURCHASE",ModuleValue.PURCHASE);
        moduleMap.put("INVENTORY",ModuleValue.INVENTORY);
        moduleMap.put("MATERIAL",ModuleValue.MATERIAL);
        moduleMap.put("FORWARD",ModuleValue.FORWARD);
        moduleMap.put("VENDOR",ModuleValue.VENDOR);
        moduleMap.put("CUSTOMER",ModuleValue.CUSTOMER);
        moduleMap.put("REPORT",ModuleValue.REPORT);
        moduleMap.put("PLAN",ModuleValue.PLAN);
        moduleMap.put("COMPANY",ModuleValue.COMPANY);
    }
    

    public static void main(String[] args) {
        System.out.println(moduleMap.get(ModuleType.ORDER));
    }
	
}

interface EnumCommon {
    public String getkey();
    public String getValue();
}