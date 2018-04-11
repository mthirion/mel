package org.jboss.integration.examples;

import java.util.*;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class ElkProcessor implements Processor{

	
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		Logger logger = Logger.getLogger(this.getClass().getName());
		

		Map<String, String> map = new HashMap<String, String>();
		map.put("content", "test");

		Message m = exchange.getIn();
		m.setBody(map);
		exchange.setOut(m);
	}

}
