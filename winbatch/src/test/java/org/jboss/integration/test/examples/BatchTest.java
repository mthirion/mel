package org.jboss.integration.test.examples;

import java.util.Dictionary;

import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;


public class BatchTest extends CamelBlueprintTestSupport {


    @Override
    protected String getBlueprintDescriptor() {
        return "OSGI-INF/blueprint/camel-context.xml";
    }
    

    @Override
    protected String useOverridePropertiesWithConfigAdmin(Dictionary props) throws Exception {

    	props.put("cron.expression", "0/30+*+*+*+*+?");
       
        return "org.jboss.integration.examples.batch";
    }
    
    @Test
    public void unitTestRoute() throws Exception {
         
    	System.out.println(this.getTestMethodName());
    	System.out.println("***********************");
    	
//    	final String start = "direct:start";
//    	context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {
//    	    @Override
//    	    public void configure() throws Exception {
//    	        replaceFromWith(start);
//    	    }
//    	});
    	
        
        //String payload = "";
        //Exchange request = createExchangeWithBody(payload);
        
        MockEndpoint mock = getMockEndpoint("mock:response");
        mock.expectedMinimumMessageCount(1);
        //Exchange ut = template.send(start, request);
        //Exchange sendResult = testProducer.send(request);
        assertMockEndpointsSatisfied(); 

        
        System.out.println("RESPONSE ");
        System.out.println(mock.getReceivedExchanges().get(0).getIn().getBody(String.class));
        //assertEquals("HELLO", response.getIn().getBody());
        
        assertTrue(true);
    }

   
}