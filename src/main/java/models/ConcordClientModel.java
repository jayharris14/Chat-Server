package models;



import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;



public class ConcordClientModel extends UnicastRemoteObject implements RMIObserver, Serializable{
	static int port;
	static ConcordServerInterface cs;
	UserManager usermanager;
	User user= new User();
	Server server;
	ServerManager servermanager;
	ObservableList<Button> userserverlist= FXCollections.observableArrayList();
	ObservableList<Button> serverchannellist= FXCollections.observableArrayList();
	ObservableList<Message> messagelist= FXCollections.observableArrayList();
	ObservableList<Button> serverlist= FXCollections.observableArrayList();
	ObservableList<Button> userlist= FXCollections.observableArrayList();
	Role role=new Role();
	Channel channel=new Channel();
	DirectConversation directconversation;
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
		if (iverify.equals("permission granted")) {
			this.user=cs.getuserbyname(u);
			RMIObserved observed = (RMIObserved) Naming.lookup("rmi://10.14.1.70:1151/Concord");
			observed.addObserver(this);
		}
		return iverify;
	}
	
	public User GetuserbyName(String name) throws RemoteException {
		user=cs.getuserbyname(name);
		return user;
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
	public void notifyFinished(String name) {
		System.out.println(name +" was notfied");
		
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
		cs.CreateMessage(user, server, channel, message);
		return channel;
		
	}


	public void sendinvite(String name) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		cs.SendInvite(user, name, server);
		
	}


	public void addMember() throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		cs.addMember(user, server);
		cs.removeinvitedserver(user, server);
		
	}
	
	public void removeinvites() throws RemoteException {
		cs.removeinvitedserver(user, server);
	}
	
	public void kickuser(String name) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException{
		cs.kick(server, name);
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


	public void notifyFinished4()
			throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		cs.notifyObserver4();
		
	}


	public void setDirectConversation(DirectConversation directConversation) {
		this.directconversation=directConversation;
		
	}


	public ArrayList<DirectConversation> getDirectConversations() throws RemoteException {
		ArrayList<DirectConversation> directonversations=cs.getdirectconversations(user);
		return directonversations;
	}


	public ObservableList<Message> getdirectconversationmessages(String name) throws RemoteException {
		ArrayList<Message> messages=cs.getdirectconversationmessages(name);
		for (int i=0; i<messages.size(); i++) {
			messagelist.add(messages.get(i));
		}
		return messagelist;
	}


	@Override
	public void notifyFinished5()
			throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		// TODO Auto-generated method stub
		
	}









	
	
}
