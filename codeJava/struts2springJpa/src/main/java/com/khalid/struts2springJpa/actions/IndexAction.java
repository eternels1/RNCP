package com.khalid.struts2springJpa.actions;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.khalid.struts2springJpa.metier.Message;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	
	private static Logger log = LogManager.getLogger(ActionSupport.class); 
	private static final long serialVersionUID = 1L;

	private Message message;

	public Message getMessage() {return message;}
	public void setMessage(Message message) {this.message = message;}

	public String index() {
		log.info("appel de index!");
		
		return SUCCESS;
	}

}
