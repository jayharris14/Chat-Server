package views;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import models.Channel;
import models.ConcordClientModel;
import models.ConcordServer;
import models.DirectConversation;
import models.Message;
import models.RMIObserved;
import models.RMIObserver;
import models.Server;
import models.User;
import models.ViewTransitionModelInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ServerController extends UnicastRemoteObject implements RMIObserver, Serializable{
	
	ViewTransitionModelInterface model;
	ConcordClientModel concordclientmodel;
	String set="off";
	Channel defaultc;
	
	public void setModel(ViewTransitionModelInterface newModel, ConcordClientModel model2) throws RemoteException, AlreadyBoundException, MalformedURLException, NotBoundException, InterruptedException
	{
		model=newModel;
		concordclientmodel=model2;
		channelView.getItems().clear();
    	userView.getItems().clear();
    	concordclientmodel.setChannel(null);
    	concordclientmodel.setChannel(concordclientmodel.getChannel());
		channelView.setItems(concordclientmodel.serverchannels(concordclientmodel.getServer()));
		userView.setItems(concordclientmodel.getserverusers(concordclientmodel.getServer().getName()));
		concordclientmodel.setChannel(null);
		this.setbuttons();
		set="off";
		RMIObserved observed = (RMIObserved) Naming.lookup("rmi://10.14.1.70:1151/Concord");
		observed.addObserver(this);
	}



		public ServerController() throws RemoteException {
		super();
	}



		@FXML
	    private Button add;

	    @FXML
	    private ListView<Button> channelView;

	    @FXML
	    private ListView<Button> userView;

	    @FXML
	    private ListView<Message> messageView;

	    @FXML
	    private TextField channelnameLabel;

	    @FXML
	    private TextField messageLabel;
	    

	    @FXML
	    private TextField inviteLabel;
	    
	    @FXML
	    private TextField kickLabel;


	    @FXML
	    void onClickMessageButton(ActionEvent event) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
	    	if (messageLabel.textProperty().get()!="") {
	   			Channel channel=concordclientmodel.createmessage(messageLabel.textProperty().get());
	   			ArrayList<Channel> channels=concordclientmodel.getChannels();
	   			for (int i=0; i<channels.size(); i++) {
	   				if (channel.getName().equals(channels.get(i).getName())){
	   					concordclientmodel.setChannel(channels.get(i));
	   					messageView.getItems().clear();
	   					messageView.setItems(concordclientmodel.getmessages(concordclientmodel.getChannel()));
	   				}
	   			}
	    	}
	    }
	    
	    @FXML
	    void updatemessages() throws RemoteException, AlreadyBoundException, NotBoundException, InterruptedException{
	    	concordclientmodel.setChannel(concordclientmodel.getChannel());
	    	messageView.setItems(concordclientmodel.getmessages(concordclientmodel.getChannel()));
        	channelView.getItems().clear();
	    	messageView.getItems().clear();
	    	userView.getItems().clear();
        	model.showServer();
	    }
	  


	    @FXML
	    void onClickChannelButton(ActionEvent event) throws RemoteException, AlreadyBoundException, NotBoundException, InterruptedException {
	   		 if (channelnameLabel.textProperty().get()!="") {
	   			 concordclientmodel.createchannel(channelnameLabel.textProperty().get());
	   			 System.out.println("channel creating");
	   			 channelView.getItems().clear();
	   			 messageView.getItems().clear();
	   			 userView.getItems().clear();
	   			 messageView.setItems(concordclientmodel.getmessages(concordclientmodel.getChannel()));
	   			 model.showServer();

	   		 }
	   	 }
	    

	    public void setbuttons() throws RemoteException {
	    	ArrayList<Channel> channels=concordclientmodel.getChannels();
	    	for (int i=0; i<channelView.getItems().size(); i++) {
	    		final Integer ii = new Integer(i);
	    		channelView.getItems().get(i).setOnAction(new EventHandler<ActionEvent>()
	    		{
		        @Override
		        public void handle(ActionEvent actionEvent) {
		        	concordclientmodel.setChannel(channels.get(ii));
		            try {
		            	messageView.getItems().clear();
		            	messageView.setItems(concordclientmodel.getmessages(concordclientmodel.getChannel()));
		            	set="on";
		    
					} catch (AlreadyBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		        }});
	    		}
	    	}
	    
	   public void setuserbuttons() throws RemoteException {
	    	ArrayList<DirectConversation> dcs=concordclientmodel.getDirectConversations();
	    	ArrayList<User> users=concordclientmodel.getallusers(concordclientmodel.getServer().getName());
	    	for (int i=0; i<userView.getItems().size(); i++) {
	    		final Integer ii = new Integer(i);
	    		String name=concordclientmodel.getUser().getUserName()+users.get(i).getUserName();
	    		userView.getItems().get(i).setOnAction(new EventHandler<ActionEvent>()
	    		{
		        @Override
		        public void handle(ActionEvent actionEvent) {
		        	for (int j=0; j<dcs.size(); j++) {
		        		if (dcs.get(j).getUsers().contains(users.get(ii))){
		        			concordclientmodel.setDirectConversation(dcs.get(j));
		        		}
		        	}
		            try {
		            	messageView.getItems().clear();
		            	messageView.setItems(concordclientmodel.getdirectconversationmessages(name));
		            	set="on";
		    
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		        }});
	    		}
	    	}
	    
	    @FXML
	    void onClickInviteButton(ActionEvent event) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
	    	if (inviteLabel.textProperty().get()!="") {
	   			concordclientmodel.sendinvite(inviteLabel.textProperty().get());
	   			channelView.getItems().clear();
		    	messageView.getItems().clear();
		    	userView.getItems().clear();
	   			model.showServer();
	   			
	    }
	    
	    }
	    
	    @FXML
	    void onClickKickButton(ActionEvent event) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
	    	if (kickLabel.textProperty().get()!="") {
	   			concordclientmodel.kickuser(kickLabel.textProperty().get());
	   			channelView.getItems().clear();
		    	messageView.getItems().clear();
		    	userView.getItems().clear();
	   			model.showServer();
	   			
	    }
	    
	    }

		@Override
		public void notifyFinished2(String name)
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			channelView.getItems().clear();
	    	messageView.getItems().clear();
	    	userView.getItems().clear();
	    	Server server=concordclientmodel.GetserverbyName(name);
	    	concordclientmodel.setChannel(concordclientmodel.getChannel());
			channelView.setItems(concordclientmodel.serverchannels(server));
			this.setbuttons();
			
		}

		@Override
		public void notifyFinished(String name)
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void notifyFinished3(String name)
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
	    	messageView.getItems().clear();
	    	Channel channel=concordclientmodel.GetchannelbyName(name);
	    	if (concordclientmodel.getChannel().getName().equals(channel.getName()) && set=="on") {
	    		messageView.setItems(concordclientmodel.getmessages(channel));
	    	}
	    	channelView.getItems().clear();
	    	userView.getItems().clear();
	    	channelView.setItems(concordclientmodel.serverchannels(concordclientmodel.getServer()));
			this.setbuttons();
			
		}
		
		@Override
		public void notifyFinished4()
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			userView.getItems().clear();
			userView.setItems(concordclientmodel.getserverusers(concordclientmodel.getServer().getName()));
		}
		
		 
		 @FXML
		    void onClickLogoutButton (ActionEvent event) throws AlreadyBoundException, InterruptedException, NotBoundException {
			 channelView.getItems().clear();
		    userView.getItems().clear();
		    messageView.getItems().clear();
			 model.showLogin();
		    }
		  
		  


		@Override
		public void notifyFinished5()
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			channelView.getItems().clear();
	    	messageView.getItems().clear();
	    	userView.getItems().clear();
   			model.showServer();
			
		}
		

	    @FXML
	    void onBackButton(ActionEvent event) throws AlreadyBoundException, InterruptedException, NotBoundException {
	    	channelView.getItems().clear();
	    	messageView.getItems().clear();
	    	userView.getItems().clear();
	    	model.showUser();
	    }




		
	    
	    
	   		


}
