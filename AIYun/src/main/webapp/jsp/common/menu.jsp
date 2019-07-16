<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.aiyun.common.vo.CommonBean"%>
<%@ page import="com.aiyun.common.vo.TableBean"%>
<%@ page import="com.aiyun.common.tool.Function"%>
<%@ taglib prefix="aiyun" uri="/WEB-INF/common.tld"%>
<%@ include file="/jsp/common/headerRef.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>AIYun - Menu</title>
</head>
<body>

    <div id="aiyun-wrapper">
        <div id="aiyun-sidebar-stitch" style="cursor:pointer" ></div>
        <div id="aiyun-sidebar-bg" ></div>
        <div id="aiyun-sidebar" >
            <div id="aiyun-searchbox" class="aiyun-inset">
                <form action="http://www.malijuwebshop.com/themes/aiyun-admin/table.html">
                    <input type="text" class="aiyun-search-input" />
                    <input type="submit" class="aiyun-search-submit" />
                </form>
            </div>
            
            <div id="aiyun-navigation" >
                <ul>
                    <li><a href="dashboard.html" class="aiyun-i-24 i-home">Dashboard</a></li>
                    <li><a href="charts.html" class="aiyun-i-24 i-chart">Charts</a></li>
                    <li><a href="calendar.html" class="aiyun-i-24 i-day-calendar">Calendar</a></li>
                    <li><a href="files.html" class="aiyun-i-24 i-file-cabinet">File Manager</a></li>
                    <li class="active"><a href="table.html" class="aiyun-i-24 i-table-1">Table</a></li>
                    <li>
                        <a href="#" class="aiyun-i-24 i-list">Forms</a>
                        <ul>
                            <li><a href="form_layouts.html">Layouts</a></li>
                            <li><a href="form_elements.html">Elements</a></li>
                        </ul>
                    </li>
                    <li><a href="widgets.html" class="aiyun-i-24 i-cog">Widgets</a></li>
                    <li><a href="typography.html" class="aiyun-i-24 i-text-styling">Typography</a></li>
                    <li><a href="grids.html" class="aiyun-i-24 i-blocks-images">Grids &amp; Panels</a></li>
                    <li><a href="gallery.html" class="aiyun-i-24 i-polaroids">Gallery</a></li>
                    <li><a href="error.html" class="aiyun-i-24 i-alert-2">Error Page</a></li>
                    <li>
                        <a href="icons.html" class="aiyun-i-24 i-pacman">
                            Icons <span class="aiyun-nav-tooltip">2000+</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        </div>


</body>
</html>
