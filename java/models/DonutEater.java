package models;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DonutEater extends UnicastRemoteObject implements RMIObserver, Serializable{

	protected DonutEater() throws RemoteException, MalformedURLException, NotBoundException, AlreadyBoundException, InterruptedException {
		RMIObserved observed = (RMIObserved) Naming.lookup("rmi://127.0.0.1/DONUT");
		observed.addObserver(this);
		DonutShop donutshop = new DonutShop();
		donutshop.makeDonuts();
	}
	


	String name="fred";

	@Override
	public void notifyFinished() {
		System.out.println(name+ "was called");
		
	}

		// TODO Auto-generated method stu

}
