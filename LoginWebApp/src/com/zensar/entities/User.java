package com.zensar.entities;

/**
 * @author Garvita Jain
 * @version 1.0
 * @creation_date 21/09/2019 5:24PM
 * @modification_date 21/09/2019 5:24PM
 * @copyright Zensar Technologies. All rights reserved.
 * @description Java Bean Classs used to represent database entity.
 * 				It is also used as value object.
 * 				It is used in all layers of application.
 *
 */
public class User {
	private String username;
	private String password;
	public User() {
		
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}
