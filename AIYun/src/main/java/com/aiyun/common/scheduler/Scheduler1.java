package com.aiyun.common.scheduler;
import java.util.Calendar;
import java.util.TimerTask;

import javax.servlet.ServletContext;

/**
 * ��ʱ����ҵ�������Ϣ
 */
public class Scheduler1 extends TimerTask {
	private static boolean isRunning = false;
	private ServletContext context = null;
	
	public Scheduler1(ServletContext context) {
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
