package com.example.Bookstore.domain;

import javax.validation.constraints.Size;

public class SignupForm {
	 @Size(min=5, max=30)
	 private String userName = "";
	 
	 @Size(min=7, max=30)
	 private String password = "";
	 
	 @Size(min=7, max=30)
	 private String email = "";
	 
	 @Size(min=7, max=30)
	 private String passwordCheck = "";
	 
	 private String role = "USER";
	 
	 public String getUserName() {
			return userName;
	 }
	
	 public void setUserName(String userName) {
		this.userName = userName;
	 }
	 
	 public String getEmail() {
			return email;
	 }
	
	 public void setEmail(String email) {
		this.email = email;
	}
	 
	 public String getPassword() {
		return password;
	 }
	
	 public void setPassword(String password) {
		this.password = password;
	 }
	
	 public String getPasswordCheck() {
		return passwordCheck;
	 }
	
	 public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	 }
	
	 public String getRole() {
		return role;
	 }
	
	 public void setRole(String role) {
		this.role = role;
	}
}
