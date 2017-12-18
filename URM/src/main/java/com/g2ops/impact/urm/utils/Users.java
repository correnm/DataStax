package com.g2ops.impact.urm.utils;

import java.io.Serializable;
import java.util.*;
import com.g2ops.impact.urm.types.User;

public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<User> users;
	
	public Users (User... users) {
		this.users = Arrays.asList(users);
	}

	public List<User> getUsers() {
		return(this.users);
	}

}
