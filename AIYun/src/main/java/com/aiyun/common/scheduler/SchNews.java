package com.aiyun.common.scheduler;
import java.util.Calendar;
import java.util.TimerTask;

import javax.servlet.ServletContext;

/**
 * ��ʱ���չ�����Ϣ
 */
public class SchNews extends TimerTask {
	private static boolean isRunning = false;
	private ServletContext context = null;
	
	public SchNews(ServletContext context) {
		this.context = context;
	}
	
	public void run() {
		Calendar cal = Calendar.getInstance(); 
		if (!isRunning) {
			isRunning = true; 
			
			isRunning = false;
		} 
	} 
}
