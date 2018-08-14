package fr.mel.integration;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class CSVBean implements Processor {

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		Logger logger = Logger.getLogger(this.getClass().getName());
		
		// Message body is List<List<String>>
		
		Message message = exchange.getIn();
		List<List<String>> data = (List<List<String>>) message.getBody();
		
		int i=1;
		for (List<String> line : data) {
			logger.log(Level.INFO, "[MEL APPLICATION -JAVA-] :: logging CSV line" +i);
		    logger.log(Level.INFO, line.get(0) + " || " + line.get(1) + "||" + line.get(2) );
		    i++;
		}
	}

}
