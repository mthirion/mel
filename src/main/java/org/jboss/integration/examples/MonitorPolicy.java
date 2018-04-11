package org.jboss.integration.examples;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Route;

public class MonitorPolicy extends org.apache.camel.impl.RoutePolicySupport{

	Logger logger = Logger.getLogger(this.getClass().getName());
			
	@Override
	public void onExchangeBegin(Route route, Exchange exchange) {
		// TODO Auto-generated method stub
		//System.out.println("exchange [" + exchange.getExchangeId() + "] began");
		
	}

	@Override
	public void onExchangeDone(Route route, Exchange exchange) {
		// TODO Auto-generated method stub
		if (exchange.isFailed())
			logger.log(Level.WARNING, "MONITOR POLICY :: exchange [" + exchange.getExchangeId() + "] failed");
		else
			logger.info("MONITOR POLICY :: exchange [" + exchange.getExchangeId() + "] completed successfully");
	}

}
