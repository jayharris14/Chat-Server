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
	  		Platform.runLater(
	  			  () -> {inviteView.getItems().clear();
	  		inviteView.getItems().clear();
	  		inviteView.getItems().clear();
	  		serverView.getItems().clear();
	  		try {
				serverView.setItems(model.userservers(concordmodel.getUser()));
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  		try {
	  			inviteView.setItems(model.getinvitedservers(concordmodel.getUser()));
				
				} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (AlreadyBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  		try {
				this.setbuttons();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  		try {
				this.setserverbuttons();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  		RMIObserved observed=null;
			try {
				observed = (RMIObserved) Naming.lookup("rmi://10.14.1.70:1151/Concord");
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				observed.addObserver(this);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  			  });}
	
	 @FXML
	 private ListView<Button> serverView;

	 @FXML
	 private ListView<User> blockView;
	 
	  @FXML
	 private ListView<Button> inviteView;
	
	 @FXML
	 private TextField servernameLabel;
	 
	  @FXML
	  private ListView<String> userinfoView;
	 
	
	 @FXML
	 void onClickButton(ActionEvent event) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		 if (servernameLabel.textProperty().get()!="") {
			 Platform.runLater(
					  () -> {try {
						concordmodel.createserver(servernameLabel.textProperty().get());
					} catch (RemoteException | AlreadyBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 serverView.getItems().clear();
			 inviteView.getItems().clear();
			 try {
				viewtransitionalmodel.showUser();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		 });}
	 }
	 

	    @FXML
	    void onClickEditButton(ActionEvent event) throws AlreadyBoundException, InterruptedException, NotBoundException {
	    	inviteView.getItems().clear();
	  		serverView.getItems().clear();
	    	viewtransitionalmodel.showUserInfo();
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
		            Platform.runLater(
							  () -> {inviteView.getItems().clear();
					serverView.getItems().clear();
					try {
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
							  });

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
		        	Platform.runLater(
		        			  () -> {concordmodel.setServer(Servers.get(ii));
		            inviteView.getItems().clear();
					serverView.getItems().clear();
		        			  });
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
	    	Platform.runLater(
	    			  () -> {inviteView.getItems().clear();
	    	serverView.getItems().clear();
	    	try {
				concordmodel.addMember();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				concordmodel.notifyFinished4();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				viewtransitionalmodel.showUser();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    			  }); }

	    @FXML
	    void onClickDeclineButton(ActionEvent event) throws AlreadyBoundException, InterruptedException, NotBoundException, RemoteException {
	    	Platform.runLater(
	    			  () -> {inviteView.getItems().clear();
	    	serverView.getItems().clear();
	    	try {
				concordmodel.removeinvites();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	try {
				viewtransitionalmodel.showUser();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    			  });}

	    @FXML
	    void onCloseButton(ActionEvent event) throws AlreadyBoundException, InterruptedException, NotBoundException {
	    	Platform.runLater(
	    			  () -> {inviteView.getItems().clear();
	    	serverView.getItems().clear();
	    	try {
				viewtransitionalmodel.showUser();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    			  }); }

		@Override
		public void notifyFinished(String name) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			User user=concordmodel.GetuserbyName(name);
			System.out.println("the user is"+ user);
			Platform.runLater(
					  () -> {inviteView.getItems().clear();
	  		try {
				inviteView.setItems(concordmodel.getinvitedservers(user));
				inviteView.getItems().clear();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (AlreadyBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  		try {
				this.setserverbuttons();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
					  });}
		
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
			 Platform.runLater(
					  () -> { inviteView.getItems().clear();	
			 serverView.getItems().clear();
			 viewtransitionalmodel.showLogin();
					  });}








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


		@Override
		public void notifyFinished6()
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			// TODO Auto-generated method stub
			
		}


	

	

}





	

