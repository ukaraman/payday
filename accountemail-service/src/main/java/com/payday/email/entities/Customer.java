package com.payday.email.entities;

import javax.persistence.Entity;

//1. User should receive an email confirmation of
//account after account is opened
//2. Confirmation email should include the User’s Full
//Name, Gender, Date of Birth, Phone Number,
//Account Number and Account Type (Debit or Deposit)

public class Customer {
	private int accountNumber;
	private String customerName;
	private int accounType;
	private String phoneNumber;
	private String gender;
	private String email;
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getAccounType() {
		return accounType;
	}
	public void setAccounType(int accounType) {
		this.accounType = accounType;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
}
