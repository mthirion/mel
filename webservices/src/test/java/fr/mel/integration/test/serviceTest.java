package fr.mel.integration.test;

import java.util.Dictionary;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;


public class serviceTest extends CamelBlueprintTestSupport {

    @Override
    protected String getBlueprintDescriptor() {
        return "OSGI-INF/blueprint/restjson.xml";
    }
    

    @Override
    protected String useOverridePropertiesWithConfigAdmin(Dictionary props) throws Exception {

    	props.put("app.key", "test_key");
       
        return "fr.mel.integration.configfile";
    }

    @Test
    public void unitTestRoute() throws Exception {
         
    	System.out.println(this.getTestMethodName());
    	System.out.println("***********************");
    	
    	String start = "direct:start";
    	context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {
    	    @Override
    	    public void configure() throws Exception {
    	        replaceFromWith(start);
    	    }
    	});
    	
        String payload = "{ 	\"id\": \"11\", \"classifier\": \"aClass\"	 }";
        Exchange request = createExchangeWithBody(payload);
        
        MockEndpoint mock = getMockEndpoint("mock:restResponse");
        mock.expectedMinimumMessageCount(1);
        Exchange ut = template.send(start, request);
        
        //Exchange sendResult = testProducer.send(request);
        assertMockEndpointsSatisfied(); 

        
        System.out.println("RESPONSE ");
        System.out.println(mock.getReceivedExchanges().get(0).getIn().getBody());
        //assertEquals("HELLO", response.getIn().getBody());
        
        assertTrue(true);
    }

    
    @Test
    public void integrationTestRoute() throws Exception {
         
    	System.out.println(this.getTestMethodName());
    	System.out.println("***********************");
    	CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:9898/api/flow");
        
        String payload = "{ 	\"id\": \"11\", \"classifier\": \"aClass\"	 }";
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(payload));
       
        MockEndpoint mock = getMockEndpoint("mock:restResponse");
        CloseableHttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        
        assertMockEndpointsSatisfied(); 
        
        System.out.println("RESPONSE ");
        //entity.getContent();
        System.out.println(EntityUtils.toString(entity));
        
        assertTrue(true);
    }
}