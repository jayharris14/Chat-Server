package models;

import java.util.ArrayList;

public class DirectConversation
{
	ArrayList<User> users= new ArrayList<User>();
	ArrayList<Message> messages= new ArrayList<Message>();
	DirectConversationManager directconversationmanager=new DirectConversationManager();
	String name;
	
		public DirectConversation(DirectConversationManager directconversationmanager){
			directconversationmanager.addDirectConversation(this);
		}

		public DirectConversation() {
			
		}
	public ArrayList<User> getUsers() {
			return users;
		}


		public void setUsers(ArrayList<User> users) {
			this.users = users;
		}


		public ArrayList<Message> getMessages() {
			return messages;
		}


		public void setMessages(ArrayList<Message> messages) {
			this.messages = messages;
		}


		public DirectConversationManager getDirectconversationmanager() {
			return directconversationmanager;
		}


		public void setDirectconversationmanager(DirectConversationManager directconversationmanager) {
			this.directconversationmanager = directconversationmanager;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


	public void sendMessage(Message message, User user, User user2)
	{
		messages.add(message);
		users.add(user);
		users.add(user2);
		
		
	}
	
	public String getLastTimestamp() {
		String c = messages.get(messages.size()-1).timestamp;
		return c;
		
		
	}
	
	
}