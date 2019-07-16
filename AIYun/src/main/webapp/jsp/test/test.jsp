<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.aiyun.common.vo.CommonBean"%>
<%@ page import="com.aiyun.common.vo.TableBean"%>
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
        TableBean cpBean = (TableBean)request.getAttribute("ok");
%>

    <div id="aiyun-wrapper">
    
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
            
            <jsp:include page="/jsp/common/footer.jsp" />
        </div>
        
    </div>


</body>
</html>
