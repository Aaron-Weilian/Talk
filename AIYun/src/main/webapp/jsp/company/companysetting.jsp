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

			<form id="aiyun-validate" class="aiyun-form" action="/AIYun/Prod/AddCompany">
				<div class="aiyun-panel grid_8">
					<div class="aiyun-tabs">

						<ul>
							<li><a href="#tab-1">基础设置</a></li>
							<li><a href="#tab-2">高级设置</a></li>
							<li><a href="#tab-3">Tab 3</a></li>
						</ul>
						<div id="tab-1">
							<div id="aiyun-validate-error" class="aiyun-form-message error" style="display: none;"></div>
							<div class="aiyun-form-inline">
								<div class="aiyun-form-row">
									<label>公司全称</label>
									<div class="aiyun-form-item large">
										<input type="text" name="companyName"
											class="aiyun-textinput required" />
									</div>
								</div>
								<div class="aiyun-form-row">
                                    <label>唯一编码</label>
                                    <div class="aiyun-form-item large">
                                        <input type="text" name="companyCode"
                                            class="aiyun-textinput required" />
                                    </div>
                                </div>
								<div class="aiyun-form-row">
                                    <label>公司简称</label>
                                    <div class="aiyun-form-item large">
                                        <input type="text" name="companyShortName"
                                            class="aiyun-textinput" />
                                    </div>
                                </div>
								<div class="aiyun-form-row">
									<label>公司邮箱</label>
									<div class="aiyun-form-item large">
										<input type="text" name="email"
											class="aiyun-textinput" />
									</div>
								</div>
								<div class="aiyun-form-row">
									<label>Duns</label>
									<div class="aiyun-form-item large">
										<input type="text" name="duns"
											class="aiyun-textinput" />
									</div>
								</div>
								<div class="aiyun-form-row">
									<label>所属国家</label>
									<div class="aiyun-form-item large">
										<select name="country">
                                            <option></option>
                                            <option>中国</option>
                                            <option>日本</option>
                                            <option>俄罗斯</option>
                                            <option>美国</option>
                                        </select>
									</div>
								</div>
								<div class="aiyun-form-row">
									<label>所属行业</label>
									<div class="aiyun-form-item large">
										<select name="busniess">
											<option></option>
											<option>信息服务</option>
											<option>信息设备</option>
											<option>电子商务和零售</option>
											<option>医药生物</option>
										</select>
									</div>
								</div>
							</div>

						</div>

						<div id="tab-2">
							<div id="aiyun-validate-error" class="aiyun-form-message error" style="display: none;"></div>
                            <div class="aiyun-form-inline">
                                <div class="aiyun-form-row">
                                    <label>设置日历</label>
                                    <div class="aiyun-form-item large">
                                        <input type="text" name="reqField"
                                            class="aiyun-textinput" />
                                    </div>
                                </div>
                                <div class="aiyun-form-row">
                                    <label>设置汇率</label>
                                    <div class="aiyun-form-item large">
                                        <input type="text" name="emailField"
                                            class="aiyun-textinput" />
                                    </div>
                                </div>
                                <div class="aiyun-form-row">
                                    <label>Duns</label>
                                    <div class="aiyun-form-item large">
                                        <input type="text" name="urlField"
                                            class="aiyun-textinput" />
                                    </div>
                                </div>
                                <div class="aiyun-form-row">
                                    <label>所属国家</label>
                                    <div class="aiyun-form-item large">
                                        <select name="selectBox">
                                            <option></option>
                                            <option>中国</option>
                                            <option>日本</option>
                                            <option>俄罗斯</option>
                                            <option>美国</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="aiyun-form-row">
                                    <label>所属行业</label>
                                    <div class="aiyun-form-item large">
                                        <select name="selectBox1">
                                            <option></option>
                                            <option>信息服务</option>
                                            <option>信息设备</option>
                                            <option>电子商务和零售</option>
                                            <option>医药生物</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
						</div>

						<div id="tab-3">
							<p>Suspendisse lacinia euismod ligula. Nullam sed est sem,
								nec sodales erat. Phasellus ipsum orci, scelerisque non faucibus
								ac, hendrerit ut massa. Praesent ornare justo non turpis
								convallis sit amet porta urna adipiscing. Donec ac neque non
								risus tristique commodo non et neque. Pellentesque habitant
								morbi tristique senectus et netus et malesuada fames ac turpis
								egestas. Mauris tincidunt augue vitae risus lacinia sed tempor
								ligula dapibus. Proin et turpis lacus, eget convallis risus.</p>
						</div>

            <div class="aiyun-button-row">
                <input type="submit" class="aiyun-button red" />
            </div>
					</div>

				</div>
			</form>
			
		</div>
		<jsp:include page="/jsp/common/footer.jsp" flush="true"/>
        </div>
    </div>
</body>
</html>
