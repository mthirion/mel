package org.jboss.integration.test.examples;

import java.util.Dictionary;

import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class serviceTest extends CamelBlueprintTestSupport {

	//@Produce(uri = "rest:test.get/company/{name}")
	//protected ProducerTemplate testProducer;
	
    private static final String BASE_URL = "http://localhost:9897/api";
    
    @Override
    protected String getBlueprintDescriptor() {
        return "OSGI-INF/blueprint/camel-context.xml";
    }


    @Override
    protected String useOverridePropertiesWithConfigAdmin(Dictionary props) throws Exception {
        props.put("api.root.url", BASE_URL);
        return "org.jboss.integration.examples.service";
    }

    @Test
    public void testRoute() throws Exception {
        /*
        String url = BASE_URL + "/service/flow";
        
        
        String payload = "{ \"id\": 666, \"name\": \"The devil\"}";
        Exchange request = createExchangeWithBody(payload);
        Exchange response = template.send(BASE_URL + "/flow/myMessage", request);
        //assertMockEndpointsSatisfied(); 

        assertEquals("HELLO", response.getIn().getBody());
        */
    }

}