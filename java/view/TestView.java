package view;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
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
import models.ViewTransitionModelInterface;
import models.ViewTransitionalModel;
import views.MainController;
import views.UserPageController;
import views.ServerController;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(ApplicationExtension.class)
public class TestView implements ViewTransitionModelInterface
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
	public void testincorrectlogin(FxRobot robot) throws InterruptedException {
		enterAmt1(robot, "jhoya101");
		enterAmt2(robot, "map55");
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
	public void testcreateaccount(FxRobot robot) throws InterruptedException {
		createaccount(robot);
		enterAmt10(robot, "jhoya101");
		enterAmt11(robot, "map55");
		enterAmt12(robot, "jay");
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
	
	private void logout(FxRobot robot) {
		robot.clickOn("#logoutbutton");
	}
	
	
	
	
	@Test
	@Order(3)
	public void testcorrectlogin(FxRobot robot) throws InterruptedException {
		createaccount(robot);
		enterAmt10(robot, "willie1");
		enterAmt11(robot, "bobo");
		enterAmt12(robot, "will");
		submit(robot);
		createaccount(robot);
		enterAmt10(robot, "5donald");
		enterAmt11(robot, "dxxd");
		enterAmt12(robot, "Don");
		submit(robot);
		
	}
	
	
	
	
	@Test
	@Order(4)
	public void testmakeparty(FxRobot robot) throws InterruptedException{
		enterAmt1(robot, "jhoya101");
		enterAmt2(robot, "map55");
		logIn1(robot);
		enterserver(robot, "party");
		add(robot);
		serverbutton(robot);
		enteruser(robot, "willie1");
		invitebutton(robot);
		enteruser(robot, "5donald");
		invitebutton(robot);
		enterchannel(robot, "food");
		addchannelbutton(robot);
		Thread.sleep(1000);
		entermessage(robot, "who's getting what");
		send(robot);
		logout(robot);
	}
	@Test
	@Order(5)
	public void login(FxRobot robot) throws InterruptedException {
		enterAmt21(robot, "willie1");
		enterAmt22(robot, "bobo");
		logIn1(robot);
		invitedserverbutton(robot);
		acceptserverbutton(robot);
		serverbutton(robot);
		channel(robot);
		entermessage(robot, "i'll get pizza");
		send(robot);
		logout(robot);
	}

		
	
	@Test
	@Order(6)
	public void testcorrectlogin3(FxRobot robot) throws InterruptedException {
		enterAmt1(robot, "donald");
		enterAmt2(robot, "bobo");
		logIn1(robot);
		serverbutton(robot);
		channel(robot);
		entermessage(robot, "great");
		send(robot);
		logout(robot);
		
		
	}
	
	@Test
	@Order(7)
	public void login3(FxRobot robot) throws InterruptedException {
		enterAmt21(robot, "5donald");
		enterAmt22(robot, "dxxd");
		logIn1(robot);
		invitedserverbutton(robot);
		acceptserverbutton(robot);
		serverbutton(robot);
		channel(robot);
		entermessage(robot, "i can get cookies");
		send(robot);
		logout(robot);
		
		
	}
	
	
	
	
	private void enterserver(FxRobot robot, String text) {
		robot.clickOn("#serverlabel");
		robot.write(text);
	}
	
	private void channel(FxRobot robot) {
		robot.clickOn(LabeledMatchers.hasText("food"));
	}
	private void channel2(FxRobot robot) {
		robot.clickOn(LabeledMatchers.hasText("drinks"));
	}
	
	private void entermessage(FxRobot robot, String text) {
		robot.clickOn("#messagelabel");
		robot.write(text);
	}
	
	private void send(FxRobot robot)
	{
		robot.clickOn("#messagebutton");
	}
	
	private void add(FxRobot robot)
	{
		robot.clickOn("#serverbutton");
	}
	
	
	
	private void enterchannel(FxRobot robot, String text) {
		robot.clickOn("#channellabel");
		robot.write(text);
	}
	private void addchannelbutton(FxRobot robot)
	{
		robot.clickOn("#channelbutton");
	}
	
	private void enteruser(FxRobot robot, String text) {
		robot.clickOn("#invitelabel");
		robot.write(text);
	}
	private void invitebutton(FxRobot robot)
	{
		robot.clickOn("#invitebutton");
	}
	
	private void serverbutton(FxRobot robot)
	{
		
		robot.clickOn(LabeledMatchers.hasText("party"));
	}
	
	private void invitedserverbutton(FxRobot robot)
	{
		
		robot.clickOn(LabeledMatchers.hasText("party"));
	}
	
	private void acceptserverbutton(FxRobot robot)
	{
		
		robot.clickOn(LabeledMatchers.hasText("Accept"));
	}
	
	

	@Override
	public void showUser() throws AlreadyBoundException, InterruptedException, NotBoundException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void showLogin() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void showCreateAccount() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void showError() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void showMainError() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void showInvitationView() throws AlreadyBoundException, InterruptedException, NotBoundException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void showServer() throws AlreadyBoundException, NotBoundException, InterruptedException {
		// TODO Auto-generated method stub
		
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