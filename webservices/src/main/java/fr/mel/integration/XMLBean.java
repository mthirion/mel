package fr.mel.integration;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class XMLBean implements Processor {

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		Logger logger = Logger.getLogger(this.getClass().getName());
		
		// Message body is List<List<String>>
		
		Message message = exchange.getIn();
		logger.log(Level.INFO, "[MEL APPLICATION -JAVA-] :: "+ message.getBody().getClass().toString());
		 
	}

}
