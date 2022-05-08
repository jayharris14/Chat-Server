package models;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MathClient 

{

	public static void main(String[] args) throws RemoteException, MalformedURLException
	{
		
	try {
		Registry registry=LocateRegistry.getRegistry(2089);
		MathServerInterface ms =(MathServerInterface) registry.lookup("MATHS");
	
		int answer= ms.addnumbers(5, 10);
		
		System.out.println("Answer is " +answer);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NotBoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
