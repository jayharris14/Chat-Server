package views;

	import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
	import javafx.scene.control.TextField;
import models.ConcordClientModel;
import models.ViewTransitionModelInterface;

	public class UserInfoController {
		ViewTransitionModelInterface model;
		ConcordClientModel newmodel;
	
		public void setModel(ViewTransitionModelInterface viewModel, ConcordClientModel secondnewmodel) {
			model = viewModel;
			newmodel=secondnewmodel;
		}


	    @FXML
	    private TextField profileLabel;

	    @FXML
	    private TextField usernameLabel;

	    @FXML
	    private TextField passwordLabel;

	    @FXML
	    private TextField nameLabel;


	    @FXML
	    void onClickSubmit(ActionEvent event) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
	    	Platform.runLater(
		  			  () -> {
	    	if (profileLabel.textProperty().get()!="") {
	    		try {
					newmodel.Setprofile(profileLabel.textProperty().get());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    	if (usernameLabel.textProperty().get()!="") {
	    		try {
					newmodel.Setusername(usernameLabel.textProperty().get());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    	if (passwordLabel.textProperty().get()!="") {
	    		try {
					newmodel.Setpassword(passwordLabel.textProperty().get());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    	if (nameLabel.textProperty().get()!="") {
	    		try {
					newmodel.Name(nameLabel.textProperty().get());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
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


}
