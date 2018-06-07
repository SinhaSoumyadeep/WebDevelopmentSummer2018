package com.example.myapp.coursemanager;

import javax.persistence.Entity;

@Entity
public class TrueAndFalse extends Question {
	
	private boolean isTrue;

	public boolean getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}

	
	

}
