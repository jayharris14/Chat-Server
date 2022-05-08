package models;

import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DonutShop extends UnicastRemoteObject implements RMIObserved, Serializable{

	protected DonutShop() throws RemoteException {
		
		super();
		// TODO Auto-generated constructor stub
	}
	
	ArrayList<RMIObserver> observers=new ArrayList<RMIObserver>();
	@Override
	public void addObserver(RMIObserver o) {
		observers.add(o);
		
	}

	@Override
	public void removeObserver(RMIObserver o) {
		observers.remove(o);
		
	}
	public void notifyObservers() throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		for(RMIObserver o: observers)
		{
			o.notifyFinished();
		}
	}
	
	public void makeDonuts() throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		notifyObservers();
	}

}
