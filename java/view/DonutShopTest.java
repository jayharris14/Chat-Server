package view;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.DonutEater;
import models.DonutShop;
import models.RMIObserved;

class DonutShopTest {
	
	DonutShop ds;

	@BeforeEach
	void setUp() throws Exception {
		ds=new DonutShop();
		Registry registry= LocateRegistry.createRegistry(1099);
		registry.rebind("DONUT", ds);
	}

	@Test
	void test() throws AlreadyBoundException, InterruptedException {
		
		try {
			DonutEater eater= new DonutEater();
			RMIObserved observed = (RMIObserved) Naming.lookup("rmi://127.0.0.1/DONUT");
			ds.makeDonuts();
			observed.addObserver(eater);
			eater.name="Tony";
			ds.makeDonuts();
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		fail("Not yet implemented");

}
}
