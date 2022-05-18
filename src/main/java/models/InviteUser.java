package models;

import java.io.Serializable;

public class InviteUser extends PointDecorator implements Serializable{
	Points points;

	public InviteUser(Points points) {
		super();
		this.points = points;
	}
	
	public String getDescription() {
		return points.getDescription()+ ", InviteUser";
	}
	

	@Override
	public int total() {
		return points.total()+ 5;
	}
	
}
