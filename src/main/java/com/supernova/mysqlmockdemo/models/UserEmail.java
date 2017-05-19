package com.supernova.mysqlmockdemo.models;

import javax.persistence.*;

@Entity
@Table(name="t_emails")
public class UserEmail {
	
	private int id;
	private String email;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
