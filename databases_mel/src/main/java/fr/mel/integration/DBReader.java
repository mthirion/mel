package fr.mel.integration;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class DBReader implements Processor {

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		Logger logger = Logger.getLogger(this.getClass().getName());

		Message message = exchange.getIn();
		List<Map<String, Object>> data = (List<Map<String, Object>>) message.getBody();

		Integer CamelSqlRowCount=(Integer)message.getHeader("CamelSqlRowCount");
		logger.log(Level.INFO, "[MEL APPLICATION -JAVA-] :: found " + CamelSqlRowCount.intValue() + " records");
		
		int i=1;
		for (Map<String, Object> record : data) {
			logger.log(Level.INFO, "[MEL APPLICATION -JAVA-] :: database row" +i);
			Iterator<Entry<String,Object>> it = record.entrySet().iterator();
			Entry<String, Object> entry=null;
			while (it.hasNext()) {
				entry = it.next();
				logger.log(Level.INFO, entry.getKey() + " || " + entry.getValue());
			}
			i++;
		}
	}

}
