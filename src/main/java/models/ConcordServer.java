package models;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class ConcordServer extends UnicastRemoteObject
implements RMIObserved, ConcordServerInterface, Serializable
{ 

		
	private static final long serialVersionUID= -2;
	private static final String SERIALIZED_FILE_NAME1="Concord.xml";
	
	private int visits=0;
	private String c;
	String run;
	Concord concord;
    Server server;
    ConcordServer concordserver;
	User user;
	ArrayList<User> UM;
	ArrayList<Server> SM;
	ArrayList<Integer> userids;
	int totalpoints;
	User admin;
	ArrayList<User> leaders;
	Date date;
	Timestamp lastlogin;
	public static int getPort() {
		return port;
	}


	public static void setPort(int port) {
		ConcordServer.port = port;
	}
	
	ArrayList<User> blocks;
	String check;
	DirectConversationManager DirectConversationManager=new DirectConversationManager();
	DirectConversation directconversation;
	public DirectConversationManager getDirectConversationManager() {
		return DirectConversationManager;
	}


	public void setDirectConversationManager(DirectConversationManager directConversationManager) {
		DirectConversationManager = directConversationManager;
	}


	public ArrayList<DirectConversation> getDCM() {
		return DCM;
	}


	public void setDCM(ArrayList<DirectConversation> dCM) {
		DCM = dCM;
	}

	ArrayList<DirectConversation> DCM= new ArrayList<DirectConversation>();
	Channel channel;
    Role role;
    UserManager usermanager;
	private String password;
	String Y;
	ServerManager servermanager;
	ArrayList<Channel> channels;
	ArrayList<Server> invites=new ArrayList<Server>();
	
	public Timestamp getlastuserlogin(String u) throws RemoteException {
		Timestamp timestamp=null;
		if(this.usermanager!=null) {
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).userName.equals(u)) {
				timestamp=this.usermanager.UM.get(i).getLastlogin();
			}
		}}
		this.storeToDisk();
		return timestamp;
		
	}

	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Timestamp getLastlogin() {
		return lastlogin;
	}


	public void setLastlogin(Timestamp lastlogin) {
		this.lastlogin = lastlogin;
	}


	public ArrayList<Server> getInvites() {
		return invites;
	}


	public void setInvites(ArrayList<Server> invites) {
		this.invites = invites;
	}


	public ArrayList<Channel> getChannels() {
		return channels;
	}


	public void setChannels(ArrayList<Channel> channels) {
		this.channels = channels;
	}

	static int port;





	public Server getServer() {
		return server;
	}


	public ServerManager getServermanager() {
		return servermanager;
	}


	public void setServermanager(ServerManager servermanager) {
		this.servermanager = servermanager;
	}


	public void setServer(Server server) {
		this.server = server;
	}

	
	public String verify(String u, String pw) {
		visits++;
		System.out.println("verifying");
		int d = 0;
		check="";
		ConcordServer diskf2=this.ReadFromDisk();
		if (diskf2!=null && diskf2.usermanager!=null )
		{
		for (int i=0; i<diskf2.usermanager.UM.size(); i++) {
			if (diskf2.usermanager.UM.get(i).userName.equals(u))
			{	System.out.println("hey");
				check="yes";
				d=i;
			}
		}
		if (check.equals("yes")){
			if (this.usermanager.UM.get(d).password.equals(pw)) {
				c="permission granted";
			}
				
		}
		else {
			c="Access Denied";
			check="no";
		}
		}
		else {
			c="Access Denied";
		}
		this.storeToDisk();
		return c;
	}
	
	public void setthelastlogin(User user) throws RemoteException{
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).userName.equals(user.userName)) {
				date = new Date();
				lastlogin = new Timestamp(date.getTime());
				this.usermanager.UM.get(i).setLastlogin(lastlogin);
			}
		}
		this.storeToDisk();
	}
	
	public Channel getChannel() {
		return channel;
	}


	public void setChannel(Channel channel) {
		this.channel = channel;
	}


	public int addnumbers(int a, int b) throws RemoteException {
		visits++;
		System.out.println("Answering Questions");
		return a+b;
	}
	
	public static void main(String[] args) throws AlreadyBoundException, RemoteException
	{
		try
		{
			ConcordServer M = new ConcordServer();
			Registry registry=LocateRegistry.createRegistry(1151);
			registry.bind("Concord", M);
			System.out.println("The RMI_Server is ready and running");
			
			
		 }catch (RemoteException e)
		{
			 e.printStackTrace();
		} 
	}
		
	


	public int getVisits() {
		return visits;
	}


	public void setVisits(int visits) {
		this.visits = visits;
	}
	
	public void createdirectconversation(String name, String name2) throws RemoteException{
		DirectConversation directconversation=new DirectConversation();
		User user1=this.getuserbyname(name);
		User user2=this.getuserbyname(name2);
		directconversation.users.add(user1);
		directconversation.users.add(user2);
		directconversation.setName(user1.userName+user2.userName);
		this.DirectConversationManager.DCM.add(directconversation);
		
		this.storeToDisk();
		
	}
	
	public void createdcmessage(String message, User user, String name) throws RemoteException{
		Message newmessage=new Message();
		newmessage.setContent(message);
		newmessage.setUser(user);
		for (int i=0; i<this.DirectConversationManager.DCM.size(); i++) {
			if (this.DirectConversationManager.DCM.get(i).name.equals(name)) {
				this.DirectConversationManager.DCM.get(i).messages.add(newmessage);
			}
		}
		this.storeToDisk();
	}
	
	public ArrayList<DirectConversation> getdirectconversations(User user) {
		ArrayList<DirectConversation> directonversations=new ArrayList<DirectConversation>();
		for (int j=0; j<this.usermanager.UM.size(); j++) {
			if (this.usermanager.UM.get(j).userName.equals(user.userName)){
				for (int i=0; i<this.DirectConversationManager.DCM.size(); i++){
					if (this.DirectConversationManager.DCM.get(i).users.contains(user)) {
						directonversations.add(this.DirectConversationManager.DCM.get(i));
					}
				}
			}
		}this.storeToDisk();
		return directonversations;
	}
	
	public ArrayList<Message> getdirectconversationmessages(String name) throws RemoteException{
		ArrayList<Message> messages = null;
		for (int i=0; i<this.DirectConversationManager.DCM.size(); i++) {
			if (this.DirectConversationManager.DCM.get(i).name.equals(name)) {
				messages=this.DirectConversationManager.DCM.get(i).messages;
			}
		}
		this.storeToDisk();
		return messages;
		
	}
		
		public ArrayList<DirectConversation> getpastconversations(User user) {
			ArrayList<DirectConversation> UserDC=new ArrayList<DirectConversation>();
			for (int i=0; i < DCM.size(); i++) {
				if (DCM.get(i).users.contains(user)==true) {
					UserDC.add(DCM.get(i));
					
				}
			}
			return UserDC;
		}
	
	public Integer SendInvite(User user, String name, Server server) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		int a=0;
		int m=0;
		for (int i=0; i<this.servermanager.SM.size(); i++) {
			try {
				{
					if (this.servermanager.SM.get(i).name.equals(server.name)) {
						for (int j=0; j<this.usermanager.UM.size(); j++) {
							if (this.usermanager.UM.get(j).userName.equals(name)) {
								for (int c=0; c<this.usermanager.UM.get(j).Blocks.size(); c++) {
								if (this.usermanager.UM.get(j).Blocks.get(c).userName.equals(user.userName)){
									m=1;
									
								}}
								if (m!=1){
									for (int k=0; k<this.servermanager.SM.get(i).users1.size(); k++) {
										for (int d=0; d<this.servermanager.SM.get(i).users1.get(k).getBlocks().size(); d++) {
										if (this.servermanager.SM.get(i).users1.get(k).Blocks.get(d).userName.equals(user.userName)) {
											m=1;
										}}}}
								if (m!=1) {
									this.usermanager.UM.get(j).invites.add(server);
									a=this.usermanager.UM.get(j).id;}
								
					}
					
				}
						}}} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
						}
		}
		notifyObservers(name);
		this.storeToDisk();
		return a;
		}
	
	public User finduser(String name)
	{
		User user=new User();
		for (int j=0; j<this.usermanager.UM.size(); j++) {
			if (this.usermanager.UM.get(j).userName.equals(name)) {
				user=this.usermanager.UM.get(j);
			}
		}
		this.storeToDisk();
		return user;		
	}

	
	public void kick(Server server, String name, User user) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		User checkadmin = null;
		for (int a=0; a<this.usermanager.UM.size(); a++) {
			if (this.usermanager.UM.get(a).equals(user.userName)){
				checkadmin=this.usermanager.UM.get(a);
			}
		}
		for (int i=0; i<this.servermanager.SM.size(); i++) {
					if (this.servermanager.SM.get(i).name.equals(server.name)) {
						for (int j=0; j<this.usermanager.UM.size(); j++) {
							if (this.usermanager.UM.get(j).userName.equals(name)) {
								if(this.servermanager.SM.get(i).getAdmin().userName.equals(user.userName)) {
									this.servermanager.SM.get(i).server.remove(this.usermanager.UM.get(j));
									this.servermanager.SM.get(i).userids1.remove(user.id);
									this.servermanager.SM.get(i).users1.remove(user.id);
								}
								
							
		
	
								
	
	}}}}
		notifyObservers5();}
	
	
	
	public Channel addChannel(User admin, String name, Server server) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException{
		if (check=="yes") {
			channel= new Channel();
			channel.setName(name);
			ConcordServer diskf2=new ConcordServer();
			for (int i=0; i<this.servermanager.SM.size(); i++) {
				if (this.servermanager.SM.get(i).name.equals(server.name)) {
					this.servermanager.SM.get(i).channels.add(channel);
				}
			}
			
		}
		notifyObservers2(name);
		this.storeToDisk();
		return channel;
	}
	
	public ArrayList<Message> getchannelmessages(Channel channel) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		System.out.println(channel+"helloj");
		ArrayList<Message> messages=channel.getMessages();
		this.storeToDisk();
		return messages;
	}
	
	public ArrayList<User> getdcusers(User user, User user2){
		String name=user.userName+user2.userName;
		ArrayList<User> users= new ArrayList<User>();
		for (int i=0; i<this.DirectConversationManager.DCM.size(); i++) {
			if (this.DirectConversationManager.DCM.get(i).name.equals(name));
				for (int j=0; j<this.DirectConversationManager.DCM.get(i).users.size(); j++) {
					users.add(this.DirectConversationManager.DCM.get(i).users.get(j));
				}
		}
		this.storeToDisk();
		return users;
	}


	public String deleteChannel(User admin, Channel channel) {
		if (check=="yes") {
			Y=server.deleteChannel(admin, channel);
			if (Y=="yes") {
				run="success";
			}
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	public String addPin(Channel channel) {
		if (check=="yes") {
			server.addPin(channel);
				run="success";
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	public String unPin(Channel channel) {
		if (check=="yes") {
			server.unPin(channel);
				run="success";
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	public void addMember(User user, Server server) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		Role role=new Role();
		role.name="member";
		RoleBuilder rolebuilder= new RoleBuilder();
		setRoleBuilder(user, role);
		if (check=="yes") {
			for (int i=0; i<this.servermanager.SM.size(); i++) {
				if (this.servermanager.SM.get(i).name.equals(server.name)) {
					this.servermanager.SM.get(i).addMember(user);
				}
			}
		}
		this.storeToDisk();
	}
	public String removeMember(User admin, User user) {
		if (check=="yes"){
			server.removeMember(user);
			if (Y=="yes") {
				run="success";
			}
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	
	public ArrayList<DirectConversation> getalldirectconversations() {
		ArrayList<DirectConversation> directconversations= new ArrayList<DirectConversation>();
			for (int i=0; i<this.DirectConversationManager.DCM.size(); i++) {
				directconversations.add(this.DirectConversationManager.DCM.get(i));
		}
			this.storeToDisk();
			return directconversations;
	}
	
	public String changeRole(Role newrole, User user) {
		if (check=="yes") {
			server.changeRole(newrole, user);
				run="success";
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	
	public void addblock(String blockname, String username) {
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).userName.equals(username)) {
				User user1=this.getuserbyname(blockname);
				this.usermanager.UM.get(i).Blocks.add(user1);
			}
		}this.storeToDisk();
	}
	
	
	public String sendMessage(Message message, User user, User user2)
	{
		if (check=="yes") {
			directconversation.sendMessage(message, user, user2);
			run="success";
		}
		else {
			run="not authorized";
		}
		this.storeToDisk();
		return run;
	}
	public DirectConversation getDirectconversation() {
		return directconversation;
	}


	public void setDirectconversation(DirectConversation directconversation) {
		this.directconversation = directconversation;
	}


	public String setRoleBuilder(User user, Role role)
	{ConcordServer diskf2=this.ReadFromDisk();
		if (check=="yes") {
			this.server.setRoleBuilder(user, role);
			run="success";
			
		}
		else {
			run="not authorized";
			
		}
		this.concordserver=diskf2;
		this.storeToDisk();
		return run;
	}
	
	public void setPassword(User newuser, String password) {
		newuser.setPassword(password);
		this.storeToDisk();
	}

		public void addBlock(User BlockedUser)
		{
			user.addBlock(user);
			this.storeToDisk();
		}
		public void removeBlock(User BlockedUser)
		{
			user.removeBlock(user);
			this.storeToDisk();
		}
		public void setProfileData(String profileData)
		{
			user.setProfileData(profileData);
			this.storeToDisk();
		}
		public void setUserName(User newuser, String realName, String userName)
		{	
			newuser.realName=realName;
			newuser.userName=userName;
			this.storeToDisk();
			
		}
		
		
	
	
	public void storeToDisk()
	{
		XMLEncoder encoder=null;
		try {
			encoder=new XMLEncoder(new BufferedOutputStream
					(new FileOutputStream("Concord.xml")));
		}catch(FileNotFoundException fileNotFound) {
			System.out.println("ERROR: While Creating or Opening");
			
		}
	
		encoder.writeObject(this);
		encoder.close();

	}


	public User getuserbyname(String username) {
		ConcordServer diskf2=this.ReadFromDisk();
		if (diskf2.usermanager!=null)
		{
		for (int i=0; i<diskf2.usermanager.UM.size(); i++) {
			if (diskf2.usermanager.UM.get(i).userName.equals(username)) {
				user=diskf2.usermanager.UM.get(i);
			}
		}
		
	}return user;
	}
	
	public Server getserverbyname(String name) {
		ConcordServer diskf2=this.ReadFromDisk();
		if (diskf2.usermanager!=null)
		{
		for (int i=0; i<this.servermanager.SM.size(); i++) {
			if (this.servermanager.SM.get(i).name.equals(name)) {
				server=this.servermanager.SM.get(i);
			}
		}
		}
		return server;
	}
		
		public Channel getchannelbyname(String name) {
			ConcordServer diskf2=this.ReadFromDisk();
			if (diskf2.usermanager!=null)
			{
			for (int i=0; i<this.servermanager.SM.size(); i++) {
				for (int j=0; j<this.servermanager.SM.get(i).channels.size(); j++) {
					if (this.servermanager.SM.get(i).channels.get(j).name.equals(name)){
						channel=this.servermanager.SM.get(i).channels.get(j);
				}
			
			}
		
	}
	}
			return channel;
		}
	
	public UserManager getUsermanager() {
		return usermanager;
	}


	public void setUsermanager(UserManager usermanager) {
		this.usermanager = usermanager;
	}


	public ConcordServer ReadFromDisk() 
	{	
			String decide="";
			XMLDecoder decoder=null;
			ConcordServer concorddata=null;
			try {
				decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("Concord.xml")));
			} catch (FileNotFoundException e) {
				System.out.println("ERROR: File Concord.xml not found");
				decide="y";
			}
			if(decide!="y") {
			{
					
				concorddata=(ConcordServer)decoder.readObject();
			}
			}
			return concorddata;
			}
			
			


	public boolean serverequals(ConcordServer that) {
		ConcordServer x;
		x=this.ReadFromDisk();
		boolean y;
		if ((server.server.size())==(that.server.server.size())){
			y=true;
		}
		else {
			y=false;
		}
			return y;
			
		}
	
	public boolean channelsequals(ConcordServer that) {
		ConcordServer x;
		x=this.ReadFromDisk();
		boolean y;
		if (server.channels.size()==(that.server.channels.size())){
			y=true;
		}
		else {
			y=false;
		}
			return y;
			
		}
	
	public boolean directconversationequals(ConcordServer that) {
		ConcordServer x;
		x=this.ReadFromDisk();
		boolean y;
		if (directconversation.messages.size()==(that.directconversation.messages.size())){
			y=true;
		}
		else {
			y=false;
		}
			return y;
			
		}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	public void CreateUser(String realname, String username, String password) throws RemoteException, AlreadyBoundException{
		ConcordServer diskf2=this.ReadFromDisk();
		blocks=new ArrayList<User>();
		totalpoints=0;
		if (diskf2!=null)
		{
		if (diskf2.usermanager==null) {
		ArrayList<User> UM=new ArrayList<User>();
		this.usermanager=new UserManager(UM);
		user=new User(null, username, null, realname, this.setuserid(), blocks, null, password, totalpoints, null);
		this.usermanager.UM=usermanager.addUser(user);
		}
		else {
		user=new User(null, username, null, realname, this.setuserid(), blocks, null, password, totalpoints, null);
		this.UM=diskf2.usermanager.UM;
		this.usermanager=diskf2.usermanager;
		this.usermanager.UM=usermanager.addUser(user);
		}}
		if (diskf2==null){
			ArrayList<User> UM=new ArrayList<User>();
			this.usermanager=new UserManager(UM);
			user=new User(null, username, null, realname, this.setuserid(), blocks, null, password, totalpoints, null);
			this.usermanager.UM=this.usermanager.addUser(user);
			
		}
		this.storeToDisk();
	}

	public void CreateMessage(User user1, Server server, Channel channel, String message) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		Message newmessage=new Message();
		newmessage.setContent(message);
		newmessage.setUser(user1);
		for (int i=0; i<this.servermanager.SM.size(); i++) {
			if (this.servermanager.SM.get(i).name.equals(server.name)) {
				for (int j=0; j<this.servermanager.SM.get(i).channels.size(); j++) {
					if (this.servermanager.SM.get(i).channels.get(j).name.equals(channel.name)) {
						this.servermanager.SM.get(i).channels.get(j).messages.add(newmessage);
					}
				}
			}
		}
		notifyObservers3(channel.name);
		this.storeToDisk();
	}
	
	public ArrayList<User> getserverusers(String name) throws IndexOutOfBoundsException{
		ArrayList<User> susers=new ArrayList<User>();
		for (int i=0; i<this.servermanager.SM.size(); i++) {
			if (this.servermanager.SM.get(i).name.equals(server.name)) {
				for (int j=0; j<this.servermanager.SM.get(i).users1.size(); j++) {
					susers.add(this.servermanager.SM.get(i).users1.get(j));
				}
			}
			}
		return susers;
	}

	/**
	 * @param usermanager
	 * @throws AlreadyBoundException 
	 */
	public ConcordServer() throws RemoteException, AlreadyBoundException {
		super();
	
	}
	public ArrayList<Server> getuserservers(User user) {
		ConcordServer diskf2=this.ReadFromDisk();
		ArrayList<Server> S=new ArrayList<Server>();
		if (diskf2!=null)
		{
		String userservers="";
		if (this.servermanager!=null) {
			if (this.servermanager.SM!=null) {
				if (this.servermanager.SM.size()>0) {
		for (int i=0; i < this.servermanager.SM.size(); i++) {
			System.out.println(diskf2.servermanager.SM.size());
			if (this.servermanager.SM.get(i).userids1.contains(user.id)) {
				S.add(this.servermanager.SM.get(i));
			}

			}
		}}}
		}
		this.storeToDisk();
		System.out.println(S);
		return S;
		}
		

	public ArrayList<Integer> getUserids() {
		return userids;
	}


	public void setUserids(ArrayList<Integer> userids) {
		this.userids = userids;
	}
	


	public Server CreateServer(String name, User user) throws RemoteException, AlreadyBoundException {
		ConcordServer diskf2=this.ReadFromDisk();
		Role role=new Role();
		role.name="admin";
		RoleBuilder rolebuilder= new RoleBuilder();
		ArrayList<Integer> userids=new ArrayList<Integer>();
		ArrayList<Channel> channels=new ArrayList<Channel>();
		if (diskf2!=null)
		{
		if (diskf2.servermanager==null) {
			ArrayList<Server> SM=new ArrayList<Server>();
			this.servermanager=new ServerManager(SM);
			HashMap<User, Role> users=new HashMap<User, Role>();
			server=new Server(users, channels, null, name, user, rolebuilder, userids);
			setRoleBuilder(user, role);
			this.userids=this.server.addMember(user);
			this.servermanager.addServer(this.server);
			this.servermanager.SM.get(this.servermanager.SM.size()-1).setAdmin(user);
			}
			else {
			HashMap<User, Role> users=new HashMap<User, Role>();
			server=new Server(users, channels, null, name, user, rolebuilder, userids);
			setRoleBuilder(user, role);
			this.server.admin=user;
			this.servermanager=diskf2.servermanager;
			this.servermanager.SM=diskf2.servermanager.SM;
			this.userids=this.server.addMember(user);
			this.servermanager=diskf2.servermanager;
			this.servermanager.SM=this.servermanager.addServer(this.server);
			this.servermanager.SM.get(this.servermanager.SM.size()-1).setAdmin(user);
			}}
			else {
				HashMap<User, Role> users=new HashMap<User, Role>();
				ArrayList<Server> SM=new ArrayList<Server>();
				this.servermanager=new ServerManager(SM);
				server=new Server(users, channels, null, name, user, rolebuilder, userids);
				setRoleBuilder(user, role);
				this.server.admin=user;
				this.userids=this.server.addMember(user);
				this.servermanager.addServer(this.server);
				this.servermanager.SM.get(this.servermanager.SM.size()-1).setAdmin(user);
				
			
			
		}
		System.out.println(servermanager.SM.get(0));
		this.storeToDisk();
		return server;
	
		
	}
	
	public int setuserid() {
		ConcordServer diskf2=this.ReadFromDisk();
		int id=1;
		if (diskf2!=null)
		{
		if (diskf2.usermanager!=null) {
			id=diskf2.usermanager.UM.size()+1;
		}
		else {
			id=1;
		}}
		
		return id;
	}


	@Override
	public UserManager convertusermanager() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String setRoleBuilder(User user, Role role, Server server) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Channel> getserverchannels(Server server) {
		ConcordServer diskf2=this.ReadFromDisk();
		ArrayList<Channel> C=new ArrayList<Channel>();
		if (diskf2!=null)
		{
		if (this.servermanager!=null) {
			for (int i=0; i<this.servermanager.SM.size(); i++) {
				if (this.servermanager.SM.get(i).name.equals(server.name)) {
					if (this.servermanager.SM.get(i).channels!=null) {
						if (this.servermanager.SM.get(i).channels.size()>0) {
							for (int j=0; j < this.servermanager.SM.get(i).channels.size(); j++) {
								C.add(this.servermanager.SM.get(i).channels.get(j));
				}
			}
		}}}
		this.storeToDisk();
		}
	
	}	
		System.out.println(C);
		return C;
	}


	@Override
	public ArrayList<Channel> getuserchannels(User user) {
		
		return null;
	}


	@Override
	public String addChannel(User admin, Channel channel) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	ArrayList<RMIObserver> observers=new ArrayList<RMIObserver>();
	@Override
	public void addObserver(RMIObserver o) throws RemoteException {
		observers.add(o);
		
	}

	@Override
	public void removeObserver(RMIObserver o) throws RemoteException{
		observers.remove(o);
		}
	public void notifyObservers(String name) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		for(RMIObserver o: observers)
		{
			o.notifyFinished(name);
		}
	}
	public void notifyObservers2(String name) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		for(RMIObserver o: observers)
		{
			o.notifyFinished2(name);
		}
	}
	public void notifyObservers3(String name) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		for(RMIObserver o: observers)
		{
			o.notifyFinished3(name);
		}
	}
	
	public void notifyObservers4() throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		for(RMIObserver o: observers)
		{
			o.notifyFinished4();
		}
		
	}
	
	public void notifyObservers5() throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		for(RMIObserver o: observers)
		{
			o.notifyFinished5();
		}
	}
	public void notifyObservers6() throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
			for(RMIObserver o: observers)
			{
				o.notifyFinished6();
			}
		
	}

	@Override
	public ArrayList<Server> getinvitedservers(User user) throws RemoteException {
		ArrayList<Server> servers=new ArrayList<Server>();
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).getUserName().equals(user.getUserName())) {
				for (int j=0; j<this.usermanager.UM.get(i).invites.size(); j++) {
					servers.add(this.usermanager.UM.get(i).invites.get(j));
				}
			}
			}
		return servers;
		}


	@Override
	public String invite(User admin, User User) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	public void removeinvitedserver(User user, Server server) {
		ArrayList<Server> servers=new ArrayList<Server>();
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).userName.equals(user.userName)) {
				for (int j=0; j<this.usermanager.UM.get(i).invites.size(); j++) {
					if (this.usermanager.UM.get(i).invites.get(j).name.equals(server.name)) {
						this.usermanager.UM.get(i).invites.remove(j);
					}
			}
		
	}



	}}
	
	public ArrayList<User> getuserblocks(String username) throws RemoteException {
		ArrayList<User> blocks= new ArrayList<User>();
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).userName.equals(username)) {
				for (int j=0; j<this.usermanager.UM.get(i).Blocks.size(); j++) {
				blocks.add(this.usermanager.UM.get(i).Blocks.get(j));}
				
			}
		}
		return blocks;
	}


	@Override
	public void notifyObserver4()
			throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void notifyObservers()
			throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void createdirectconversation(String name, User user) throws RemoteException {
		// TODO Auto-generated method stub
		
	}


	
	public void setprofile(String profileLabel, int id) throws RemoteException {
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).id==id)
			{this.usermanager.UM.get(i).setProfileData(profileLabel);
			}}
		this.storeToDisk();
		
	}



	public void setusername(String usernameLabel, int id) throws RemoteException {
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).id==id)
			{this.usermanager.UM.get(i).setUserName(usernameLabel);;
			}}
		this.storeToDisk();
		
	}

		


	public void setpassword(String passwordLabel, int id) throws RemoteException {
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).id==id)
			{this.usermanager.UM.get(i).setPassword(passwordLabel);;
			}}
		this.storeToDisk();
	}

		



	public void setname(String nameLabel, int id) throws RemoteException {
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).id==id)
			{this.usermanager.UM.get(i).setRealName(nameLabel);
			}}
		this.storeToDisk();
	}

	@Override
	public String getprofile(User user) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		String a=null;
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).id==user.id);
			{a=this.usermanager.UM.get(i).getProfileData();
			}}
		notifyObservers6();
		this.storeToDisk();
		return a;
		
	}



	@Override
	public String getusername(User user) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		String a= null;
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).id==user.id)
			{a=this.usermanager.UM.get(i).getUserName();;
			}}
		notifyObservers6();
		return a;
	}

		


	@Override
	public String getpassword(User user) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		String a = null;
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).id==user.id)
			{a=this.usermanager.UM.get(i).getPassword();;
			}}
		notifyObservers6();
		return a;
	}

	public void addpoints(Points points, User user) throws RemoteException{
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).id==user.id) {
				int currentpoints=(this.usermanager.UM.get(i).totalpoints)+points.total();
				this.usermanager.UM.get(i).setTotalpoints(currentpoints);
			}
			}this.storeToDisk();
	}
		



	public int getTotalpoints() {
		return totalpoints;
	}


	public void setTotalpoints(int totalpoints) {
		this.totalpoints = totalpoints;
	}


	@Override
	public String getname(User user) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		String a=null;
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).id==user.id)
			{a=this.usermanager.UM.get(i).getRealName();
			}
			}
		notifyObservers6();
		return a;
	}


	@Override
	public String gettotalpoints(User user) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
		int a=0;
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).id==user.id)
			{a=this.usermanager.UM.get(i).getTotalpoints();
			}
			}
		notifyObservers6();
		String b=Integer.toString(a);
		return b;
		
	}
	
	public ArrayList<User> sortleaderboard() throws RemoteException{
		ArrayList<User> leaders=new ArrayList<User>();
		int max=-1;
		User maxuser=null;
		System.out.println(this.usermanager.UM.size());
		for (int i=0; i<this.usermanager.UM.size(); i++) {
			if (this.usermanager.UM.get(i).getTotalpoints()>=max) {
				leaders.add(this.usermanager.UM.get(i));
				max=this.usermanager.UM.get(i).getTotalpoints();
				maxuser=this.usermanager.UM.get(i);
			}
			else {
				int c=leaders.size();
				for (int j=0; j<c; j++) {
					if (c==1) {
						leaders.add(0, this.usermanager.UM.get(i));
					}
					else if (this.usermanager.UM.get(i).getTotalpoints()<=leaders.get(j).getTotalpoints()) {
						leaders.add(j, this.usermanager.UM.get(i));
					}
				}
			}
		}
		this.storeToDisk();
		return leaders;
	}
	
	
	

}
		
	

		
	






	

	


	/**
	 * User user
	 */



	/**
	 *
	 */
	

	

	


		
		




		




