/**
 * 
 */
package com.aiyun.sys.permission.bean;

import com.aiyun.common.po.DBUtil;
import com.aiyun.common.po.DataBaseObject;
import com.aiyun.common.po.IBusnessObject;
import com.aiyun.common.tool.Function;
import com.aiyun.common.tool.Log;
import com.aiyun.common.vo.CommonBean;

/**
 * @author ZT
 *
 */
public class PermissionBean extends DBUtil implements IBusnessObject {

    public CommonBean getPermissionList(String userID) {
        try {
            String strSql = "select p.* from u_module m , u_permission p, u_user_module um where m.moduleName = p.module and m.moduleId = um.mid and um.uid="+userID+"";
            DataBaseObject dbo = getDataBaseObject();
            CommonBean cb = dbo.getData(strSql);
            cb = Function.formatBean(cb);
            return cb;
        } catch (Exception e) {
            Log.error(this, "获取权限列表失败：" + e.getMessage());
            getErrMsgBean().addCommonMessage("获取权限列表失败：" + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            rollback();
        }
    }
}
