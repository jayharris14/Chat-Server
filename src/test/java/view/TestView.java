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
	
	private void enterserver(FxRobot robot, String text) {
		robot.clickOn("#serverlabel");
		robot.write(text);
	}
	
	
	private void channel(FxRobot robot) {
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
	}

	private void enterAmt2(FxRobot robot, String text) {
		robot.clickOn("#passWordLabel");
		robot.write(text);
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
		logIn(robot);
		createaccount(robot);
		enterAmt10(robot, "jhoya101");
		enterAmt11(robot, "map55");
		enterAmt12(robot, "jay");
		submit(robot);
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
		enterAmt1(robot, "jhoya101");
		enterAmt2(robot, "map55");
		logIn1(robot);
		enterserver(robot, "party");
		add(robot);
		serverbutton(robot);
		enteruser(robot, "willie1");
		invitebutton(robot);
		Thread.sleep(2000);
		enterchannel(robot, "food");
		addchannelbutton(robot);
		enteruser(robot, "5donald");
		invitebutton(robot);
		logout2(robot);
		enterAmt21(robot, "willie1");
		enterAmt22(robot, "bobo");
		logIn1(robot);
		invitedserverbutton(robot);
		acceptserverbutton(robot);
		serverbutton(robot);
		channel(robot);
		entermessage(robot, "who's getting what");
		send(robot);
		logout2(robot);
		enterAmt1(robot, "5donald");
		enterAmt2(robot, "dxxd");
		logIn1(robot);
		invitedserverbutton(robot);
		acceptserverbutton(robot);  
		serverbutton(robot);
		Thread.sleep(2000);
		channel(robot);
		entermessage(robot, "i'll get pizza");
		send(robot);
		enterchannel(robot, "drinks");
		addchannelbutton(robot);
		channel2(robot);
		clearmessage(robot);
		clearmessage(robot);
		entermessage(robot, "i'll get waters");
		send(robot);
		logout2(robot);
		enterAmt21(robot, "jhoya101");
		enterAmt22(robot, "map55");
		logIn1(robot);
		serverbutton(robot);
		channel(robot);
		entermessage(robot, "i can get cookies");
		send(robot);
		channel2(robot);
		clearmessage(robot);
		clearmessage(robot);
		clearmessage(robot);
		entermessage(robot, "ill get sodas");
		send(robot);
		back(robot);
		enterserver(robot, "movies");
		add(robot);
		secondserver(robot);
		enteruser(robot, "5donald");
		invitebutton(robot);
		Thread.sleep(2000);
		enterchannel(robot, "favorites");
		addchannelbutton(robot);
		channel3(robot);
		clearmessage(robot);
		clearmessage(robot);
		clearmessage(robot);
		entermessage(robot, "snow whites my fav");
		send(robot);
		logout2(robot);
		enterAmt1(robot, "5donald");
		enterAmt2(robot, "dxxd");
		logIn1(robot);
		invitedserverbutton2(robot);
		acceptserverbutton(robot);  
		Thread.sleep(2000);
		secondserver(robot);
		channel3(robot);
		entermessage(robot, "me too");
		send(robot);
		logout2(robot);
		enterAmt21(robot, "jhoya101");
		enterAmt22(robot, "map55");
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