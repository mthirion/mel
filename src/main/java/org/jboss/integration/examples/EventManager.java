package org.jboss.integration.examples;

import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.api.management.mbean.ManagedRouteMBean;
import org.apache.camel.management.event.ExchangeCompletedEvent;
import org.apache.camel.management.event.ExchangeFailedEvent;

public class EventManager extends org.apache.camel.support.EventNotifierSupport {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public boolean isEnabled(EventObject event) {
		// TODO Auto-generated method stub
		return (event instanceof ExchangeCompletedEvent) || (event instanceof ExchangeFailedEvent);
	}

	@Override
	public void notify(EventObject event) throws Exception {
		// TODO Auto-generated method stub
		
		Exchange exchange = null;
		if (event instanceof ExchangeCompletedEvent) {
			ExchangeCompletedEvent completedEvent = (ExchangeCompletedEvent)event;
			exchange = completedEvent.getExchange();
		}
		if (event instanceof ExchangeFailedEvent) {
			ExchangeFailedEvent failedEvent = (ExchangeFailedEvent)event;
			exchange = failedEvent.getExchange();
		}
		
		String id = exchange.getExchangeId();
		ManagedRouteMBean perfMbean = exchange.getContext().getManagedRoute(exchange.getContext().getRoutes().get(0).getId(), ManagedRouteMBean.class);
		long respTime = 0;
		respTime+=perfMbean.getLastProcessingTime();
		Message m = exchange.getIn();
		m.setHeader("exec_milliseconds", String.valueOf(respTime));
		m.setHeader("identifier", exchange.getExchangeId());
		exchange.setOut(m);
		
		if (exchange.isFailed())
			logger.log(Level.WARNING,"EVENT MANAGER :: Exchange [" + id + "] has failed");
		else
			logger.info("EVENT MANAGER :: Exchange [" + id + "] completed successfully in " + respTime + " ms");
		
	}

}
