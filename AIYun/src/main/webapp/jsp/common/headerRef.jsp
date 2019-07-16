<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="aiyun" uri="/WEB-INF/common.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!-- Required Stylesheets -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/text.css" media="screen" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/fonts/ptsans/stylesheet.css" media="screen" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/fluid.css" media="screen" />

<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/aiyun.style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/icons/icons.css" media="screen" />

<!-- Demo and Plugin Stylesheets -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/demo.css" media="screen" />

<link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/colorpicker/colorpicker.css" media="screen" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/jimgareaselect/css/imgareaselect-default.css" media="screen" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/fullcalendar/fullcalendar.css" media="screen" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/fullcalendar/fullcalendar.print.css" media="print" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/tipsy/tipsy.css" media="screen" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/sourcerer/Sourcerer-1.2.css" media="screen" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/jgrowl/jquery.jgrowl.css" media="screen" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/spinner/spinner.css" media="screen" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/jui/jquery.ui.css" media="screen" />

<!-- Theme Stylesheet -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/aiyun.theme.css" media="screen" />

<!-- JavaScript Plugins -->

<script type="text/javascript" src="<%=basePath%>/js/jquery-1.7.1.min.js"></script>

<script type="text/javascript" src="<%=basePath%>/plugins/jimgareaselect/jquery.imgareaselect.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery.dualListBox-1.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/jgrowl/jquery.jgrowl.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery.filestyle.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/fullcalendar/fullcalendar.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery.dataTables.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=basePath%>/plugins/flot/excanvas.min.js"></script>
<![endif]-->
<script type="text/javascript" src="<%=basePath%>/plugins/flot/jquery.flot.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/flot/jquery.flot.pie.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/flot/jquery.flot.stack.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/flot/jquery.flot.resize.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/colorpicker/colorpicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/tipsy/jquery.tipsy.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/sourcerer/Sourcerer-1.2.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery.placeholder.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/jquery.mousewheel.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/spinner/ui.spinner.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-ui.js"></script>

<script type="text/javascript" src="<%=basePath%>/js/aiyun.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/demo.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/themer.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("div#aiyun-login .aiyun-login-back").mouseover(function(event) {
			$(this).find("a").animate({'left':0})
		}).mouseout(function(event) {
			$(this).find("a").animate({'left':70})
		});
	});
</script>



</head>


</html>
