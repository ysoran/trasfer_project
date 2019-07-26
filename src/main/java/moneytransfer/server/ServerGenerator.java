package moneytransfer.server;

import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.glassfish.jersey.servlet.ServletContainer;

import moneytransfer.controller.Routes;

public class ServerGenerator {
	private static ServletContextHandler context;
	private static Server server;
	public static void config(){
		context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	    context.setContextPath("/");
	    QueuedThreadPool threadPool = new QueuedThreadPool();
	    threadPool.setMaxThreads(500);
	    server = new Server(threadPool);
	    ServerConnector http = new ServerConnector(server, new HttpConnectionFactory());
	    http.setPort(8080);
	    server.addConnector(http);
	    server.setHandler(context);
	    ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
	    jerseyServlet.setInitOrder(0);
	    jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", Routes.class.getCanonicalName());
	}
	
	public static void start() throws Exception{
		try {
	    	server.start();
	    	server.join();
	    } finally {
	    	server.destroy();
	    }
	}
	
	public static Server getServer(){
		return server;
	}
}
