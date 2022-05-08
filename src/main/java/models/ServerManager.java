package models;

import java.util.ArrayList;

public class ServerManager
{
	Server server;
	ArrayList<Server> SM= new ArrayList<Server>();
	
	public ArrayList<Server> addServer(Server server) {
		this.SM.add(server);
		return SM;
	}
	
	
	
	/**
	 * @param sM
	 */
	public ServerManager(ArrayList<Server> sM) {
		super();
		if (this.SM==null) {
			this.SM=sM;
		}
	}
	
	public ServerManager() {
		this(null);
	}



	public Server getServer() {
		return server;
	}



	public void setServer(Server server) {
		this.server = server;
	}



	public ArrayList<Server> getSM() {
		return SM;
	}



	public void setSM(ArrayList<Server> sM) {
		SM = sM;
	}



	public String getuserservers(User user) {
		String userservers="";
		for (int i=0; i < SM.size(); i++) {
			if (SM.get(i).server.containsKey(user)==true) {
				if (i==0) {
				userservers=userservers +(SM.get(i).name);
			}
				else {
					userservers=userservers +", "+ (SM.get(i).name);
				}
		}
	} return userservers;
}
}