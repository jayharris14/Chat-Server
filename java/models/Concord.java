package models;

import java.util.ArrayList;

public class Concord {
	
	Server server;
	Role role;
	static ArrayList<User> UM=new ArrayList<User>();
	static ArrayList<Server> SM=new ArrayList<Server>();
	DirectConversation directconversation;
	public Server getServer() {
		return server;
	}
	public void setServer(Server server) {
		this.server = server;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public ArrayList<User> getUM() {
		return UM;
	}
	public void setUM(ArrayList<User> uM) {
		UM = uM;
	}
	public ArrayList<Server> getSM() {
		return SM;
	}
	public void setSM(ArrayList<Server> sM) {
		SM = sM;
	}
	public DirectConversation getDirectconversation() {
		return directconversation;
	}
	public void setDirectconversation(DirectConversation directconversation) {
		this.directconversation = directconversation;
	}
	/**
	 * @param uM
	 * @param sM
	 */
	public Concord(ArrayList<User> uM, ArrayList<Server> sM) {
		super();
		UM = uM;
		SM = sM;
	}
	
	public Concord() {
		this(UM, SM);
	}
	
	

}
