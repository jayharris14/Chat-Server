package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Channel implements Serializable
{
	String name;
	ArrayList<Message> messages= new ArrayList<Message>();

	public void changeName(String name2) {
		this.name=name2;
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

	/**
	 * @return the messages
	 */
	public ArrayList<Message> getMessages()
	{
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(ArrayList<Message> messages)
	{
		this.messages = messages;
	}
}
