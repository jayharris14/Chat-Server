package models;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIObserver extends Remote {
	public void notifyFinished(String name) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
	public void notifyFinished2(String name) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
	public void notifyFinished3(String name) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
	public void notifyFinished4() throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
	public void notifyFinished5() throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
	public void notifyFinished6() throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
}
