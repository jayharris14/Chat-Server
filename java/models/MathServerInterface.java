package models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MathServerInterface extends Remote 
{
	public int addnumbers(int a, int b) throws RemoteException;
	
}
