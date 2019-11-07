/**
 * 
 */
package com.aiyun.sys.permission.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.dict.Constants;
import com.aiyun.common.taglib.model.TableModel;
import com.aiyun.common.vo.CommonBean;
import com.aiyun.sys.permission.bean.PermissionBean;

/**
 * @author ZT
 *
 */
public class PermissionListRequestHandler extends RequestHandlerSupport {
    
    @Override
    public void processRequest(HttpServletRequest request) throws CommonException {
        PermissionBean permissionBean = new PermissionBean();
        
        CommonBean permissionList = permissionBean.getPermissionList("1");
        
        TableModel tb = new TableModel(permissionList);
        tb.setSName(permissionList.getColNames());
        tb.setChName(new String[] { "T1", "T2","T3" ,"T4" ,"CSS grade","S"});
        tb.setTableClass("aiyun-datatable aiyun-table");
        for(int i = 0; i < permissionList.getRowNum(); i++) {
            Map trClass = new HashMap();
            trClass.put(i, "gradeA");
            tb.setRowClass(trClass);
            for(int j = 0; j<permissionList.getColumnNum(); j++) {
                Map tdClass = new HashMap();
                tdClass.put(i, "center");
                tb.setColClass(tdClass);
            }
        }
        setSessionAttribute(request,"permissionList",tb);
        
    }

}
