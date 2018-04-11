package org.jboss.integration.examples;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class NagiosProcessor implements Processor{

	private static int OK=0;
	private static int WARN=1;
	private static int CRITICAL=2;
	private static int UNKNOWN=3;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		Logger logger = Logger.getLogger(this.getClass().getName());
		
	
		//[<timestamp>] PROCESS_SERVICE_CHECK_RESULT;<host_name>;<svc_description>;<return_code>;<plugin_output>
		
		Date d = Calendar.getInstance().getTime();
		String hostname = "FUSE";
		String service = "melAPI";
		int code = OK;
		String message = "Message from Fuse to nagios";
		
		String nagios = "["+Long.toString(d.getTime()) + "] PROCESS_SERVICE_CHECK_RESULT;"+hostname+";"+service+";"+Integer.toString(code)+";"+message;
		
		
		Message m = exchange.getIn();
		m.setBody(nagios);
		exchange.setOut(m);
	}

}
