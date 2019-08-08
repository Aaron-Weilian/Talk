<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.aiyun.common.vo.CommonBean"%>
<%@ taglib prefix="aiyun" uri="/WEB-INF/common.tld"%>
<%@ include file="/jsp/common/headerRef.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>AIYun - Menu</title>
</head>
<body>
<%
CommonBean userMenus = (CommonBean)request.getAttribute("userMenus");
%>
        <!-- Search Box -->
            <div id="aiyun-searchbox" class="aiyun-inset">
                <form action="http://www.malijuwebshop.com/themes/aiyun-admin/table.html">
                    <input type="text" class="aiyun-search-input" />
                    <input type="submit" class="aiyun-search-submit" />
                </form>
            </div>
            
            <!-- Main Navigation -->
            <div id="aiyun-navigation" >
                <aiyun:MenuTag menus="<%=userMenus%>" />
            </div>
            <!-- End Navigation -->

</body>
</html>
