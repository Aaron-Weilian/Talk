package com.aiyun.business;


import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

//import com.paink.module.trade.trade.bean.TradeBean;
import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.control.web.RequestHandlerSupport;
import com.aiyun.common.taglib.model.TableModel;
import com.aiyun.common.vo.CommonBean;
/**
 * @author Administrator
 *
 */
public class testRequestHandler extends RequestHandlerSupport {

	public void processRequest(HttpServletRequest request) throws CommonException {

		CommonBean testBean = new CommonBean();
		testBean.setColNames(new String[] { "Rendering engine", "Browser","Platform(s)","Engine version" ,"CSS grade"});
		
		
		Vector vecRow1 = new Vector();
		
		vecRow1.add("Rendering engine");
		vecRow1.add("Browser");
		vecRow1.add("Platform(s)");
		vecRow1.add("Engine version");
		vecRow1.add("CSS grade");
		
		testBean.addRow(vecRow1);
		
		
		Vector vecRow2 = new Vector();
		vecRow2.add("Trident");
		vecRow2.add("Internet Explorer 4.0");
		vecRow2.add("Win 95+");
		vecRow2.add("4");
		vecRow2.add("X");
		
		testBean.addRow(vecRow2);
		
		
		TableModel tb = new TableModel(testBean);
		tb.setSName(testBean.getColNames());
		tb.setChName(new String[] { "T1", "T2","T3" ,"T4" ,"CSS grade"});
		tb.setTableClass("aiyun-datatable aiyun-table");
		for(int i = 0; i < testBean.getRowNum(); i++) {
			Map trClass = new HashMap();
			trClass.put(i, "gradeA");
			tb.setRowClass(trClass);
			for(int j = 0; j<testBean.getColumnNum(); j++) {
				Map tdClass = new HashMap();
				tdClass.put(i, "center");
				tb.setColClass(tdClass);
			}
		}
		
		
		
		request.setAttribute("ok", tb);

	}
}
