package models;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MathServer 
	extends UnicastRemoteObject
	implements MathServerInterface 
{
	private static final long serialVersionUID= -2;

	
	private int visits=0;
	
	protected MathServer() throws RemoteException
	{
		super();
	}

	@Override
	public int addnumbers(int a, int b) throws RemoteException {
		visits++;
		System.out.println("Answering Questions");
		return a+b;
	}
	
	public static void main(String[] args) throws MalformedURLException, AlreadyBoundException
	{
		try
		{
			MathServer M = new MathServer();
			Registry registry=LocateRegistry.createRegistry(2079);
			registry.bind("MATHS", M);
			System.out.println("The RMI_Server is ready and running");
			
		 }catch (RemoteException e)
		{		System.out.println("no");
			 e.printStackTrace();
		}
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

}
