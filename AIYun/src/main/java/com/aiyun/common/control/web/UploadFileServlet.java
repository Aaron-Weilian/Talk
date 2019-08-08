package com.aiyun.common.control.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;


import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileItemFactory;
//import org.apache.commons.fileupload.RequestContext;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.aiyun.common.bo.DataBaseObject;
import com.aiyun.common.control.exception.CommonException;
import com.aiyun.common.util.Function;
import com.aiyun.common.util.Log;
import com.aiyun.common.util.Oid;
import com.aiyun.common.vo.CommonBean;
//import com.paink.module.pub.bean.OperFile;
//import com.paink.sys.notice.bean.NoticeBean;
//import com.paink.ywdata.shcy.bean.ShcyBean;

public class UploadFileServlet extends HttpServlet {
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
//		try {
//			CommonBean cbUser = (CommonBean) request.getSession().getAttribute("_userinfo");
//	
//			OperFile noticebean = new OperFile();
//			CommonBean cbAcc = new CommonBean();
//			String objid = request.getParameter("id");
//			String fileurl = request.getParameter("fileurl"); 
//			
//			//Create a factory for disk-based file items
//			DiskFileItemFactory factory = new DiskFileItemFactory();
//
//			//Create a new file upload handler
//			ServletFileUpload upload = new ServletFileUpload(factory);
//			upload.setSizeMax(100*1024*1024);
//			//Parse the request
//			List items = upload.parseRequest(request);
//			//Process the uploaded items
//			Iterator iter = items.iterator();
//	
//			while (iter.hasNext()) {
//				FileItem item = (FileItem) iter.next();
//				if (!item.isFormField()) {
//					cbAcc.addValue("objid", objid);
//					String fname = item.getName();//��ȡ�ϴ��ļ���·�����ļ���
//					cbAcc.addValue("sname", fname.substring(fname.lastIndexOf("\\")+1));
//					
//			    if (fileurl.equals("shcyacc")){
//			    	String id=Oid.getOid();
//			    	String realPath = request.getRealPath("/");	
//			    	String filename=id+".png";
//			    	String trueurl=realPath+"uploadfile/";
//					String filename1=trueurl+filename; //�����ļ��ķ���������·�����ļ���
//					File f = new File(filename1);
//					
//					item.write(f);//д�ļ�
//					ShcyBean shcy= new ShcyBean();	
//					
//						String sql="insert into yw_shcy_mx_pic (id,objid,filename,trueurl) values('"+id+"','"+objid+"','"+filename+"','"+trueurl+"')";
//					shcy.SavePic(sql);
//				
//			    }
//			    	
//			    }else {
//					InputStream sis = item.getInputStream();
//					noticebean.saveFile(cbAcc, sis);
//				}
//			}
//			if (fileurl.equals("shcyacc")){
//			
//				
//				String shcyid = request.getParameter("shcyid");
//							request.setAttribute("id",objid);
//							request.setAttribute("shcyid",shcyid);
//						
//						}
//			fileurl="/BaseServlet/"+fileurl;
//			
//			request.getRequestDispatcher(fileurl).forward(request, response);
//		} catch (Exception e) {
//			e.printStackTrace(System.out);
//			return;
//		} 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}
	public void processRequest(HttpServletRequest request) throws CommonException {
	}

}
