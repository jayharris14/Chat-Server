package models;

import java.io.Serializable;

public class CreateServer extends PointDecorator implements Serializable {
	Points points;

	public CreateServer(Points points) {
		super();
		this.points = points;
	}
	
	public String getDescription() {
		return points.getDescription()+ ", CreateServer";
	}
	

	@Override
	public int total() {
		return points.total()+ 5;
	}

}
