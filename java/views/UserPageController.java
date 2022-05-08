package views;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.ConcordClientModel;
import models.RMIObserved;
import models.RMIObserver;
import models.Server;
import models.User;
import models.ViewTransitionalModel;

public class UserPageController extends UnicastRemoteObject implements RMIObserver, Serializable{
	
	
	public UserPageController() throws RemoteException, MalformedURLException, NotBoundException {
		super();
	}

	ConcordClientModel concordmodel;
	ViewTransitionalModel viewtransitionalmodel;

	
	public void setModel(ViewTransitionalModel viewTransitionalModel, ConcordClientModel model) throws RemoteException, AlreadyBoundException, InterruptedException, MalformedURLException, NotBoundException {
	        concordmodel=model;
	  		viewtransitionalmodel=viewTransitionalModel;
	  		concordmodel.setUser(concordmodel.getUser());
	  		inviteView.getItems().clear();
	  		inviteView.getItems().clear();
	  		serverView.getItems().clear();
	  		serverView.setItems(model.userservers(concordmodel.getUser()));
	  		inviteView.setItems(model.getinvitedservers(concordmodel.getUser()));
	  		this.setbuttons();
	  		this.setserverbuttons();
	  		RMIObserved observed = (RMIObserved) Naming.lookup("rmi://10.14.1.70:1151/Concord");
			observed.addObserver(this);
		}
	
	 @FXML
	 private ListView<Button> serverView;

	 @FXML
	 private ListView<User> blockView;
	 
	  @FXML
	 private ListView<Button> inviteView;
	
	 @FXML
	 private TextField servernameLabel;
	 
	
	 @FXML
	 void onClickButton(ActionEvent event) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		 if (servernameLabel.textProperty().get()!="") {
			 concordmodel.createserver(servernameLabel.textProperty().get());
			 serverView.getItems().clear();
			 inviteView.getItems().clear();
			 viewtransitionalmodel.showUser();

		 }
	 }
	 
	 


		 


	 
	    public void setbuttons() throws RemoteException {
	    	ArrayList<Server> Servers=concordmodel.getServers();
	    	for (int i=0; i<serverView.getItems().size(); i++) {
	    		final Integer ii = new Integer(i);
	    		serverView.getItems().get(i).setOnAction(new EventHandler<ActionEvent>()
	    		{
		        @Override
		        public void handle(ActionEvent actionEvent) {
		        	concordmodel.setServer(Servers.get(ii));
		            try {
		            	inviteView.getItems().clear();
		            	serverView.getItems().clear();
						viewtransitionalmodel.showServer();
					} catch (AlreadyBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		        }});
	    		}
	    	}
	 
	 
	    public void setserverbuttons() throws RemoteException {
	    	ArrayList<Server> Servers=concordmodel.getInvites();
	    	for (int i=0; i<inviteView.getItems().size(); i++) {
	    		final Integer ii = new Integer(i);
	    		inviteView.getItems().get(i).setOnAction(new EventHandler<ActionEvent>()
	    		{
		        @Override
		        public void handle(ActionEvent actionEvent) {
		        	concordmodel.setServer(Servers.get(ii));
		            inviteView.getItems().clear();
					serverView.getItems().clear();
					try {
						viewtransitionalmodel.showInvitationView();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		        }});
	    		}
	    	}
	    @FXML
	    void onClickAcceptButton(ActionEvent event) throws AlreadyBoundException, RemoteException, InterruptedException, NotBoundException {
	    	inviteView.getItems().clear();
	    	serverView.getItems().clear();
	    	concordmodel.addMember();
	    	concordmodel.notifyFinished4();
	    	viewtransitionalmodel.showUser();
	    }

	    @FXML
	    void onClickDeclineButton(ActionEvent event) throws AlreadyBoundException, InterruptedException, NotBoundException, RemoteException {
	    	inviteView.getItems().clear();
	    	serverView.getItems().clear();
	    	concordmodel.removeinvites();
	    	viewtransitionalmodel.showUser();
	    }

	    @FXML
	    void onCloseButton(ActionEvent event) throws AlreadyBoundException, InterruptedException, NotBoundException {
	    	inviteView.getItems().clear();
	    	serverView.getItems().clear();
	    	viewtransitionalmodel.showUser();
	    }

		@Override
		public void notifyFinished(String name) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			User user=concordmodel.GetuserbyName(name);
			System.out.println("the user is"+ user);
			inviteView.getItems().clear();
	  		inviteView.setItems(concordmodel.getinvitedservers(user));
	  		this.setserverbuttons();
			
		}
		
		 @FXML
		    void onClickRefresh(ActionEvent event) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			 	inviteView.getItems().clear();
		  		serverView.getItems().clear();
		  		serverView.setItems(concordmodel.userservers(concordmodel.getUser()));
		  		inviteView.setItems(concordmodel.getinvitedservers(concordmodel.getUser()));
		  		this.setbuttons();
		  		this.setserverbuttons();
		  		viewtransitionalmodel.showUser();
		    }
		 
		 @FXML
		    void onClickLogoutButton(ActionEvent event) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			 inviteView.getItems().clear();	
			 viewtransitionalmodel.showLogin();
		    }








		@Override
		public void notifyFinished2(String name)
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			// TODO Auto-generated method stub
			
		}








		@Override
		public void notifyFinished3(String name)
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			// TODO Auto-generated method stub
			
		}
		







		@Override
		public void notifyFinished4()
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			// TODO Auto-generated method stub
			
		}








		@Override
		public void notifyFinished5()
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			// TODO Auto-generated method stub
			
		}
}





	

