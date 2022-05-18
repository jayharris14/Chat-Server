package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.io.Serializable;
import java.sql.Timestamp;


public class User implements Serializable
{
	String profileData;
	String userName;
	Role role;
	HashMap<Server, Role> roles= new HashMap<Server, Role>();
	UserManager usermanager;
	ArrayList<Server> invites=new ArrayList<Server>();
	int totalpoints;
	Date date = new Date();
	Timestamp lastlogin = new Timestamp(date.getTime());

	
	
	
	public Timestamp getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Timestamp lastlogin) {
		this.lastlogin = lastlogin;
	}

	@Override
	public String toString() {
		return this.userName;
	}

	public ArrayList<Server> getInvites() {
		return invites;
	}

	public void setInvites(ArrayList<Server> invites) {
		this.invites = invites;
	}

	/**
	 * @param profileData
	 * @param userName
	 * @param role
	 * @param realName
	 * @param id
	 * @param blocks
	 * @param profilePic
	 * @param password
	 */
	public User(String profileData, String userName, Role role, String realName, int id, ArrayList<User> blocks,
			String profilePic, String password, int totalpoints, Timestamp lastlogin) {
		super();
		this.profileData = profileData;
		this.userName = userName;
		this.role = role;
		this.realName = realName;
		this.id = id;
		this.Blocks = blocks;
		this.profilePic = profilePic;
		this.password = password;
		this.totalpoints=totalpoints;
		this.lastlogin=lastlogin;
	}

	public int getTotalpoints() {
		return totalpoints;
	}

	public void setTotalpoints(int totalpoints) {
		this.totalpoints = totalpoints;
	}

	public User() {
		this(null, "lou", null,"louise", 1, null, null, "lll", 50, null);
	}
	public void setRole(Role role, Server concordServer) {
		this.role=role;
		roles.put(concordServer, role);
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public HashMap<Server, Role> getRoles() {
		return roles;
	}

	public void setRoles(HashMap<Server, Role> roles) {
		this.roles = roles;
	}

	public UserManager getUsermanager() {
		return usermanager;
	}

	public void setUsermanager(UserManager usermanager) {
		this.usermanager = usermanager;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<User> getBlocks() {
		return Blocks;
	}

	public void setBlocks(ArrayList<User> blocks) {
		Blocks = blocks;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getProfileData() {
		return profileData;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @param profileData
	 * @param userName
	 * @param role
	 * @param roles
	 * @param usermanager
	 * @param realName
	 * @param id
	 * @param blocks
	 * @param profilePic
	 * @param password
	 */
	

	/**
	 * @param userName
	 * @param realName
	 * @param password
	 */
	


	/**
	 * @return the userName
	 */
	public String getUserName()
	{
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	String realName;
	int id;
	ArrayList<User> Blocks;
	String profilePic;
	String password;
	
	public void addInvite(Server server) {
		this.invites.add(server);
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

		public void addBlock(User BlockedUser)
		{
			this.getBlocks().add(BlockedUser);
		}
		public void removeBlock(User BlockedUser)
		{
			Blocks.remove(BlockedUser);
		}
		public void setProfileData(String profileData)
		{
			this.profileData=profileData;
		}
		public void setUserName(String realName, String userName)
		{
			this.realName=realName;
			this.userName=userName;
			
		}
/*
		@Override
		public int hashCode() {
			return Objects.hash(id, password, realName, userName);
		}
*/
	/*	@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			return id == other.id && Objects.equals(password, other.password)
					&& Objects.equals(realName, other.realName) && Objects.equals(userName, other.userName);
		}
		
		*/
		
		
}
