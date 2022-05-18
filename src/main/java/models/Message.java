package models;

import java.io.Serializable;

public class Message implements Serializable
{
	@Override
	public String toString() {
		return this.User.userName +":" + content;
	}
	User User;
	String content;
	String timestamp;
	
	/**
	 * @return the user
	 */
	public User getUser()
	{
		return User;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user)
	{
		User = user;
	}
	/**
	 * @return the content
	 */
	public String getContent()
	{
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content)
	{
		this.content = content;
	}
	/**
	 * @return the timestamp
	 */
	public String getTimestamp()
	{
		return timestamp;
	}
	/**
	 * @param string the timestamp to set
	 */
	public void setTimestamp(String string)
	{
		this.timestamp = string;
	}
	
}
