package models;

import java.io.Serializable;

public class CreateChannel extends PointDecorator implements Serializable {
	Points points;

	public CreateChannel(Points points) {
		super();
		this.points = points;
	}
	
	public String getDescription() {
		return points.getDescription()+ ", CreateChannel";
	}
	

	@Override
	public int total() {
		return points.total()+ 3;
	}
	
}
