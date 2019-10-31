package com.aiyun.common.util;

import javax.servlet.http.HttpSession;
//import javax.servlet.jsp.PageContext;

import com.aiyun.common.cache.CacheManager;
import com.aiyun.common.vo.CommonBean;

public class XmlTool {

//	public static CommonBean getHeadDefination(PageContext context) {
//		HttpSession session = context.getSession();
//		CommonBean cbUser = (CommonBean)session.getAttribute("_userinfo");
//		if (cbUser==null) 
//			return new CommonBean();
//		String userid = cbUser.getValue("id");
//		
//		String key = "sys-other-headdefination_" + userid;
//		CommonBean head = (CommonBean) CacheManager.get(key);
//		
//		if (head == null) {
//			try {
//				CommonBean cbBusInfo = new com.paink.module.pub.bean.InfoBean().getBusInfo(userid);
//				head = new CommonBean();
//				String[] headColumn = new String[] { "id", "title", "url", "llink", "items" };
//				head.setColNames(headColumn);
//
//				CommonBean item = new CommonBean();
//				item.setColNames(new String[] { "title", "url" });
//				
//				
//				for (int i=0; i<cbBusInfo.getRowNum(); i++) {
//					if (head.getRowNum()==0 || !head.getCellStr(head.getRowNum()-1,"id").equals(cbBusInfo.getCellStr(i,"m_id"))) {
//						head.addValue("id", cbBusInfo.getCellStr(i,"m_id"));
//						head.addValue("title", cbBusInfo.getCellStr(i,"m_sname"));
//						head.addValue("llink", cbBusInfo.getCellStr(i,"llink"));
//						head.addValue("url", cbBusInfo.getCellStr(i,"m_url"));
//						
//						item = new CommonBean();
//						item.setColNames(new String[] { "title", "url" });
//						
//						head.addObject("items", item);
//					}
//					if (cbBusInfo.getCellStr(i,"b_id")!=null && !cbBusInfo.getCellStr(i,"b_id").equals("")) {
//						item.addValue("title", cbBusInfo.getCellStr(i,"b_sname"));
//						item.addValue("url", cbBusInfo.getCellStr(i,"b_url"));
//					}
//				}
//				CacheManager.put(key, head);
//			} catch (Exception e) {
//				Log.error(XmlTool.class, "����ҵ�񵼺�ʱ�����������\n" + e.getMessage());
//			}
//		}
//		return head;
//	}
}
