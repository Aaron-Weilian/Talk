<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.aiyun.common.vo.CommonBean"%>
<%@ page import="com.aiyun.common.taglib.model.TableModel"%>
<%@ page import="com.aiyun.common.tool.Function"%>
<%@ taglib prefix="aiyun" uri="/WEB-INF/common.tld"%>


    <jsp:include page="/jsp/common/headerInfo.jsp" flush="true"/>

    <div id="aiyun-wrapper">
    <!-- Necessary markup, do not remove -->
        <div id="aiyun-sidebar-stitch" style="cursor:pointer" ></div>
        <div id="aiyun-sidebar-bg" style="display:block"></div>
        
        <!-- Sidebar Wrapper -->
        <div id="aiyun-sidebar">
        <jsp:include page="/jsp/common/menu.jsp" flush="true"/>
        </div>
        
        <!-- Container Wrapper -->
        <div id="aiyun-container" class="clearfix">
            <div class="container">
            
                <div class="aiyun-panel grid_8">
                    <div class="aiyun-panel-header">
                        <span class="aiyun-i-24 i-table-1">用户列表</span>
                    </div>
                    <div class="aiyun-panel-body">
                    <div class="aiyun-panel-toolbar top clearfix">
                            <ul>
                                <li><a href="/AIYun/Prod/userInfo" class="aiyun-ic-16 ic-add">Accept</a></li>
                                <li><a href="#" class="aiyun-ic-16 ic-cross">Delete</a></li>
                                <li><a href="#" class="aiyun-ic-16 ic-arrow-refresh">Refresh</a></li>
                            </ul>
                        </div>
                        <aiyun:TableTag tb="${userList}" width="100%" height=""/>
                    </div>
                </div>
            </div>
            <jsp:include page="/jsp/common/footer.jsp" flush="true"/>
        </div>
    </div>
</body>
</html>
