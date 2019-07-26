package moneytransfer.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import moneytransfer.constants.Constants;
import moneytransfer.database.FakeDataGenerator;
import moneytransfer.model.Transaction;
import moneytransfer.model.TransactionWay;
import moneytransfer.server.ServerGenerator;
import moneytransfer.tasks.MassTransferTask;

public class ControllerTest {
	private static Server server;
	private static WebTarget target;
	
	@BeforeClass
    public static void beforeAll()  {
        ServerGenerator.config();
        try{
        	FakeDataGenerator.generate();
        	server = ServerGenerator.getServer();
        	server.start();
        }catch (Exception e) {
        	e.printStackTrace();
		}
        
        Client c = ClientBuilder.newClient();
        target = c.target(Constants.URLS.BASE_URL.getValue());
    }
	
 	@AfterClass
    public static void afterAll() {
        try {
			server.stop();
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 
 	@Test
    public void testAddNewTransfer_worksWithDefaultPath() {
        Response response = target.path("/byid/1/2/10000").request().get();
        String result = response.readEntity(String.class);
        
        assertEquals(200, response.getStatus());
        assertEquals("text/plain",response.getMediaType().toString());
        assertThat(result, is(equalTo("Your transfer is successfull")));

    }
 	
 	@Test
    public void testAddNewTransfer_worksWithInccorrectPath() {
        Response response = target.path("/byid/1/2/").request().get();
        String result = response.readEntity(String.class);
        
        assertEquals(404, response.getStatus());
    }

}
