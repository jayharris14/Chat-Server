package models;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;



public class ConcordClients {
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException
	{
	try {
		ConcordServerInterface cs =(ConcordServerInterface) Naming.lookup("rmi://localhost:2079/Concord");
	
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	


	}
}
