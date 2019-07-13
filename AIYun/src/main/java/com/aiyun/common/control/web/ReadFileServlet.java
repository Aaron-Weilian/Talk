/*
 *  �����ݿ��ȡ�ļ���servlet���ļ�
 */
package com.aiyun.common.control.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.paink.module.pub.bean.OperFile;

/**
 * �����ݿ��ȡ�ļ�
 */
public class ReadFileServlet extends HttpServlet {


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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		
//		OperFile rf=new OperFile();
//		try {
//			rf.readBlob(request,id);
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		String filename = (String)request.getAttribute("filename");
//		byte[] buffer=(byte[])request.getAttribute("buffer");
//		response.setContentType("application/octet-stream");
//		
//		response.setHeader("Content-Disposition", "attachment; filename=\""+URLEncoder.encode(filename,"UTF-8") + "\"");
//		
//		ServletOutputStream op = response.getOutputStream();
//		op.write(buffer);
//		op.flush();
//		op.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}
}
