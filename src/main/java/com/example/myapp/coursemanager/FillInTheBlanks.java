package com.example.myapp.coursemanager;

import javax.persistence.Entity;

@Entity
public class FillInTheBlanks extends Question{
	
	private String blanks;

	public String getBlanks() {
		return blanks;
	}

	public void setBlanks(String blanks) {
		this.blanks = blanks;
	}
	

}
