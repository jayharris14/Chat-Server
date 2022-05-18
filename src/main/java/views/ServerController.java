package views;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javafx.application.Platform;
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
	String iswitch="on";
	Channel defaultc;
	
	public void setModel(ViewTransitionModelInterface newModel, ConcordClientModel model2) throws RemoteException, AlreadyBoundException, MalformedURLException, NotBoundException, InterruptedException
	{
		model=newModel;
		concordclientmodel=model2;
		Platform.runLater(
				  () -> {channelView.getItems().clear();
    	userView.getItems().clear();
    	concordclientmodel.setChannel(null);
    	concordclientmodel.setUser(concordclientmodel.getUser());
    	channelView.getItems().clear();
    	concordclientmodel.setChannel(concordclientmodel.getChannel());
		try {
			channelView.setItems(concordclientmodel.serverchannels(concordclientmodel.getServer()));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			userView.setItems(concordclientmodel.getserverusers(concordclientmodel.getServer().getName()));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		concordclientmodel.setChannel(null);
		try {
			this.setbuttons();
			this.setuserbuttons();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		set="off";
		RMIObserved observed = null;
		try {
			observed = (RMIObserved) Naming.lookup("rmi://10.14.1.70:1151/Concord");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			observed.addObserver(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
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
	    	Platform.runLater(
					  () -> {if (messageLabel.textProperty().get()!="") {
	   			Channel channel = null;
	   			DirectConversation directconversation=null;
				try {
					channel = concordclientmodel.createmessage(messageLabel.textProperty().get());
					
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (AlreadyBoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (NotBoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	   			ArrayList<Channel> channels = null;
				try {
					channels = concordclientmodel.getChannels();
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	   			for (int i=0; i<channels.size(); i++) {
	   				final Integer ii = new Integer(i);
	   				if (channel.getName().equals(channels.get(i).getName())){
	   					concordclientmodel.setChannel(channels.get(ii));
	   					messageView.getItems().clear();
	   					try {
							messageView.setItems(concordclientmodel.getmessages(concordclientmodel.getChannel()));
	   						
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (AlreadyBoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NotBoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	   					try {
	   						userView.getItems().clear();
							userView.setItems(concordclientmodel.getserverusers(concordclientmodel.getServer().getName()));
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	   				}
	   			}
	    	}});
	    }
	    
	    @FXML
	    void updatemessages() throws RemoteException, AlreadyBoundException, NotBoundException, InterruptedException{
	    	Platform.runLater(
	    			  () -> {
	    				  concordclientmodel.setChannel(concordclientmodel.getChannel());
	    				  try {
						messageView.setItems(concordclientmodel.getmessages(concordclientmodel.getChannel()));
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (AlreadyBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        	channelView.getItems().clear();
	    	messageView.getItems().clear();
	    	userView.getItems().clear();
        	try {
				model.showServer();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});
	    }
	  


	    @FXML
	    void onClickChannelButton(ActionEvent event) throws RemoteException, AlreadyBoundException, NotBoundException, InterruptedException {
	    	Platform.runLater(
	   				  () -> { if (channelnameLabel.textProperty().get()!="") {
	   			 try {
	   				userView.setItems(concordclientmodel.getserverusers(concordclientmodel.getServer().getName()));
					concordclientmodel.createchannel(channelnameLabel.textProperty().get());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (AlreadyBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	   			 System.out.println("channel creating");
	   			 try {
	   				userView.setItems(concordclientmodel.getserverusers(concordclientmodel.getServer().getName()));
					messageView.setItems(concordclientmodel.getmessages(concordclientmodel.getChannel()));
					channelView.setItems(concordclientmodel.serverchannels(concordclientmodel.getServer()));
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
				}}

	   		 }
	    );
	   	 }
	    

	    public void setbuttons() throws RemoteException {
	    	ArrayList<Channel> channels=concordclientmodel.getChannels();
	    	for (int i=0; i<channelView.getItems().size(); i++) {
	    		final Integer ii = new Integer(i);
	    		channelView.getItems().get(i).setOnAction(new EventHandler<ActionEvent>()
	    		{
		        @Override
		        public void handle(ActionEvent actionEvent) {
		        	iswitch="on";
		        	concordclientmodel.setChannel(channels.get(ii));
		            Platform.runLater(
							  () -> {messageView.getItems().clear();
					try {
						messageView.setItems(concordclientmodel.getmessages(concordclientmodel.getChannel()));
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
					set="on";});

		        }});
	    		}
	    	}
	    
	   public void setuserbuttons() throws RemoteException {
		   	ArrayList<DirectConversation> dcs = null;
					try {
						dcs = concordclientmodel.getpastdcs();
					} catch (RemoteException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
	    	ArrayList<User> users = null;
			try {
				users = concordclientmodel.getallusers(concordclientmodel.getServer().getName());
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	    	for (int i=0; i<userView.getItems().size(); i++) {
	    		final Integer ii = new Integer(i);
	    		String name1=concordclientmodel.getUser().getUserName();
	    		String name2=users.get(i).getUserName();
	    		String name3=concordclientmodel.getServer().getName();
	    		String dcsname=name1+name2;
	    		String x="yes";
	    		for (int j=0; j<dcs.size(); j++) {
	    			if (dcs.get(j).getName().equals(dcsname)) {
	    				x="no";
	    			}
	    		if (x.equals("yes")) {
	    			try {
						concordclientmodel.creatdcs(name1, name2);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    		}
	    		userView.getItems().get(i).setOnAction(new EventHandler<ActionEvent>()
	    		{
		        @Override
		        public void handle(ActionEvent actionEvent) {
		        	ArrayList<DirectConversation> dcs2 = null;
		        	iswitch="off";
					try {
						dcs2 = concordclientmodel.getpastdcs();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	for (int k=0; k<dcs2.size(); k++) {
		        		if (dcs2.get(k).getName().equals(dcsname)){
		        			concordclientmodel.setDirectConversation(dcs2.get(k));
		        		}
		        	}
		            messageView.getItems().clear();
		            userView.getItems().clear();
		            try {
						concordclientmodel.setUser2(concordclientmodel.GetuserbyName(name2));
						concordclientmodel.setUser(concordclientmodel.GetuserbyName(name1));
					} catch (RemoteException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
		            Platform.runLater(
						  () -> {try {
							model.showDirectConversation();
						} catch (AlreadyBoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NotBoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}});
					set="on";
		        }});}}
		        
	    
	    @FXML
	    void onClickInviteButton(ActionEvent event) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
	    	if (inviteLabel.textProperty().get()!="") {
	    		Platform.runLater(
		   				  () -> {try {
							concordclientmodel.sendinvite(inviteLabel.textProperty().get());
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (AlreadyBoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NotBoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	   			channelView.getItems().clear();
		    	messageView.getItems().clear();
		    	userView.getItems().clear();
	   			try {
					model.showServer();
				} catch (AlreadyBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}});
	   			
	    }
	    
	    }
	    
	    @FXML
	    void onClickKickButton(ActionEvent event) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
	    	if (kickLabel.textProperty().get()!="") {
	   			Platform.runLater(
	   				  () -> {
	   					try {
							concordclientmodel.kickuser(kickLabel.textProperty().get());
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (AlreadyBoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NotBoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	   					channelView.getItems().clear();
		    	messageView.getItems().clear();
		    	userView.getItems().clear();
	   			try {
					model.showServer();
				} catch (AlreadyBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}});
	   			
	    }
	    
	    }

		@Override
		public void notifyFinished2(String name)
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			Platform.runLater(
					  () -> {channelView.getItems().clear();
	    	messageView.getItems().clear();
	    	userView.getItems().clear();
	    	Server server = null;
			try {
				server = concordclientmodel.GetserverbyName(name);
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	    	concordclientmodel.setChannel(concordclientmodel.getChannel());
			try {
				channelView.setItems(concordclientmodel.serverchannels(server));
				userView.setItems(concordclientmodel.getserverusers(concordclientmodel.getServer().getName()));
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				this.setbuttons();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});
			
		}

		@Override
		public void notifyFinished(String name)
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void notifyFinished3(String name)
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			Platform.runLater(
	    			  () -> {messageView.getItems().clear();
	    	Channel channel = null;
			try {
				channel = concordclientmodel.GetchannelbyName(name);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	if (concordclientmodel.getChannel().getName().equals(channel.getName()) && set=="on") {
	    			try {
							messageView.setItems(concordclientmodel.getmessages(channel));
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
						};
	    	}channelView.getItems().clear();
	    	
	    	try {
				channelView.setItems(concordclientmodel.serverchannels(concordclientmodel.getServer()));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.setbuttons();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});
			
		}
		
		@Override
		public void notifyFinished4()
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			Platform.runLater(
					  () -> {userView.getItems().clear();
			try {
				userView.setItems(concordclientmodel.getserverusers(concordclientmodel.getServer().getName()));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});
		}
		
		 
		 @FXML
		    void onClickLogoutButton (ActionEvent event) throws AlreadyBoundException, InterruptedException, NotBoundException {
			 Platform.runLater(
					  () -> { channelView.getItems().clear();
		    userView.getItems().clear();
		    messageView.getItems().clear();
			 model.showLogin();});
		    }
		  
		  


		@Override
		public void notifyFinished5()
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			Platform.runLater(
					  () -> {channelView.getItems().clear();
	    	messageView.getItems().clear();
	    	userView.getItems().clear();
   			});
			
		}
		

	    @FXML
	    void onBackButton(ActionEvent event) throws AlreadyBoundException, InterruptedException, NotBoundException {
	    	Platform.runLater(
	    			  () -> {channelView.getItems().clear();
	    	messageView.getItems().clear();
	    	userView.getItems().clear();
	    	try {
				model.showUser();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});
	    }



		@Override
		public void notifyFinished6()
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			// TODO Auto-generated method stub
			
		}






		
	    
	    
	   		


}
