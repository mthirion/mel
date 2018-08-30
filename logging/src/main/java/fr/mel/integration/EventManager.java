package fr.mel.integration;

import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.api.management.mbean.ManagedRouteMBean;
import org.apache.camel.management.event.ExchangeCompletedEvent;
import org.apache.camel.management.event.ExchangeFailedEvent;

public class EventManager extends org.apache.camel.support.EventNotifierSupport {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	private Boolean errorDetected=false;
	
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
			errorDetected=true;
		}
				
		String id = exchange.getExchangeId();
		ManagedRouteMBean perfMbean = exchange.getContext().getManagedRoute(exchange.getContext().getRoutes().get(0).getId(), ManagedRouteMBean.class);
		long respTime = 0;
		respTime+=perfMbean.getLastProcessingTime();
		Message m = exchange.getIn();
		m.setHeader("exec_milliseconds", String.valueOf(respTime));
		m.setHeader("identifier", exchange.getExchangeId());
		exchange.setOut(m);
	
		logger.log(Level.WARNING,"[MEL EVENT MANAGER ] :: route = " + exchange.getContext().getRoutes().get(0).getId());		
		
		if (errorDetected==true && exchange.getContext().getRoutes().get(0).getId() == "event-route")
		{
			logger.log(Level.WARNING,"[MEL EVENT MANAGER] :: Exchange [" + id + "] has failed");
		
			
			logger.log(Level.WARNING,"[MEL EVENT MANAGER] :: send an email for " + exchange.getContext().getRoutes().get(0).getId());
			//sendMail(exchange);
			
		}
		else
			logger.info("[MEL EVENT MANAGER] :: Exchange [" + id + "] completed successfully in " + respTime + " ms");
		
	}
	protected void sendMail(Exchange exchange) {
		Endpoint edp = exchange.getContext().getRoute("emailRoute").getEndpoint();
		ProducerTemplate tmpl = exchange.getContext().createProducerTemplate();
		Exchange exch = exchange.copy();
		exch.setException(null);
		Message email_msg=exch.getIn();
		String email_content="[MEL EVENT MANAGER] :: ";
		email_content+="Exchange ["; email_content+=exchange.getExchangeId(); email_content+="] has failed \n";
		email_content+="\nContext = "; email_content+=exchange.getContext().getName();
		email_content+="\nRoute = "; email_content+=exchange.getUnitOfWork().getRouteContext().getRoute().getId();
		email_content+="\nURI = "; email_content+=exchange.getContext().getRoute(exchange.getUnitOfWork().getRouteContext().getRoute().getId() ).getEndpoint().getEndpointUri();
		email_msg.setBody(email_content+"\n");
		exch.setIn(email_msg);exch.setOut(email_msg);
		logger.log(Level.INFO,"Sending email");
		tmpl.send(edp, exch);
		logger.log(Level.INFO,"Email sent");
	}

}
