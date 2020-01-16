package com.aca.rest.model;

public class EmailMessage {
	private String email;
	private String emailSubject;
	private String emailText;
	
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getEmailText() {
		return emailText;
	}
	public void setEmailText(String emailText) {
		this.emailText = emailText;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
