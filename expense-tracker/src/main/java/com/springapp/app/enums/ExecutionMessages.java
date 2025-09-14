package com.springapp.app.enums;

public enum ExecutionMessages {

	DEFAULT_VALUE("[]"),
	ADDED("Expense Added Successfully"),
	UPDATED("Expense Updated Successfully"),
	DELETED("Expense Deleted Successfully");

	private String value; 
	
	ExecutionMessages(String value) {
		this.value=value;
	}

	public String value() {
		return value;
	}
	
}
