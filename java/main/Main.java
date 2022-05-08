package main;

import java.util.Random;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.ViewTransitionalModel;
import models.ConcordClientModel;
import models.ConcordServer;
import views.MainController;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		ConcordClientModel concordclientmodel=new ConcordClientModel(1151);
		concordclientmodel.main(null);
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/MainView.fxml"));
		BorderPane view=loader.load();
		MainController cont=loader.getController();
		ViewTransitionalModel vm= new ViewTransitionalModel(view, concordclientmodel);
		cont.setModel(vm, concordclientmodel);
		
		Scene s=new Scene(view);
		stage.setScene(s);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}