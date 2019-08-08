<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.aiyun.common.vo.CommonBean"%>
<%@ page import="com.aiyun.common.vo.TableBean"%>
<%@ page import="com.aiyun.common.util.Function"%>
<%@ taglib prefix="aiyun" uri="/WEB-INF/common.tld"%>
<%@ include file="/jsp/common/headerRef.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<frameset border=0 framespacing=0 rows="65, *" frameborder=no>
<frame name=header src="<%=path%>/jsp/common/headerInfo.jsp" frameborder=0 noresize scrolling=no>
    <frameset id="frame" cols="206, *">
        <frame name=menu src="<%=path%>/jsp/common/menu.jsp" frameborder=0 noresize scrolling=no   >
        <frame name=main src="<%=path%>/BaseServlet/test" frameborder=0 noresize scrolling=auto>
    </frameset>
</frameset>
<noframes>
    <body >您的浏览器不支持框架！</body>
</noframes>


</html>
