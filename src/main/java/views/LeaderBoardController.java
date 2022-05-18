package views;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.ConcordClientModel;
import models.RMIObserved;
import models.RMIObserver;
import models.User;
import models.ViewTransitionalModel;

public class LeaderBoardController extends UnicastRemoteObject implements RMIObserver, Serializable{

		
		
		public LeaderBoardController() throws RemoteException, MalformedURLException, NotBoundException {
			super();
		}

		ConcordClientModel concordmodel;
		ViewTransitionalModel viewtransitionalmodel;

		
		public void setModel(ViewTransitionalModel viewTransitionalModel, ConcordClientModel model) throws RemoteException, AlreadyBoundException, InterruptedException, MalformedURLException, NotBoundException, NullPointerException {
		        concordmodel=model;
		  		viewtransitionalmodel=viewTransitionalModel;
		  		Platform.runLater(
		  			  () -> {
		  		try {
		  			leaderboardView.getItems().clear();
		  			 leaderboardView.getItems().clear();
					leaderboardView.setItems(concordmodel.getleaderboard());
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
		 private ListView<String> leaderboardView;
		 
		 public void onClickBack() {
			 try {
				 leaderboardView.getItems().clear();
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
		 }



		@Override
		public void notifyFinished(String name)
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			// TODO Auto-generated method stub
			
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

		@Override
		public void notifyFinished6()
				throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			// TODO Auto-generated method stub
			
		}
		 

	}

	

