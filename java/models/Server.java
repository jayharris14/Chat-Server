package models;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;



public class Server implements Serializable

{
	User user=new User();
	HashMap<User, Role> server= new HashMap<User, Role>();
	ArrayList<Integer> userids1=new ArrayList<Integer>();
	ArrayList<Channel> channels= new ArrayList<Channel>();
	ArrayList<Channel> pins= new ArrayList<Channel>();
	HashMap<User, Role> users= new HashMap<User, Role>();
	ArrayList<User>users1=new ArrayList<User>();
	String name;
	RoleBuilder rolebuilder= new RoleBuilder();
    ServerManager servermanager;
    String autho="yes";
    Role role;
    User admin=new User();
    public User getAdmin() {
		return admin;
	}

    private static final long serialVersionUID= -2;


	public void setAdmin(User admin) {
		this.admin = admin;
	}

	int visits;
    static User fakeuser=new User();
	
	Server(ServerManager servermanager){
		servermanager.addServer(this);
	}
	
	
	
	/**
	 * @param server
	 * @param channels
	 * @param pins
	 * @param name
	 * @param rolebuilder
	 * @param servermanager
	 * @throws RemoteException 
	 */
	
	public Server(HashMap<User, Role> server, ArrayList<Channel> channels, ArrayList<Channel> pins, String name, User admin,
			RoleBuilder rolebuilder, ArrayList<Integer> userids) throws RemoteException {
		super();
		this.server = server;
		this.channels = channels;
		this.pins = pins;
		this.name = name;
		this.rolebuilder = rolebuilder;
		this.admin=admin;
		this.userids1=userids;
	}
	public Server() throws RemoteException {
		this(null, null, null, "party", null, null, null);
	}
	



	public void addadmin(User admin) {
		System.out.println(this.admin.userName);
		this.server.put(this.admin, this.admin.roles.get(this));
		this.userids1.add(this.admin.id);
	}
	
	public void setadmin(User admin, Role role)
	{	
		visits++;
		Role x=rolebuilder.createUserRole(role.name);
		System.out.println(visits);
	}

	public void setRoleBuilder(User user, Role role)
	{
		Role x=rolebuilder.createUserRole(role.name);
		this.user=user;
		System.out.println(user);
		this.user.setRole(x, this);
		
	}

	public ArrayList<Integer> getUserids1() {
		return userids1;
	}



	public void setUserids1(ArrayList<Integer> userids1) {
		this.userids1 = userids1;
	}



	@Override
	public String toString() {
		return this.name;
	}



	public String invite(User admin, User User, Server server) {
		String e="";
		String c=User.userName;
		User.addInvite(server);
		e=c+ " invited to "+ this.name;
		return e;
	}

		
	
	public String kick(User admin, User user) {
		String w="";
		if (admin.roles.get(this).removeMember==true) {
		String a=user.userName;
		String b=name;
		this.removeMember(user);
		w=a+ "kicked from"+ b;
		}
		else {
			w="permission denied";
		}
		return w;
		
	}
	
	public ArrayList<Channel> addChannel(User admin, Channel channel) {
		if (admin.roles.get(this).addChannel==true) {
			System.out.println("hello");
		this.channels.add(channel);
		this.setChannels(channels);
		}
		else {
			autho="no";
			System.out.println("bye");
		}
		return channels;
	}
	public String deleteChannel(User admin, Channel channel) {
		if (admin.roles.get(this).removeChannel==true) {
		channels.remove(channel);
		}
		else {
			autho="no";
		}
		return autho;
		
	}
	
	public void addPin(Channel channel) {
		pins.add(channel);
	}
	
	public void unPin(Channel channel) {
		pins.remove(channel);
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList<Integer> addMember(User user){
		
		this.server.put(user, user.roles.get(this));
		this.userids1.add(user.id);
		this.users1.add(user);
		
		return userids1;
	}
	
	public void removeMember(User user) {
		this.server.remove(user);
		this.userids1.remove(user.id);
		this.users1.remove(user);
	}
	
	public void changeRole(Role newrole, User user) {
		server.put(user, newrole);
	}

	public void put(User user, Role role) {
		// TODO Auto-generated method stub
		
	}

	public HashMap<User, Role> getServer() {
		return server;
	}

	public void setServer(HashMap<User, Role> server) {
		this.server = server;
	}

	public ArrayList<Channel> getChannels() {
		return channels;
	}

	public void setChannels(ArrayList<Channel> channels) {
		this.channels = channels;
	}

	public ArrayList<Channel> getPins() {
		return pins;
	}

	public void setPins(ArrayList<Channel> pins) {
		this.pins = pins;
	}

	public RoleBuilder getRolebuilder() {
		return rolebuilder;
	}

	public void setRolebuilder(RoleBuilder rolebuilder) {
		this.rolebuilder = rolebuilder;
	}

	public ServerManager getServermanager() {
		return servermanager;
	}

	public void setServermanager(ServerManager servermanager) {
		this.servermanager = servermanager;
	}
	
	
}


	
	
	
