package models;

import java.io.Serializable;

public class RoleBuilder implements Serializable
{
	public Role createUserRole(String name)
	{	
		Role role= new Role();
		if (name.equals("admin")) {
			role.name=name;
			role.addAdmin=true;
			role.addChannel=true;
			role.addModerator=true;
			role.inviteUser=true;
			role.removeChannel=true;
			role.removeMember=true;
			role.removeModerator=true;
			role.addMember=true;
		}
		if (name.equals("mod")) {
			role.name=name;
			role.addAdmin=false;
			role.addChannel=true;
			role.addModerator=false;
			role.inviteUser=false;
			role.removeChannel=true;
			role.removeMember=true;
			role.removeModerator=false;
			role.addMember=false;
		}
		if (name.equals("member")) {
			role.name=name;
			role.addAdmin=false;
			role.addChannel=false;
			role.addModerator=false;
			role.inviteUser=true;
			role.removeChannel=false;
			role.removeMember=false;
			role.removeModerator=false;
			role.addMember=true;
		}
		
		return role;
	}
	
	
	
	
}
