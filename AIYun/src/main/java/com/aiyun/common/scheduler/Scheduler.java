package com.aiyun.common.scheduler;
import java.util.Calendar;
import java.util.TimerTask;

import javax.servlet.ServletContext;

//import com.paink.module.pub.bean.AuditBus;

/**
 * 
 */

public class Scheduler extends TimerTask {
	private static boolean isRunning = false;
	private ServletContext context = null;
	
	public Scheduler(ServletContext context) {
		this.context = context;
	}
	
	public void run() {
		Calendar cal = Calendar.getInstance(); 
		if (!isRunning) {
			isRunning = true; 
			
//			AuditBus ab = AuditBus.getInstance();
			try {
				//ab.autoAuditBus();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			isRunning = false;
		} 
	} 
}
