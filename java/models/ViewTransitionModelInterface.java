package models;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

public interface ViewTransitionModelInterface 
{
	public void showUser() throws AlreadyBoundException, InterruptedException, NotBoundException;
	public void showLogin();
	public void showCreateAccount();
	public void showError();
	public void showMainError();
	public void showInvitationView() throws AlreadyBoundException, InterruptedException, NotBoundException;
	public void showServer() throws AlreadyBoundException, NotBoundException, InterruptedException;
	
}

