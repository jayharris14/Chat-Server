package models;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class UserManager
{
	User user;
	ArrayList<User> UM=new ArrayList<User>();
	
	

	
	/**
	 * @param user
	 * @param uM
	 */
	
	

	/**
	 * 
	 */
	
	public User getUser() {
		return user;
	}

	/**
	 * 
	 */
	
	

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @param user
	 */
	
	
	

	public ArrayList<User> getUM() {
		return UM;
	}

	/**
	 * @param uM2 
	 * @param user2 
	 * 
	 */


	public void setUM(ArrayList<User> uM) {
		UM = uM;
	}

	/**
	 * @param user
	 * @param uM
	 */
	public UserManager(ArrayList<User> uM) {
		super();
		if (this.UM==null) {
			this.UM=uM;
		}
		
	}
	public UserManager() {
		this(null);
	}

	public ArrayList<User> addUser(User user) {
		this.UM.add(user);
		return UM;
	}
	
	


	
	
}
