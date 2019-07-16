<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="aiyun" uri="/WEB-INF/common.tld"%>
<%@ include file="/jsp/common/headerRef.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<body>


    <div id="aiyun-header" class="clearfix">
        <div id="aiyun-logo-container">
            <div id="aiyun-logo-wrap">
                
                
            </div>
        </div>
        
        <div id="aiyun-user-tools" class="clearfix">
            <div id="aiyun-user-notif" class="aiyun-dropdown-menu">
                <a href="#" class="aiyun-i-24 i-alert-2 aiyun-dropdown-trigger">Notifications</a>
                <span class="aiyun-dropdown-notif">35</span>
                <div class="aiyun-dropdown-box">
                    <div class="aiyun-dropdown-content">
                        <ul class="aiyun-notifications">
                            <li class="read">
                                <a href="#">
                                    <span class="message">
                                        Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                    </span>
                                    <span class="time">
                                        January 21, 2012
                                    </span>
                                </a>
                            </li>
                            <li class="read">
                                <a href="#">
                                    <span class="message">
                                        Lorem ipsum dolor sit amet
                                    </span>
                                    <span class="time">
                                        January 21, 2012
                                    </span>
                                </a>
                            </li>
                            <li class="unread">
                                <a href="#">
                                    <span class="message">
                                        Lorem ipsum dolor sit amet
                                    </span>
                                    <span class="time">
                                        January 21, 2012
                                    </span>
                                </a>
                            </li>
                            <li class="unread">
                                <a href="#">
                                    <span class="message">
                                        Lorem ipsum dolor sit amet
                                    </span>
                                    <span class="time">
                                        January 21, 2012
                                    </span>
                                </a>
                            </li>
                        </ul>
                        <div class="aiyun-dropdown-viewall">
                            <a href="#">View All Notifications</a>
                        </div>
                    </div>
                </div>
            </div>
            <div id="aiyun-user-message" class="aiyun-dropdown-menu">
                <a href="#" class="aiyun-i-24 i-message aiyun-dropdown-trigger">Messages</a>
                <span class="aiyun-dropdown-notif">35</span>
                <div class="aiyun-dropdown-box">
                    <div class="aiyun-dropdown-content">
                        <ul class="aiyun-messages">
                            <li class="read">
                                <a href="#">
                                    <span class="sender">John Doe</span>
                                    <span class="message">
                                        Lorem ipsum dolor sit amet consectetur adipiscing elit, et al commore
                                    </span>
                                    <span class="time">
                                        January 21, 2012
                                    </span>
                                </a>
                            </li>
                            <li class="read">
                                <a href="#">
                                    <span class="sender">John Doe</span>
                                    <span class="message">
                                        Lorem ipsum dolor sit amet
                                    </span>
                                    <span class="time">
                                        January 21, 2012
                                    </span>
                                </a>
                            </li>
                            <li class="unread">
                                <a href="#">
                                    <span class="sender">John Doe</span>
                                    <span class="message">
                                        Lorem ipsum dolor sit amet
                                    </span>
                                    <span class="time">
                                        January 21, 2012
                                    </span>
                                </a>
                            </li>
                            <li class="unread">
                                <a href="#">
                                    <span class="sender">John Doe</span>
                                    <span class="message">
                                        Lorem ipsum dolor sit amet
                                    </span>
                                    <span class="time">
                                        January 21, 2012
                                    </span>
                                </a>
                            </li>
                        </ul>
                        <div class="aiyun-dropdown-viewall">
                            <a href="#">View All Messages</a>
                        </div>
                    </div>
                </div>
            </div>
            <div id="aiyun-user-info" class="aiyun-inset">
                <div id="aiyun-user-photo">
                    <img src="<%=basePath%>/example/profile.jpg" alt="User Photo" />
                </div>
                <div id="aiyun-user-functions">
                    <div id="aiyun-username">
                        Hello, John Doe
                    </div>
                    <ul>
                        <li><a href="#">Profile</a></li>
                        <li><a href="#">Change Password</a></li>
                        <li><a href="index-2.html">Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    
</body>
</html>
