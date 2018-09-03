package fr.mel.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class CSVProcess implements Processor {

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		Logger logger = Logger.getLogger(this.getClass().getName());
		
		
		Message message=exchange.getIn();
		
		List<Map<String, Object>> newdata = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> rowOne = new HashMap<String, Object>();
		rowOne.put("goodName", "MICHAEL");
		rowOne.put("name", "THIRION");
		rowOne.put("sign", "Pisces");
		Map<String, Object> rowTwo = new HashMap<String, Object>();
		rowTwo.put("goodName", "JEREMY");
		rowTwo.put("name", "SARAN");
		rowTwo.put("sign", "Leo");
		Map<String, Object> rowThree = new HashMap<String, Object>();
		rowTwo.put("goodName", "FRANK");
		rowTwo.put("name", "LURON");
		rowTwo.put("sign", "Scorpio");
		
		newdata.add(rowOne);
		newdata.add(rowTwo);
		newdata.add(rowThree);
		
		message.setBody(newdata);
		exchange.setIn(message);
	}

}
