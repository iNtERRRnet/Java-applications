package com.may.informatic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "books")
public class Book 
{
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String author;
	private String genre;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate loanDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDate;
    @Column(name = "Takers_ID")
	private Integer takersId;
	
	public Book()
	{
		super();
	}
	
	public Book(String name, String author, String genre, int id, LocalDate loanDate, LocalDate dueDate, Integer takersId)
	{
		super();
		this.name = name;
		this.author = author;
		this.genre = genre;
		this.id = id;
		this.loanDate = loanDate;
		this.dueDate = dueDate;
		this.takersId = takersId;
	}

	public String getName() 
	{
		return this.name;
	}

	public void setName(String newName) 
	{
		this.name = newName;
	}

	public String getAuthor() 
	{
		return this.author;
	}

	public void setAuthor(String newAuthor) 
	{
		this.author = newAuthor;
	}
	
	public String getGenre()
	{
		return this.genre;
	}
	
	public void setGenre(String newGenre)
	{
		this.genre = newGenre;
	}

	public int getId() 
	{
		return this.id;
	}

	public void setId(int newId) 
	{
		this.id = newId;
	}

	public LocalDate getLoanDate() 
	{
		return this.loanDate;
	}

	public void setLoanDate(LocalDate newLoanDate) 
	{
		this.loanDate = newLoanDate;
	}

	public LocalDate getDueDate() 
	{
		return this.dueDate;
	}

	public void setDueDate(LocalDate newDueDate) 
	{
		this.dueDate = newDueDate;
	}
	
	public Integer getTakersId() 
	{
		return this.takersId;
	}

	public void setTakersId(Integer newTakersId) 
	{
		this.takersId = newTakersId;
	}
}