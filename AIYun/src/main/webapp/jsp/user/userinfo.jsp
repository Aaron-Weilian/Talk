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
                        <span class="aiyun-i-24 i-pencil">User Info</span>
                    </div>
                    <div class="aiyun-panel-body">
                        <form class="aiyun-form" action="http://www.malijuwebshop.com/themes/aiyun-admin/form_elements.html">
                            <div class="aiyun-form-inline">
                                <div class="aiyun-form-row">
                                    <label>用户名</label>
                                    <div class="aiyun-form-item large">
                                        <input type="text" class="aiyun-textinput" placeholder="您的用户名" name="username" />
                                    </div>
                                </div>
                                
                                <div class="aiyun-form-row">
                                    <label>密码</label>
                                    <div class="aiyun-form-item large">
                                        <input type="password" class="aiyun-textinput" placeholder="您的密码" name="pswd" />
                                    </div>
                                </div>
                                
                                
                                <div class="aiyun-form-row">
                                    <label>邮箱</label>
                                    <div class="aiyun-form-item large">
                                        <input type="text" class="aiyun-textinput" placeholder="您的邮箱" name="email" />
                                    </div>
                                </div>
                                
                                <div class="aiyun-form-row">
                                    <label>用户组</label>
                                    <div class="aiyun-form-item large">
                                        <div class="aiyun-dualbox clearfix">
                                            <div class="aiyun-dualbox-col1">
                                                <div class="aiyun-dualbox-filter clearfix">
                                                    <input type="text" id="box1Filter" class="aiyun-textinput" placeholder="Filter" />
                                                    <button type="button" id="box1Clear">X</button>
                                                </div>

                                                <select id="box1View" multiple="multiple" size="15">
                                                    <option value="501649">计划</option>
                                                    <option value="500004">采购</option>
                                                    <option value="500336">仓库</option>
                                                    <option value="500336">物流</option>
                                                    <option value="500336">市场</option>
                                                    <option value="500336">财务</option>
                                                </select>
                                                
                                            </div>
                                            <div class="aiyun-dualbox-col2">
                                                <button id="to2" type="button">&gt;</button>
                                                <button id="allTo2" type="button">&gt;&gt;</button>
                                                <div class="clear"></div>
                                                <button id="allTo1" type="button">&lt;&lt;</button>
                                                <button id="to1" type="button">&lt;</button>
                                            </div>
                                            <div class="aiyun-dualbox-col3">
                                                <div class="aiyun-dualbox-filter clearfix">
                                                    <input type="text" id="box2Filter" class="aiyun-textinput" placeholder="Filter" />
                                                    <button type="button" id="box2Clear">X</button>
                                                </div>

                                                <select id="box2View" multiple="multiple" size="15"></select>
                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="aiyun-button-row">
                                    <input type="submit" class="aiyun-button red" />
                                </div>
                            </div>
                        </form>
                    </div>      
                </div>
                
                
            
                
            </div>
            <jsp:include page="/jsp/common/footer.jsp" flush="true"/>
        </div>
    </div>
</body>
</html>
