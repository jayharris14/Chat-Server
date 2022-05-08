package models;

import java.io.Serializable;

public class Role implements Serializable
{	
	public String name;
	boolean addModerator;
	boolean addChannel;
	boolean addAdmin;
	boolean removeMember;
	boolean removeModerator;
	boolean inviteUser;
	boolean removeChannel;
	boolean addMember;
	
	public Role(String name, boolean addModerator, boolean addChannel, boolean addAdmin, boolean removeMember,
			boolean removeModerator, boolean inviteUser, boolean removeChannel, boolean addMember) {
		super();
		this.name = name;
		this.addModerator = addModerator;
		this.addChannel = addChannel;
		this.addAdmin = addAdmin;
		this.removeMember = removeMember;
		this.removeModerator = removeModerator;
		this.inviteUser = inviteUser;
		this.removeChannel = removeChannel;
		this.addMember = addMember;
	}


	public Role() {
		this("admin", true, true, true, true, true, true, true, true);
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean isAddMember() {
		return addMember;
	}


	public void setAddMember(boolean addMember) {
		this.addMember = addMember;
	}


	public boolean isAddModerator() {
		return addModerator;
	}


	public boolean isAddChannel() {
		return addChannel;
	}


	public boolean isAddAdmin() {
		return addAdmin;
	}


	public boolean isRemoveMember() {
		return removeMember;
	}


	public boolean isRemoveModerator() {
		return removeModerator;
	}


	public boolean isInviteUser() {
		return inviteUser;
	}


	public boolean isRemoveChannel() {
		return removeChannel;
	}


	/**
	 * @return the addModerator

	/**
	 * @param addModerator the addModerator to set
	 */
	public void setAddModerator(boolean addModerator)
	{
		this.addModerator = addModerator;
	}
	/**
	 * @return the addChannell
	 *

	/**
	 * @param addChannell the addChannell to set
	 */
	public void setAddChannel(boolean addChannell)
	{
		this.addChannel = addChannell;
	}
	/**
	 * @return the addAdmin
	 */

	public void setAddAdmin(boolean addAdmin)
	{
		this.addAdmin = addAdmin;
	}
	/**
	 * @return the removeMember
	 */
	/**
	 * @param removeMember the removeMember to set
	 */
	public void setRemoveMember(boolean removeMember)
	{
		this.removeMember = removeMember;
	}
	/**
	 * @return the removeModerator
	 */
	public void setRemoveModerator(boolean removeModerator)
	{
		this.removeModerator = removeModerator;
	}
	/**
	 * @return the inviteUser
	 *
	/**
	 * @param inviteUser the inviteUser to set
	 */
	public void setInviteUser(boolean inviteUser)
	{
		this.inviteUser = inviteUser;
	}
	/**
	 * @return the removeChannel
	 */
	public void setRemoveChannel(boolean removeChannel)
	{
		this.removeChannel = removeChannel;
	}
	
	

}
