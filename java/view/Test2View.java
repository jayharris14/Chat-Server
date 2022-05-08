package view;

import java.io.IOException;
import java.rmi.RemoteException;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Main;
import models.ConcordClientModel;
import models.ViewTransitionalModel;
import views.MainController;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(ApplicationExtension.class)
public class Test2View
{
	@Start
	private void start(Stage stage) throws RemoteException {

		ConcordClientModel concordclientmodel=new ConcordClientModel(1151);
		ConcordClientModel.main(null);
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/MainView.fxml"));
		BorderPane view = null;
		try {
			view = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainController cont=loader.getController();
		ViewTransitionalModel vm= new ViewTransitionalModel(view, concordclientmodel);
		cont.setModel(vm, concordclientmodel);
		
		Scene s=new Scene(view);
		stage.setScene(s);
		stage.show();
		
	}
	
	
	private void enterAmt1(FxRobot robot, String text) {
		robot.clickOn("#userNameLabel");
		robot.write(text);
	}
	

	private void enterAmt2(FxRobot robot, String text) {
		robot.clickOn("#passWordLabel");
		robot.write(text);
	}
	
	private void logIn(FxRobot robot) {
		robot.clickOn("#loginbutton");
	}
	
	
	
	
	
	@Test
	@Order(1)
	public void testincorrectlogin(FxRobot robot) {
		enterAmt1(robot, "willi56");
		enterAmt2(robot, "twwt5");
		logIn(robot);
	}
	
	private void createaccount(FxRobot robot) {
		robot.clickOn("#createaccountbutton");
	}
	
	private void enterAmt10(FxRobot robot, String text) {
		robot.clickOn("#usernameLabel");
		robot.write(text);
	}
	
	
	private void enterAmt11(FxRobot robot, String text) {
		robot.clickOn("#passwordLabel");
		robot.write(text);
	}
	

	private void enterAmt12(FxRobot robot, String text) {
		robot.clickOn("#nameLabel");
		robot.write(text);
	}
	

	private void submit(FxRobot robot) {
		robot.clickOn("#submitbutton");
	}
	
	
	@Test
	@Order(2)
	public void testcreateaccount(FxRobot robot) {
		createaccount(robot);
		enterAmt10(robot, "willi65");
		enterAmt11(robot, "twt5");
		enterAmt12(robot, "Mike");
		submit(robot);
	}
	
	private void enterAmt21(FxRobot robot, String text) {
		robot.clickOn("#userNameLabel");
		robot.write(text);
	}
	

	private void enterAmt22(FxRobot robot, String text) {
		robot.clickOn("#passWordLabel");
		robot.write(text);
	}
	
	private void logIn1(FxRobot robot) {
		robot.clickOn("#loginbutton");
	}
	
	
	
	
	
	@Test
	@Order(3)
	public void testcorrectlogin(FxRobot robot) {
		enterAmt21(robot, "willi65");
		enterAmt22(robot, "twwt5");
		logIn1(robot);
	}
	
	
	
	
	/*
	private void checkSubtract(FxRobot robot, String value1, String value2, String result)
	{
		enterAmt1(robot, value1);
		enterAmt2(robot, value2);
		robot.clickOn("#subtractButton");
		checkTotal(robot, result);
		
	}
	
	@Test
	public void testSubtract(FxRobot robot) {
		checkSubtract(robot, "3", "2", "1");
		checkSubtract(robot, "7.5", "2", "5.5");
		checkSubtract(robot, "-1", "2.5", "-3.5");
	}
	
	private void checkDivide(FxRobot robot, String value1, String value2, String result)
	{
		enterAmt1(robot, value1);
		enterAmt2(robot, value2);
		robot.clickOn("#divideButton");
		checkTotal(robot, result);
		
	}
	
	@Test
	public void testDivide(FxRobot robot) {
		checkDivide(robot, "6", "2", "3");
		checkDivide(robot, "3.3", "1", "3.3");
		checkDivide(robot, "2", "-.5", "-4");
	}
	
	private void checkMultiply(FxRobot robot, String value1, String value2, String result)
	{
		enterAmt1(robot, value1);
		enterAmt2(robot, value2);
		robot.clickOn("#multiplyButton");
		checkTotal(robot, result);
		
	}
	
	@Test
	public void testMultiply(FxRobot robot) {
		checkMultiply(robot, "3", "2", "6");
		checkMultiply(robot, "1.5", "2", "3");
		checkMultiply(robot, "3.5", "-2", "-7");
	}*/
}