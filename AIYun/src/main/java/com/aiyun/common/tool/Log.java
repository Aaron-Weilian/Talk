package com.aiyun.common.tool;

import org.apache.log4j.Logger;

public class Log {

	private Log() {
	}

	public static void info(Object oo, String sMsg) {
		logger = Logger.getLogger(oo.getClass().getName());
		logger.info(sMsg);
	}

	public static void debug(Object oo, String sMsg) {
		logger = Logger.getLogger(oo.getClass().getName());
		logger.debug(sMsg);
	}

	public static void error(Object oo, String sMsg) {
		logger = Logger.getLogger(oo.getClass().getName());
		logger.error(sMsg);
	}

	public static void warn(Object oo, String sMsg) {
		logger = Logger.getLogger(oo.getClass().getName());
		logger.warn(sMsg);
	}

	private static Logger logger = null;

}