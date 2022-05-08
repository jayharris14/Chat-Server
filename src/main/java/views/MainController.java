package views;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.ConcordClientModel;
import models.ConcordServer;
import models.ViewTransitionModelInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController {
	
	ViewTransitionModelInterface model;
	ConcordClientModel concordclientmodel;
	
	
	public void setModel(ViewTransitionModelInterface newModel, ConcordClientModel model2)
	{
		model=newModel;
		concordclientmodel=model2;
	}
	
	@FXML
	private TextField userNameLabel;

	@FXML
	private TextField passWordLabel;


	

    @FXML
    void onClickLogIn(ActionEvent event) throws RemoteException, MalformedURLException, NotBoundException, AlreadyBoundException, InterruptedException {
    	String c=concordclientmodel.verified(userNameLabel.textProperty().get(), passWordLabel.textProperty().get());
    	System.out.println(c);
    	if (c.equals("permission granted")) {
    		model.showUser();
    	}
    	else {
    		model.showMainError();
    		
    	}
        }
    @FXML
    void onClickCreateAccount(ActionEvent event) {
    	model.showCreateAccount();
    }
    

    }



