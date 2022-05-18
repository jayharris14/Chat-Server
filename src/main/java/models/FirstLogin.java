package models;

import java.io.Serializable;

public class FirstLogin extends Points implements Serializable {

	public FirstLogin() {
		super();
		Description="First Log In";
	}

	@Override
	public int total() {
		// TODO Auto-generated method stub
		return 100;
	}

} 


