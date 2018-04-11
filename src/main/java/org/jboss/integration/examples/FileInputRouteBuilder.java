package org.jboss.integration.examples;

import org.apache.camel.builder.RouteBuilder;


public class FileInputRouteBuilder extends RouteBuilder {

	public void configure() throws Exception {
        // direct:start is a internal queue to kick-start the routing in our example
        // we use this as the starting point where you can send messages to direct:start
        from("file:/redhat/tmp/mel/app?filename=*.xml")
            .to("velocity:MailBody.vm");
    }
}
