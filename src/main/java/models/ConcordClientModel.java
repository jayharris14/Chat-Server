package models;



import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;



public class ConcordClientModel extends UnicastRemoteObject implements RMIObserver, Serializable{
	static int port;
	static ConcordServerInterface cs;
	UserManager usermanager;
	User user= new User();
	User user2=new User();
	public User getUser2() {
		return user2;
	}


	public void setUser2(User user2) {
		this.user2 = user2;
	}


	Server server;
	ServerManager servermanager;
	ObservableList<Button> userserverlist= FXCollections.observableArrayList();
	ObservableList<Button> serverchannellist= FXCollections.observableArrayList();
	ObservableList<Message> messagelist= FXCollections.observableArrayList();
	ObservableList<Button> serverlist= FXCollections.observableArrayList();
	ObservableList<Button> userlist= FXCollections.observableArrayList();
	ObservableList<Button> dcuserlist= FXCollections.observableArrayList();
	ObservableList<String> infolist= FXCollections.observableArrayList();
	ObservableList<String> leaderboardlist= FXCollections.observableArrayList();
	ObservableList<User> blocklist= FXCollections.observableArrayList();
	Role role=new Role();
	Channel channel=new Channel();
	DirectConversation directconversation;
	Points points;
	private static final long serialVersionUID= -2;
	
	public ConcordClientModel(int port) throws RemoteException {
		// TODO Auto-generated constructor stub
		this.port=port;
	}
	
	
	public static void main(String[] args) 
	{
	try {
		Registry registry=LocateRegistry.getRegistry(port);
		cs =(ConcordServerInterface) registry.lookup("Concord");
		System.out.println("the client is running");
		/*String accept=invite(User admin, User User) throws RemoteException;
		String kicked=kick(User admin, User user) throws RemoteException;
		String addedc=addChannel(User admin, Channel channel) throws RemoteException;
		String deletedc=deleteChannel(User admin, Channel channel) throws RemoteException;
		String addedp=addPin(Channel channel) throws RemoteException;
		String unpinnedp=unPin(Channel channel) throws RemoteException;
		String addedm=addMember(User admin, User user) throws RemoteException;
		String removedm=removeMember(User admin, User user) throws RemoteException;
		String changedr=changeRole(Role newrole, User user) throws RemoteException;
		String setr=setRoleBuilder(User user, Role role) throws RemoteException;
		String sentm=sendMessage(Message message, User user, User user2) throws RemoteException;
		int a= cs.addnumbers(0, 1);*/
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	


	}
	public String verified(String u, String pw) throws RemoteException, MalformedURLException, NotBoundException{
		String iverify=cs.verify(u, pw);
		Timestamp lastlogin=cs.getlastuserlogin(u);
		if (iverify.equals("permission granted")) {
			this.setUser(cs.getuserbyname(u));
			cs.setthelastlogin(user);
			RMIObserved observed = (RMIObserved) Naming.lookup("rmi://10.14.1.70:1151/Concord");
			observed.addObserver(this);
			Date date = new Date();
			Timestamp current = new Timestamp(date.getTime());
			if (lastlogin==null) {
				points=new FirstLogin();
				cs.addpoints(points, user);
			}
			else if (current.getDate()==lastlogin.getDate()) {
				points=new Return();
				cs.addpoints(points, user);
			}
			else {
			points=new LogIn();
			cs.addpoints(points, user);
			}
		}
		return iverify;
	}
	
	public User GetuserbyName(String name) throws RemoteException {
		User tempuser=cs.getuserbyname(name);
		return tempuser;
	}
	
	public Server GetserverbyName(String name) throws RemoteException {
		server=cs.getserverbyname(name);
		return server;
	}
	
	public Channel GetchannelbyName(String name) throws RemoteException {
		channel=cs.getchannelbyname(name);
		return channel;
	}
	
	public void createuser(String realname, String username, String password) throws RemoteException, AlreadyBoundException {
		cs.CreateUser(realname, username, password);
	}


	public void createserver(String name) throws RemoteException, AlreadyBoundException {
		Server newserver=cs.CreateServer(name, user);
		points=new CreateServer(points);
		
		
	}
	
	public void blockuser(String blockname) throws RemoteException{
		String name=this.user.userName;
		cs.addblock(blockname, name);
	}

	public ObservableList<javafx.scene.control.Button> userservers(User user) throws RemoteException {
		ArrayList<Server> Servers=cs.getuserservers(user);
		ArrayList<Button> buttons=new ArrayList<Button>();
		for (int i=0; i<Servers.size(); i++) {
			Button button = new Button(Servers.get(i).name);
			userserverlist.add(button);
		}
			
		System.out.println(userserverlist);
		return userserverlist;
	}
	
	public ObservableList<javafx.scene.control.Button> dcusers() throws RemoteException {
		ArrayList<User> users=new ArrayList<User>();
		users.add(this.user);
		users.add(this.user2);
		ArrayList<Button> buttons=new ArrayList<Button>();
		for (int i=0; i<2; i++) {
			Button button = new Button(users.get(i).userName);
			dcuserlist.add(button);
		}	
	
		return dcuserlist;
	}
	
	
	
	public ArrayList<Server> getServers() throws RemoteException {
		ArrayList<Server> servers=cs.getuserservers(user);
		return servers;
	}
	
	public ArrayList<Server> getInvites() throws RemoteException {
		ArrayList<Server> invites=cs.getinvitedservers(user);
		return invites;
	}
	
	
	public ArrayList<Channel> getChannels() throws RemoteException {
		ArrayList<Channel> channels=cs.getserverchannels(server);
		return channels;
	}
	public void notifyFinished() {
		System.out.println(" I was notfied");
		
	}
	
	public ObservableList<User> getblocks(User user1) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException{
		ArrayList<User> blocks=new ArrayList<User>();
		int id=this.user.id;
		blocks=cs.getuserblocks(user1.userName);
		for (int i=0; i<blocks.size(); i++) {
			blocklist.add(blocks.get(i));
		}
		return blocklist;
	}
	
	public ObservableList<Button> getserverusers(String name) throws RemoteException{
		ArrayList<User> serverusers=cs.getserverusers(name);
		ArrayList<Button> buttons=new ArrayList<Button>();
		for (int i=0; i<serverusers.size(); i++) {
			Button button = new Button(serverusers.get(i).userName);
			cs.createdirectconversation(name, user);
			userlist.add(button);
		}
		return userlist;
	}
	
	public ArrayList<User> getallusers(String name) throws RemoteException{
		ArrayList<User> serverusers=cs.getserverusers(name);
		return serverusers;
	}
	
	public void creatdcs(String name, String name2) throws RemoteException{
		cs.createdirectconversation(name, name2);
		
	}
	
	public ArrayList<DirectConversation> getpastdcs() throws RemoteException{
		ArrayList<DirectConversation> directconversations=cs.getalldirectconversations();
		return directconversations;
	}
	

	



	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public ObservableList<javafx.scene.control.Button> serverchannels(Server server) throws RemoteException {
		ArrayList<Channel> channels=cs.getserverchannels(server);
		ArrayList<Button> buttons=new ArrayList<Button>();
		for (int i=0; i<channels.size(); i++) {
			Button button = new Button(channels.get(i).name);
			serverchannellist.add(button);
		}
			
		return serverchannellist;
	}


	public Channel createchannel(String name) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		Channel channel=cs.addChannel(user, name, server);
		this.channel=channel;
		points=new CreateChannel(points);
		cs.addpoints(points, user);
		return channel;
	}
	
	public ObservableList<Message> getmessages(Channel channel) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		ArrayList<Message> messages=cs.getchannelmessages(channel);
		for (int i=0; i<messages.size(); i++) {
			messagelist.add(messages.get(i));
		}
		this.channel=channel;
		return messagelist;
	}
	
	public ObservableList<javafx.scene.control.Button> getinvitedservers(User user) throws RemoteException, AlreadyBoundException {
		ArrayList<Server> servers=cs.getinvitedservers(this.user);
		ArrayList<Button> buttons=new ArrayList<Button>();
		for (int i=0; i<servers.size(); i++) {
			Button button = new Button(servers.get(i).name);
			serverlist.add(button);
		}
		return serverlist;
	}



	public Channel getChannel() {
		return channel;
	}


	public void setChannel(Channel channel) {
		this.channel = channel;
	}


	public Server getServer() {
		return server;
	}


	public void setServer(Server server) {
		this.server=server;

		
	}


	public Channel createmessage(String message) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		cs.CreateMessage(this.user, this.server, this.channel, message);
		points=new CreateMessage(points);
		cs.addpoints(points, user);
		return channel;
		
	}


	public void sendinvite(String name) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		cs.SendInvite(user, name, server);
		points=new InviteUser(points);
		cs.addpoints(points, user);
		
	}

	
	public void addMember() throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		cs.addMember(user, server);
		cs.removeinvitedserver(user, server);
		points=new JoinServer(points);
		cs.addpoints(points, user);
		
	}
	
	public void removeinvites() throws RemoteException {
		cs.removeinvitedserver(user, server);
	}
	
	public void kickuser(String name) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException{
		cs.kick(server, name, this.user);
	}
	
	public void submitpoints() {
		try {
			cs.addpoints(points, user);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/*

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


	public void notifyFinished4()
			throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		cs.notifyObserver4();
		
	}
*/

	public void setDirectConversation(DirectConversation directConversation) {
		this.directconversation=directConversation;
		
	}


	public ArrayList<DirectConversation> getDirectConversations() throws RemoteException {
		ArrayList<DirectConversation> directonversations=cs.getdirectconversations(user);
		return directonversations;
	}


	public ObservableList<Message> getdirectconversationmessages(String name) throws RemoteException, NullPointerException {
		ArrayList<Message> messages=cs.getdirectconversationmessages(name);
		for (int i=0; i<messages.size(); i++) {
			messagelist.add(messages.get(i));
		}
		return messagelist;
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
		cs.notifyObserver4();
		
	}


	@Override
	public void notifyFinished5()
			throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		// TODO Auto-generated method stub
		
	}


	public DirectConversation Createdcmessage(String message, String name) throws RemoteException {
		cs.createdcmessage(message, user, name);
		return null;
	}


	public DirectConversation Createdcmessage(String string, User user3, String dcsname) {
		// TODO Auto-generated method stub
		return null;
	}


	public void Setprofile(String string) throws RemoteException {
		cs.setprofile(string, user.id);
	}


	public void Setusername(String usernameLabel) throws RemoteException {
		cs.setusername(usernameLabel, user.id);
	}


	public void Setpassword(String passwordLabel) throws RemoteException{
		cs.setpassword(passwordLabel, user.id);
	}


	public void Name(String nameLabel) throws RemoteException {
		cs.setname(nameLabel, user.id);
		
	}
	
	public ObservableList<String> getinfo() throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		this.user=user;
			for (int i=0; i<4; i++) {
				if (i==0) {
					infolist.add("Username:"+ cs.getusername(user));
				}
				if (i==1) {
					infolist.add("Name:"+cs.getname(user));
				}
				if (i==2) {
					infolist.add("TotalPoints:"+cs.gettotalpoints(user));
				}
				if(i==3) {
					infolist.add("Info:"+cs.getprofile(user));
				}
			}
			return infolist;
		}
	
	public ObservableList<String> getleaderboard() throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException{
		ArrayList<User> leaders=cs.sortleaderboard();
		System.out.println(leaders);
		for (int i=leaders.size()-1; i>0; i--) {
			String b=Integer.toString(leaders.get(i).getTotalpoints());
			leaderboardlist.add(leaders.get(i).userName+": "+b);
		}
		System.out.println(leaderboardlist);
		return leaderboardlist;
	}


	@Override
	public void notifyFinished6()
			throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		// TODO Auto-generated method stub
		
	}

	}




/*
	@Override
	public void notifyFinished()
			throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		// TODO Auto-generated method stub
		
	}*/

/*
	@Override
	public void notifyFinished5()
			throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		// TODO Auto-generated method stub
		
	}*/









	
	

