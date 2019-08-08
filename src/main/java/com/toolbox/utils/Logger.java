package com.toolbox.utils;

import java.net.InetAddress;

import com.toolbox.AppContext;

public class Logger {

	static ThreadLocal threadLocal = new ThreadLocal();


	public static void removeAction() {
		threadLocal.remove();
	}

	/**
	 * Get logger
	 * 
	 * @auther lihu
	 * @see
	 */
	private static org.apache.log4j.Logger getLogger() {
		org.apache.log4j.Logger logger = org.apache.log4j.Logger
				.getLogger("info");
	
		return logger;
	}

	private static org.apache.log4j.Logger getErrorLogger() {
		org.apache.log4j.Logger logger = org.apache.log4j.Logger
				.getLogger("error");
		return logger;
	}

	public static String getPreMessage() {
		String action = (String) AppContext.get("action");
		String userId = (String) AppContext.get("userId");
		String msg = "";
		if (action != null && userId != null)
			msg += "[" + userId + "]";
		else
			msg += "[NOUSER]";

		if (action != null)
			msg += "<" + action + "> ";
		else
			msg += "<NOACTION> ";

		return getHostName() + msg;
	}

	public static String getHostName() {
		try {
			return "[" + InetAddress.getLocalHost().getHostName() + "]";
		} catch (Exception ex) {
			return "";
		}
	}

	private static Object formatMessage(Object message) {
		String msg = getPreMessage();

		if (message != null
				&& (message.toString().indexOf("password") != -1 || message
						.toString().indexOf("Password") != -1)) {
			message = message.toString().replace("password", "drowssap");
		}			

		return msg += maskMessage(message);
	}

	private static Object maskMessage(Object message) {
		return message;
	}

	public static void debug(Object message) {
		getLogger().debug(formatMessage(message));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.service.logging.LoggingService#debug(java.lang.Object,
	 * java.lang.Exception)
	 */
	public static void debug(Object message, Throwable exception) {
		getLogger().debug(formatMessage(message), exception);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.service.logging.LoggingService#info(java.lang.Object)
	 */
	public static void info(Object message) {
		getLogger().info(formatMessage(message));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.service.logging.LoggingService#info(java.lang.Object,
	 * java.lang.Exception)
	 */
	public static void info(Object message, Throwable exception) {
		getLogger().info(formatMessage(message), exception);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.service.logging.LoggingService#warn(java.lang.Object)
	 */
	public static void warn(Object message) {
		getLogger().warn(formatMessage(message));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.service.logging.LoggingService#warn(java.lang.Object,
	 * java.lang.Exception)
	 */
	public static void warn(Object message, Throwable exception) {
		getLogger().warn(formatMessage(message), exception);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.service.logging.LoggingService#error(java.lang.Object)
	 */
	public static void error(Object message) {
		if (message instanceof Throwable)
			error(((Throwable) message).getMessage(), (Throwable) message);
		else
			getErrorLogger().error(formatMessage(message));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.service.logging.LoggingService#error(java.lang.Object,
	 * java.lang.Exception)
	 */
	public static void error(Object message, Throwable exception) {
		Object formatMessage = formatMessage(message);

		getErrorLogger().error(formatMessage, exception);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.service.logging.LoggingService#fatal(java.lang.Object)
	 */
	public static void fatal(Object message) {
		if (message instanceof Throwable)
			fatal(((Throwable) message).getMessage(), (Throwable) message);
		else
			getErrorLogger().fatal(formatMessage(message));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.service.logging.LoggingService#fatal(java.lang.Object,
	 * java.lang.Exception)
	 */
	public static void fatal(Object message, Throwable exception) {
		Object formatMessage = formatMessage(message);

		getErrorLogger().fatal(formatMessage, exception);

//		BaseAction action = getAction();
//		String request = "";
//		if (action != null) {
//			request = action.getRequest().replace("\n", "\r\n\t") + "\r\n\t";
//		}

	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.service.logging.LoggingService#info(java.lang.Object)
	 */
	public static boolean isDebugEnabled() {
		return getLogger().isDebugEnabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.service.logging.LoggingService#info(java.lang.Object)
	 */
	public static boolean isInfoEnabled() {
		return getLogger().isInfoEnabled();
	}

	public static void main(String[] args) {
		System.out.println("测");
	}
}
