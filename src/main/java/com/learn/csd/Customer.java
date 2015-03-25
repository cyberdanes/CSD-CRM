package com.learn.csd;

import com.mongodb.DBObject;

public class Customer {
	private String registrationNo;
	private String fullName;
	private String email;
	private String phoneNumber;
	private String companyName;
	private String location;
	private String course;
	
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	public static Customer convertDBObjectToCustomer(DBObject dbo){
		if(dbo!=null){
			Customer customer = new Customer();
			customer.setRegistrationNo(dbo.get("Registration No").toString());
			customer.setFullName(dbo.get("Full Name").toString());
			customer.setEmail(dbo.get("Email").toString());
			customer.setPhoneNumber(dbo.get("Phone Number").toString());
			customer.setCompanyName(dbo.get("Company Name").toString());
			customer.setLocation(dbo.get("Location").toString());
			customer.setCourse(dbo.get("Course").toString());
			return customer;
		}
		return null;
	}
	
	public String toString(){
		return  "Registration No: "+registrationNo+
			   " Full Name: "+fullName+
			   " Email: "+email+
			   " Phone Number: "+phoneNumber+
			   " Company Name: "+companyName+
			   " Location: "+location+
			   " Course: "+course;
	}
}
