package fr.mel.integration.test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Dictionary;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;


public class serviceTest extends CamelBlueprintTestSupport {

	private String payload=null;
	private String output_endpoint="file:/redhat/tmp/mel/appdata/out";
	private String inputfile = "src/main/resources/xml/fruits.xml";
	
    @Override
    protected String getBlueprintDescriptor() {
        return "OSGI-INF/blueprint/xslt.xml";
    }
    
    @Override
    protected void doPostSetup() throws Exception {
    	
    	byte[] encoded = Files.readAllBytes(Paths.get("src/main/resources/xml/fruits.xml" ));
        payload = new String(encoded, "UTF-8");
    }
    

    @Override
    protected String useOverridePropertiesWithConfigAdmin(Dictionary props) throws Exception {

    	props.put("appName.myValue", "testValue");
       
        return "fr.mel.integration.configfile";	//return PID;
    }
    
    @Override
    public String isMockEndpoints() {
        return output_endpoint;
    }

    @Test
    public void unitTestXSLTRoute() throws Exception {
         
    	System.out.println(this.getTestMethodName());
    	System.out.println("***********************");
    	
    	
    	// Mock the input endpoint
    	final String start = "direct:start";
    	context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {
    	    @Override
    	    public void configure() throws Exception {
    	        replaceFromWith(start);
    	    }
    	});
    	
    	// Create the test message
        Exchange request = createExchangeWithBody(payload);
        
        
        // Create the test condition
        MockEndpoint mock = getMockEndpoint("mock:"+output_endpoint);
        mock.expectedMinimumMessageCount(1);
        
        
        // Do the test
        Exchange exchange = template.send(start, request);
        assertMockEndpointsSatisfied(); 

        
        System.out.println("RESPONSE ");
        System.out.println(mock.getReceivedExchanges().get(0).getIn().getBody());
        
        assertTrue(true);
    }


}