<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ page import="com.aiyun.common.vo.CommonBean"%>
<%@ taglib prefix="aiyun" uri="/WEB-INF/common.tld"%>


<!-- Search Box -->
<div id="aiyun-searchbox" class="aiyun-inset">
	<form action="http://www.malijuwebshop.com/themes/aiyun-admin/table.html">
		<input type="text" class="aiyun-search-input" /> <input type="submit" class="aiyun-search-submit" />
	</form>
</div>

<!-- Main Navigation -->
<div id="aiyun-navigation">
	<aiyun:MenuTag menus="${USERMENUS}" />
</div>
<!-- End Navigation -->

