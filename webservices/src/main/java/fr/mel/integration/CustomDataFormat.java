package fr.mel.integration;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;
import org.apache.cxf.helpers.IOUtils;

public final class CustomDataFormat implements DataFormat {

    public void marshal(Exchange exchange, Object graph, OutputStream stream) throws Exception {
 
        // do convert the "graph" object to a stream
        String body = graph.toString();
        
        stream.write(body.getBytes());
    }
 
    public Object unmarshal(Exchange exchange, InputStream stream) throws Exception {
           	
    	String s = IOUtils.toString(stream, "UTF-8");
    	
    	// parse String and create object o
    	Object o=s.getBytes();
        
    	return o;
    }

}