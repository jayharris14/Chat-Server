package models;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import views.CreateAccountController;
import views.DirectConversationController;
import views.MainController;
import views.ServerController;
import views.UserInfoController;
import views.UserPageController;
import java.rmi.*;

public class ViewTransitionalModel implements ViewTransitionModelInterface
{

		
		BorderPane mainview;
		ConcordClientModel model;
		
		public ViewTransitionalModel(BorderPane view, ConcordClientModel model2)
		{
			mainview=view;
			model=model2;

		}
		public void showUser() throws AlreadyBoundException, InterruptedException, NotBoundException {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ViewTransitionalModel.class.getResource("../views/UserPageView.fxml"));
			try {
				Pane view=loader.load();
				mainview.setCenter(view);
				UserPageController cont=loader.getController();
				cont.setModel(this, model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void showLogin() {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ViewTransitionalModel.class.getResource("../views/MainView.fxml"));
			try {
				Pane view=loader.load();
				mainview.setCenter(view);
				MainController cont=loader.getController();
				cont.setModel(this, model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void showCreateAccount() {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ViewTransitionalModel.class.getResource("../views/CreateAccountView.fxml"));
			try {
				Pane view=loader.load();
				mainview.setCenter(view);
				CreateAccountController cont=loader.getController();
				cont.setModel(this, model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void showError() {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ViewTransitionalModel.class.getResource("../views/ErrorCreateAccountView.fxml"));
			try {
				Pane view=loader.load();
				mainview.setCenter(view);
				CreateAccountController cont=loader.getController();
				cont.setModel(this, model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void showMainError() {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ViewTransitionalModel.class.getResource("../views/ErrorMainView.fxml"));
			try {
				Pane view=loader.load();
				mainview.setCenter(view);
				MainController cont=loader.getController();
				cont.setModel(this, model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void showServer() throws AlreadyBoundException, NotBoundException, InterruptedException {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ViewTransitionalModel.class.getResource("../views/ServerView.fxml"));
			try {
				Pane view=loader.load();
				mainview.setCenter(view);
				ServerController cont=loader.getController();
				cont.setModel(this, model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void showInvitationView() throws AlreadyBoundException, InterruptedException, NotBoundException {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ViewTransitionalModel.class.getResource("../views/InvitationView.fxml"));
			try {
				Pane view=loader.load();
				mainview.setCenter(view);
				UserPageController cont=loader.getController();
				cont.setModel(this, model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void showDirectConversation() throws AlreadyBoundException, InterruptedException, NotBoundException {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ViewTransitionalModel.class.getResource("../views/DirectConversationView.fxml"));
			try {
				Pane view=loader.load();
				mainview.setCenter(view);
				DirectConversationController cont=loader.getController();
				cont.setModel(this, model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void showUserInfo() throws AlreadyBoundException, InterruptedException, NotBoundException {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ViewTransitionalModel.class.getResource("../views/UserInfoView.fxml"));
			try {
				Pane view=loader.load();
				mainview.setCenter(view);
				UserInfoController cont=loader.getController();
				cont.setModel(this, model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
}
