package models;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;

public interface ConcordServerInterface extends Remote{

		public String verify(String u, String pw) throws RemoteException;
		public String invite(User admin, User User) throws RemoteException;
		public String addChannel(User admin, Channel channel) throws RemoteException;
		public String deleteChannel(User admin, Channel channel) throws RemoteException;
		public String addPin(Channel channel) throws RemoteException;
		public String unPin(Channel channel) throws RemoteException;
		public void addMember(User user, Server server) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
		public String removeMember(User admin, User user) throws RemoteException;
		public String changeRole(Role newrole, User user) throws RemoteException;
		public String setRoleBuilder(User user, Role role, Server server) throws RemoteException;
		public String sendMessage(Message message, User user, User user2) throws RemoteException;
		public void CreateUser(String realname, String username, String password) throws RemoteException, AlreadyBoundException;
		public ConcordServer ReadFromDisk() throws RemoteException;
		public Server CreateServer(String name, User user) throws RemoteException, AlreadyBoundException;
		public ArrayList<Server> getuserservers(User user) throws RemoteException;
		public int addnumbers(int a, int b) throws RemoteException;
		public UserManager getUsermanager() throws RemoteException;
		public UserManager convertusermanager() throws RemoteException;
		public User getuserbyname(String username) throws RemoteException;
		public ArrayList<Channel> getuserchannels(User user)  throws RemoteException;
		public ArrayList<Channel> getserverchannels(Server server)  throws RemoteException;
		public Channel addChannel(User user, String name, Server server) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
		public ArrayList<Message> getchannelmessages(Channel channel) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
		public void CreateMessage(User user, Server server, Channel channel, String message) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
		public Integer SendInvite(User user, String name, Server server) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
		public User finduser(String name) throws RemoteException;
		public void notifyObservers() throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
		public ArrayList<Server> getinvitedservers(User user) throws RemoteException;
		public void removeinvitedserver(User user, Server server) throws RemoteException;
		public Server getserverbyname(String name) throws RemoteException;
		public Channel getchannelbyname(String name) throws RemoteException;
		public ArrayList<User> getserverusers(String name) throws RemoteException;
		public void notifyObserver4() throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
		public ArrayList<DirectConversation> getdirectconversations(User user)throws RemoteException;
		public void createdirectconversation(String name, User user) throws RemoteException;
		public ArrayList<Message> getdirectconversationmessages(String name) throws RemoteException;
		public void kick(Server server, String name) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
		public ArrayList<DirectConversation> getalldirectconversations() throws RemoteException;
		public void createdirectconversation(String name, String name2) throws RemoteException;
		public void createdcmessage(String message, User user, String name) throws RemoteException;
		public ArrayList<User> getdcusers(User user, User user2) throws RemoteException;
		public void setprofile(String profileLabel, String userName) throws RemoteException;
		public void setusername(String usernameLabel, String userName) throws RemoteException;
		public void setpassword(String passwordLabel, String userName) throws RemoteException;
		public void setname(String nameLabel, String userName) throws RemoteException;
		public String getprofile(User user) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
		public String getusername(User user) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
		public String getpassword(User user) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
		public String getname(User user) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException;
		
}
	

