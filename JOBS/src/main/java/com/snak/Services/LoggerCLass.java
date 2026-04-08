package com.snak.Services;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerCLass {
	static Logger logger = LoggerFactory.getLogger(LoggerCLass.class);

	public  void printStackTraceToLogs(String className, String methodName, Exception e)
	{
		try
		{
//			Writer writer = new StringWriter();
//			PrintWriter print = new PrintWriter(writer);
//			e.printStackTrace(print);

			   logger.info("{}::{}:: Exception occurred  {}", className, methodName, e.toString());
			//logger.info(className+"::"+methodName+":: Error :: > " + writer.toString());

//			print = null;	
//			writer= null;
		}
		catch(Exception f)
		{
			f.printStackTrace();
		}
	}	
}
