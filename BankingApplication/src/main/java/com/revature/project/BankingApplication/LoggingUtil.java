package com.revature.project.BankingApplication;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class LoggingUtil {
	
	private static Logger log = Logger.getRootLogger();
	
	public static void logFatal(String s){
		log.fatal(s);
	}
	public static void logError(String s){
		log.error(s);
	}
	public static void logWarn(String s){
		log.warn(s);
	}
	public static void logInfo(String s){
		log.info(s);
	}
	public static void logDebug(String s){
		log.debug(s);
	}
	public static void logTrace(String s){
		log.trace(s);
	}
	public static void logAccount(Account s){
		System.out.println(s);
	}
	public static void logUser(User s){
		System.out.println(s);
	}
	
	public static void main(String[] jc){
		BasicConfigurator.configure();
		LoggingUtil.logFatal("Test");
;
	}
}
