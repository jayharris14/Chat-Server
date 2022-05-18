package models;

import java.io.Serializable;

public class CreateMessage extends PointDecorator implements Serializable {
	Points points;

	public CreateMessage(Points points) {
		super();
		this.points = points;
	}
	
	public String getDescription() {
		return points.getDescription()+ ", CreateMessage";
	}
	

	@Override
	public int total() {
		return points.total()+ 5;
	}
}