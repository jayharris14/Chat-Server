package view;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Main;
import models.ConcordClientModel;
import models.ConcordServer;
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
	private void start(Stage stage) throws RemoteException, AlreadyBoundException {
		
		ConcordServer cs=new ConcordServer();
		cs.main(null);
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
	
	private void enterserver(FxRobot robot, String text) {
		robot.clickOn("#serverlabel");
		robot.write(text);
		Assertions.assertThat("#serverlabel").as("party");
	}
	
	private void enterserver2(FxRobot robot, String text) {
		robot.clickOn("#serverlabel");
		robot.write(text);
		Assertions.assertThat("#serverlabel").as("movies");
	}
	
	
	private void channel1(FxRobot robot) {
		robot.clickOn(LabeledMatchers.hasText("food"));
	}
	
	private void editinfo(FxRobot robot) {
		robot.clickOn(LabeledMatchers.hasText("Edit Info"));
	}
	private void editusername(FxRobot robot, String text) {
		robot.clickOn("#usernamelabel");
		robot.write(text);
	}
	private void editpassword(FxRobot robot, String text) {
		robot.clickOn("#passwordlabel");
		robot.write(text);
	}

	private void editname(FxRobot robot, String text) {
		robot.clickOn("#namelabel");
		robot.write(text);
	}
	private void editprofile(FxRobot robot, String text) {
		robot.clickOn("#datalabel");
		robot.write(text);
	}
	private void channel2(FxRobot robot) {
		robot.clickOn(LabeledMatchers.hasText("drinks"));
	}
	private void channel3(FxRobot robot) {
		robot.clickOn(LabeledMatchers.hasText("favorites"));
	}
	
	private void entermessage1(FxRobot robot, String text) {
		robot.clickOn("#messagelabel");
		robot.write(text);
		Assertions.assertThat("#messagelabel").as("who's getting what");
		
	}
	private void entermessage2(FxRobot robot, String text) {
		robot.clickOn("#messagelabel");
		robot.write(text);
		Assertions.assertThat("#messagelabel").as("i'll get pizza");
	}
	private void entermessage3(FxRobot robot, String text) {
		robot.clickOn("#messagelabel");
		robot.write(text);
		Assertions.assertThat("#messagelabel").as("i'll get waters");
	}
	
	private void entermessage4(FxRobot robot, String text) {
		robot.clickOn("#messagelabel");
		robot.write(text);
		Assertions.assertThat("#messagelabel").as("i can get cookies");
	}
	
	private void entermessage5(FxRobot robot, String text) {
		robot.clickOn("#messagelabel");
		robot.write(text);
		Assertions.assertThat("#messagelabel").as("i'll get sodas");
	}
	
	private void entermessage6(FxRobot robot, String text) {
		robot.clickOn("#messagelabel");
		robot.write(text);
		Assertions.assertThat("#messagelabel").as("snow white's my fav");
	}
	
	
	private void send(FxRobot robot)
	{
		robot.clickOn("#messagebutton");
	}
	
	private void add(FxRobot robot)
	{
		robot.clickOn("#serverbutton");
	}
	
	
	
	private void enterchannel1(FxRobot robot, String text) {
		robot.clickOn("#channellabel");
		robot.write(text);
		Assertions.assertThat("#channellabel").as("food");
	}
	
	private void enterchannel2(FxRobot robot, String text) {
		robot.clickOn("#channellabel");
		robot.write(text);
		Assertions.assertThat("#channellabel").as("drinks");
		
	}
	
	private void enterchannel3(FxRobot robot, String text) {
		robot.clickOn("#channellabel");
		robot.write(text);
		Assertions.assertThat("#channellabel").as("favorites");
	}
	
	
	private void clearmessage(FxRobot robot) {
		robot.clickOn("#messagelabel");
		robot.eraseText(20);
		robot.eraseText(20);
	}
	
	private void addchannelbutton(FxRobot robot)
	{
		robot.clickOn("#channelbutton");
	}
	
	private void enteruser(FxRobot robot, String text) {
		robot.clickOn("#invite");
		robot.write(text);
		Assertions.assertThat("#invite").as("jhoya101");
		
	}
	private void enteruser2(FxRobot robot, String text) {
		robot.clickOn("#invite");
		robot.write(text);
		Assertions.assertThat("#invite").as("wllie1");
	}private void enteruser3(FxRobot robot, String text) {
		robot.clickOn("#invite");
		robot.write(text);
		Assertions.assertThat("#invite").as("5donald");
	}
	
	private void invitebutton(FxRobot robot)
	{
		robot.clickOn("#invitebutton");
	}
	
	private void serverbutton(FxRobot robot)
	{
		
		robot.clickOn(LabeledMatchers.hasText("party"));
	}
	
	private void secondserver(FxRobot robot)
	{
		
		robot.clickOn(LabeledMatchers.hasText("movies"));
	}
	
	private void invitedserverbutton(FxRobot robot)
	{
		
		robot.clickOn(LabeledMatchers.hasText("party"));
	}
	private void invitedserverbutton2(FxRobot robot)
	{
		
		robot.clickOn(LabeledMatchers.hasText("movies"));
	}
	
	private void acceptserverbutton(FxRobot robot)
	{
		
		robot.clickOn(LabeledMatchers.hasText("Accept"));
	}
	
	private void enterAmt1(FxRobot robot, String text) {
		robot.clickOn("#userNameLabel");
		robot.write(text);
		Assertions.assertThat("#userNameLabel").as("jhoya101");
	}

	private void enterAmt2(FxRobot robot, String text) {
		robot.clickOn("#passWordLabel");
		robot.write(text);
		Assertions.assertThat("#passWordLabel").as("map55");
	}
	
	private void enterAmtf(FxRobot robot, String text) {
		robot.clickOn("#userNameLabel");
		robot.write(text);
		Assertions.assertThat("#userNameLabel").as("jhoya1");
	}

	private void enterAmtfp(FxRobot robot, String text) {
		robot.clickOn("#passWordLabel");
		robot.write(text);
		Assertions.assertThat("#passWordLabel").as("map5");
	}
	private void logIn(FxRobot robot) {
		robot.clickOn("#loginbutton");
	}
	
	private void createaccount(FxRobot robot) {
		robot.clickOn(LabeledMatchers.hasText("Create Account"));
	}
	
	private void enterAmt10(FxRobot robot, String text) {
		robot.clickOn("#usernameLabel");
		robot.write(text);
		Assertions.assertThat("#usernameLabel").as("jhoya101");
		
	}
	
	
	
	private void enterAmt11(FxRobot robot, String text) {
		robot.clickOn("#passwordLabel");
		robot.write(text);
		Assertions.assertThat("#passWordLabel").as("map55");
	}
	

	private void enterAmt12(FxRobot robot, String text) {
		robot.clickOn("#nameLabel");
		robot.write(text);
	}
	
	private void enterAmt31(FxRobot robot, String text) {
		robot.clickOn("#userNameLabel");
		robot.write(text);
		Assertions.assertThat("#userNameLabel").as("5donald");
		
	}
	

	private void enterAmt32(FxRobot robot, String text) {
		robot.clickOn("#passWordLabel");
		robot.write(text);
		Assertions.assertThat("#passWordLabel").as("dxxd");
	}
	
	private void enterAmt220(FxRobot robot, String text) {
		robot.clickOn("#usernameLabel");
		robot.write(text);
		Assertions.assertThat("#userameLabel").as("willie1");
	}
	
	
	
	private void enterAmt221(FxRobot robot, String text) {
		robot.clickOn("#passwordLabel");
		robot.write(text);
		Assertions.assertThat("#passwordLabel").as("bobo");
	}
	

	private void enterAmt222(FxRobot robot, String text) {
		robot.clickOn("#nameLabel");
		robot.write(text);
		Assertions.assertThat("#nameLabel").as("will");
	}
	
	private void enterAmt330(FxRobot robot, String text) {
		robot.clickOn("#usernameLabel");
		robot.write(text);
		Assertions.assertThat("#usernameLabel").as("5donald");
	}
	
	
	
	private void enterAmt331(FxRobot robot, String text) {
		robot.clickOn("#passwordLabel");
		robot.write(text);
		Assertions.assertThat("#passwordLabel").as("dxxd");
	}
	

	private void enterAmt332(FxRobot robot, String text) {
		robot.clickOn("#nameLabel");
		robot.write(text);
		Assertions.assertThat("#nameLabel").as("Don");
	}
	
	
	

	private void submit(FxRobot robot) {
		robot.clickOn("#submitbutton");
	}
	
	
	

	
	private void enterAmt21(FxRobot robot, String text) {
		robot.clickOn("#userNameLabel");
		robot.write(text);
		Assertions.assertThat("#userNameLabel").as("willie1");
	}
	

	private void enterAmt22(FxRobot robot, String text) {
		robot.clickOn("#passWordLabel");
		robot.write(text);
		Assertions.assertThat("#passWordLabel").as("dxxd");
	}
	
	private void logIn1(FxRobot robot) {
		robot.clickOn("#loginbutton");
	}
	
	private void logout(FxRobot robot) {
		robot.clickOn("#logoutbutton");
	}
	
	private void logout2(FxRobot robot) {
		robot.clickOn(LabeledMatchers.hasText("Logout"));
	}
	
	private void back(FxRobot robot) {
		robot.clickOn(LabeledMatchers.hasText("Back"));
	}
	
	@Test
	@Order(1)
	public void test1(FxRobot robot) throws Throwable, RuntimeException, InvocationTargetException {
		enterAmt1(robot, "jhoya101");
		enterAmt2(robot, "map55");
		Thread.sleep(1000);
		logIn(robot);
		Thread.sleep(1000);
		createaccount(robot);
		enterAmt10(robot, "jhoya101");
		enterAmt11(robot, "map55");
		enterAmt12(robot, "jay");
		submit(robot);
		createaccount(robot);
		enterAmt220(robot, "willie1");
		enterAmt221(robot, "bobo");
		enterAmt222(robot, "will");
		submit(robot);
		createaccount(robot);
		enterAmt330(robot, "5donald");
		enterAmt331(robot, "dxxd");
		enterAmt332(robot, "Don");
		submit(robot);
		enterAmt1(robot, "jhoya101");
		enterAmt2(robot, "map55");
		logIn1(robot);
		Thread.sleep(1000);
		enterserver(robot, "party");
		Thread.sleep(500);
		add(robot);
		Thread.sleep(1000);
		serverbutton(robot);
		Thread.sleep(1000);
		enteruser2(robot, "willie1");
		Thread.sleep(1000);
		invitebutton(robot);
		Thread.sleep(1000);
		enterchannel1(robot, "food");
		Thread.sleep(1000);
		addchannelbutton(robot);
		Thread.sleep(1000);
		enteruser3(robot, "5donald");
		Thread.sleep(1000);
		invitebutton(robot);
		Thread.sleep(1000);
		logout2(robot);
		enterAmt21(robot, "willie1");
		enterAmt22(robot, "bobo");
		logIn1(robot);
		Thread.sleep(1000);
		invitedserverbutton(robot);
		Thread.sleep(1000);
		acceptserverbutton(robot);
		Thread.sleep(1000);
		serverbutton(robot);
		Thread.sleep(1000);
		channel1(robot);
		Thread.sleep(1000);
		entermessage1(robot, "who's getting what");
		Thread.sleep(1000);
		send(robot);
		Thread.sleep(1000);
		logout2(robot);
		enterAmt31(robot, "5donald");
		enterAmt32(robot, "dxxd");
		logIn1(robot);
		Thread.sleep(1000);
		invitedserverbutton(robot);
		Thread.sleep(1000);
		acceptserverbutton(robot);  
		Thread.sleep(1000);
		serverbutton(robot);
		Thread.sleep(1000);
		channel1(robot);
		Thread.sleep(1000);
		entermessage2(robot, "i'll get pizza");
		Thread.sleep(1000);
		send(robot);
		Thread.sleep(1000);
		enterchannel2(robot, "drinks");
		Thread.sleep(1000);
		addchannelbutton(robot);
		Thread.sleep(1000);
		channel2(robot);
		Thread.sleep(1000);
		clearmessage(robot);
		clearmessage(robot);
		entermessage3(robot, "i'll get waters");
		Thread.sleep(1000);
		send(robot);
		logout2(robot);
		enterAmt1(robot, "jhoya101");
		enterAmt2(robot, "map55");
		logIn1(robot);
		Thread.sleep(1000);
		serverbutton(robot);
		Thread.sleep(1000);
		channel1(robot);
		Thread.sleep(1000);
		entermessage4(robot, "i can get cookies");
		Thread.sleep(1000);
		send(robot);
		Thread.sleep(1000);
		channel2(robot);
		Thread.sleep(1000);
		clearmessage(robot);
		clearmessage(robot);
		clearmessage(robot);
		entermessage5(robot, "ill get sodas");
		Thread.sleep(1000);
		send(robot);
		Thread.sleep(1000);
		back(robot);
		Thread.sleep(1000);
		enterserver2(robot, "movies");
		Thread.sleep(1000);
		add(robot);
		Thread.sleep(1000);
		secondserver(robot);
		Thread.sleep(1000);
		enteruser3(robot, "5donald");
		Thread.sleep(1000);
		invitebutton(robot);
		Thread.sleep(1000);
		enterchannel3(robot, "favorites");
		Thread.sleep(1000);
		addchannelbutton(robot);
		Thread.sleep(1000);
		channel3(robot);
		Thread.sleep(1000);
		entermessage6(robot, "snow whites my fav");
		Thread.sleep(1000);
		send(robot);
		Thread.sleep(1000);
		logout2(robot);
		enterAmt21(robot, "5donald");
		enterAmt22(robot, "dxxd");
		logIn1(robot);
		invitedserverbutton2(robot);
		Thread.sleep(1000);
		acceptserverbutton(robot);  
		Thread.sleep(1000);
		secondserver(robot);
		Thread.sleep(1000);
		channel3(robot);
		Thread.sleep(1000);
		entermessage1(robot, "me too");
		Thread.sleep(1000);
		send(robot);
		logout2(robot);
		enterAmt1(robot, "jhoya101");
		enterAmt2(robot, "map55");
		logIn1(robot);
		logout(robot);
		
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

	@Override
	public void showDirectConversation() throws AlreadyBoundException, InterruptedException, NotBoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showUserInfo() throws AlreadyBoundException, InterruptedException, NotBoundException {
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