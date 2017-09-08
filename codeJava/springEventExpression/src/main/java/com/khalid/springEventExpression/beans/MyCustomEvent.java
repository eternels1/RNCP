package com.khalid.springEventExpression.beans;

import org.springframework.context.ApplicationEvent;

public class MyCustomEvent extends ApplicationEvent {

	private String message;
	
	public MyCustomEvent(Object source, String message) {
		super(source);
		this.message=message;
	}

	@Override
	public String toString() {
		return "MyCustomEvent [message=" + message + "]";
	}
	

}
