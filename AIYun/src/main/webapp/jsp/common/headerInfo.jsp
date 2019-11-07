<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="aiyun" uri="/WEB-INF/common.tld"%>
<%@ include file="/jsp/common/headerRef.jsp"%>

<div id="aiyun-themer">
    <div id="aiyun-themer-hide"></div>
    <div id="aiyun-themer-content">
        <div class="aiyun-themer-section">
            <label for="aiyun-theme-presets">Presets</label> 
            <select id="aiyun-theme-presets"></select>
        </div>
        <div class="aiyun-themer-separator"></div>
        <div class="aiyun-themer-section">
            <ul>
                <li><span>Base Color</span>
                    <div id="aiyun-base-cp" class="aiyun-cp-trigger"></div></li>
                <li><span>Text Color</span>
                    <div id="aiyun-text-cp" class="aiyun-cp-trigger"></div></li>
                <li><span>Text Glow Color</span>
                    <div id="aiyun-textglow-cp" class="aiyun-cp-trigger"></div></li>
            </ul>
        </div>
        <div class="aiyun-themer-separator"></div>
        <div class="aiyun-themer-section">
            <ul>
                <li><span>Text Glow Opacity</span>
                    <div id="aiyun-textglow-op"></div></li>
            </ul>
        </div>
        <div class="aiyun-themer-separator"></div>
        <div class="aiyun-themer-section">
            <button class="aiyun-button red small" id="aiyun-themer-getcss">Get CSS</button>
        </div>
    </div>
    <div id="aiyun-themer-css-dialog">
        <div class="aiyun-form">
            <div class="aiyun-form-row" style="padding: 0;">
                <div class="aiyun-form-item">
                    <textarea cols="auto" rows="auto" readonly="readonly"></textarea>
                </div>
            </div>
        </div>
    </div>
</div>

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
    

