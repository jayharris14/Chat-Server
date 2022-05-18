package models;

import java.io.Serializable;

public abstract class PointDecorator extends Points implements Serializable{
	public abstract String getDescription();
}
