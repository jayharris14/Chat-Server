package views;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.Channel;
import models.ConcordClientModel;
import models.DirectConversation;
import models.Message;
import models.User;
import models.ViewTransitionModelInterface;
import models.ViewTransitionalModel;



public class DirectConversationController {
	
	ConcordClientModel concordmodel;
	ViewTransitionalModel viewtransitionalmodel;

	
	public void setModel(ViewTransitionalModel viewTransitionalModel, ConcordClientModel model) throws RemoteException, AlreadyBoundException, InterruptedException, MalformedURLException, NotBoundException {
	        concordmodel=model;
	  		viewtransitionalmodel=viewTransitionalModel;
	  		String name1=concordmodel.getUser().getUserName();
				String name2=concordmodel.getUser2().getUserName();
				String dcsname=name1+name2;
		
	  		Platform.runLater(
				  () -> {
					  userView.getItems().clear();
					  try {
						userView.setItems(concordmodel.dcusers());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  });
	  		
	}
	@FXML
    private Button add;

    @FXML
    private ListView<Message> messageView;

    @FXML
    private TextField messageLabel;

    @FXML
    private ListView<Button> userView;

    @FXML
    void onBackButton(ActionEvent event) {
    	Platform.runLater(
  			  () -> {
  	messageView.getItems().clear();
  	userView.getItems().clear();
  	try {
			viewtransitionalmodel.showServer();
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

    @FXML
    void onClickLogoutButton(ActionEvent event) {
    	 Platform.runLater(
				  () -> { 
	    userView.getItems().clear();
	    messageView.getItems().clear();
		 viewtransitionalmodel.showLogin();});
    }

    @FXML
    void onClickMessageButton(ActionEvent event) {
    	Platform.runLater(
				  () -> {if (messageLabel.textProperty().get()!="") {
 			DirectConversation directconversation=null;
			try {
				String name1=concordmodel.getUser().getUserName();
				String name2=concordmodel.getUser2().getUserName();
				String dcsname=name1+name2;
				concordmodel.Createdcmessage(messageLabel.textProperty().get(), dcsname);
				
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			messageView.getItems().clear();
 					try {
 						String name1=concordmodel.getUser().getUserName();
 						String name2=concordmodel.getUser2().getUserName();
 						String dcsname=name1+name2;
						messageView.setItems(concordmodel.getdirectconversationmessages(dcsname));
 						
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
 					try {
 						userView.getItems().clear();
						userView.setItems(concordmodel.getserverusers(concordmodel.getServer().getName()));
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
 				}});
 			}
    
  	

    }



	

