package models;

import java.io.Serializable;

public class LogIn extends Points implements Serializable {

	public LogIn() {
		super();
		Description="Log In";
	}

	@Override
	public int total() {
		// TODO Auto-generated method stub
		return 50;
	}

}
