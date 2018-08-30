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

		logger.log(Level.INFO, "--------------------------------------------------");
		String header="|";
		int i=1;
		String column;
		for (Map<String, Object> record : data) {
			Iterator<Entry<String,Object>> it = record.entrySet().iterator();
			Entry<String, Object> entry=null;
			column="";
			while (it.hasNext()) {
				entry = it.next();
				if (i==1) header=header+ entry.getKey().toUpperCase() + " | "; // print the column name only the first time
				column=column+entry.getValue() + " | ";
			}
			if (i==1) logger.log(Level.INFO, header);
			logger.log(Level.INFO, column);
			i++;
		}
		
	}

}
