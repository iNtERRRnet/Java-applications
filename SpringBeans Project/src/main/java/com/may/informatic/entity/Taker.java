package com.may.informatic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "takers")
public class Taker 
{
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Takers_ID")
	private int id;
	private String name;
	private String surname;
	private String phoneNumber;
	private String email;
	private String hasTakenBook;
	
	public Taker()
	{
		super();
	}
	
	public Taker(String name, String surname, String phoneNumber, String email, int id, String hasTakenBook) 
	{
		super();
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.id = id;
		this.hasTakenBook = hasTakenBook;
	}
	
	public String getName() 
	{
		return this.name;
	}
	
	public void setName(String newName) 
	{
		this.name = newName;
	}
	
	public String getSurname() 
	{
		return this.surname;
	}
	
	public void setSurname(String newSurname)
	{
		this.surname = newSurname;
	}
	
	public String getPhoneNumber() 
	{
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String newPhoneNumber)
	{
		this.phoneNumber = newPhoneNumber;
	}
	
	public String getEmail() 
	{
		return this.email;
	}
	
	public void setEmail(String newEmail) 
	{
		this.email = newEmail;
	}
	
	public int getId() 
	{
		return this.id;
	}
	
	public void setId(int newId) 
	{
		this.id = newId;
	}
	
	public String getHasTakenBook() 
	{
		return this.hasTakenBook;
	}
	
	public void setHasTakenBook(String newHasTakenBook) 
	{
		this.hasTakenBook = newHasTakenBook;
	}
}