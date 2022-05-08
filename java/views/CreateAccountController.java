package views;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.ViewTransitionModelInterface;
import models.ViewTransitionalModel;
import models.ConcordClientModel;

public class CreateAccountController {
		
		ViewTransitionModelInterface model;
		ConcordClientModel newmodel;
	
		public void setModel(ViewTransitionModelInterface viewModel, ConcordClientModel secondnewmodel) {
			model = viewModel;
			newmodel=secondnewmodel;
		}

	    @FXML
	    private TextField usernameLabel;

	    @FXML
	    private TextField passwordLabel;

	    @FXML
	    private TextField nameLabel;

	    @FXML
	    void onClickSubmit(ActionEvent event) throws RemoteException, AlreadyBoundException {
	    	if (usernameLabel.textProperty().get()=="" || passwordLabel.textProperty().get()=="" || nameLabel.textProperty().get()=="") {
	    		model.showError();
	    	}
	    	else {
	    		newmodel.createuser(nameLabel.textProperty().get(), usernameLabel.textProperty().get(), passwordLabel.textProperty().get());
	    		model.showLogin();
	    	}
	       
	 

	    }


	}



