package models;

import java.io.Serializable;

public abstract class Points implements Serializable{
	String Description="unknown total";

	public String getDescription() {
		return Description;
	}

	public abstract int total();
}
