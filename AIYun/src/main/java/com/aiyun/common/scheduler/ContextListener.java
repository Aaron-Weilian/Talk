package com.aiyun.common.scheduler;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
	private java.util.Timer timer = new java.util.Timer(true);
	
	/* ���� Javadoc��
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		//event.getServletContext().log("������ʱ����");
		timer.schedule(new Scheduler(event.getServletContext()), 1000, 6*60*60*1000);
	}

	/* ���� Javadoc��
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
		//event.getServletContext().log("���ٶ�ʱ����");
		timer.cancel();
	}
}
