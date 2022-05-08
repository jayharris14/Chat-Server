package models;

import java.util.ArrayList;

public class DirectConversationManager
{
	ArrayList<DirectConversation> DCM= new ArrayList<DirectConversation>();
	
	public void addDirectConversation(DirectConversation directconversation) {
		DCM.add(directconversation);
	}
	
	public ArrayList<DirectConversation> getpastconversations(User user) {
		ArrayList<DirectConversation> UserDC=new ArrayList<DirectConversation>();
		for (int i=0; i < DCM.size(); i++) {
			if (DCM.get(i).users.contains(user)==true) {
				UserDC.add(DCM.get(i));
				
			}
		}
		return UserDC;
	}
}
