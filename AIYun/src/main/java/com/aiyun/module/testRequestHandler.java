package com.aiyun.module;


import javax.servlet.http.HttpServletRequest;

//import com.paink.module.trade.trade.bean.TradeBean;
import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.vo.CommonBean;
/**
 * @author Administrator
 *
 */
public class testRequestHandler extends RequestHandlerSupport {

	public void processRequest(HttpServletRequest request) throws CommonException {

		CommonBean testBean = new CommonBean();
		
		testBean.addColumn("test", "test");
		
		request.setAttribute("ok", testBean);

	}
}
