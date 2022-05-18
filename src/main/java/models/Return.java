package models;

import java.io.Serializable;

public class Return extends Points implements Serializable {

	public Return() {
		super();
		Description="Returning Log In";
	}

	@Override
	public int total() {
		// TODO Auto-generated method stub
		return 1;
	}

}
