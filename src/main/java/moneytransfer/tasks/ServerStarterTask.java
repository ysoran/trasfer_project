package moneytransfer.tasks;

import moneytransfer.database.FakeDataGenerator;
import moneytransfer.server.ServerGenerator;

public class ServerStarterTask implements Runnable{

	public void run() {
		ServerGenerator.config();
    	FakeDataGenerator.generate();
    	try {
			ServerGenerator.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
