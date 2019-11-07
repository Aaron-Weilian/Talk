<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.aiyun.common.vo.CommonBean"%>
<%@ page import="com.aiyun.common.taglib.model.TableModel"%>
<%@ page import="com.aiyun.common.tool.Function"%>
<%@ taglib prefix="aiyun" uri="/WEB-INF/common.tld"%>
<%@ include file="/jsp/common/headerRef.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>AIYun - Table</title>
</head>
<body>

<%
    TableModel cpBean = (TableModel)request.getAttribute("ok");
%>

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
                        <span class="aiyun-i-24 i-table-1">Default Data Table</span>
                    </div>
                    <div class="aiyun-panel-body">
                        <aiyun:TableTag tb="<%=cpBean%>" width="100%" height=""/>
                    </div>
                </div>

                
            </div>
            <jsp:include page="/jsp/common/footer.jsp" flush="true"/>
            
        </div>
    </div>


</body>
</html>
