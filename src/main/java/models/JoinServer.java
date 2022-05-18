package models;

import java.io.Serializable;

public class JoinServer extends PointDecorator implements Serializable{
	Points points;

	public JoinServer(Points points) {
		super();
		this.points = points;
	}
	
	public String getDescription() {
		return points.getDescription()+ ", JoinServer";
	}
	

	@Override
	public int total() {
		return points.total()+ 10;
	}
}

