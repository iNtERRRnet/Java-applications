package application;

public class Taker 
{
	private String name;
	private String surname;
	private String phoneNumber;
	private String email;
	private String takersID;
	private String hasTakenBook;
	
	public Taker(String name, String surname, String phoneNumber, String email, String takersID, String hasTakenBook) 
	{
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.takersID = takersID;
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
	
	public String getTakersID() 
	{
		return this.takersID;
	}
	
	public void setTakersID(String newTakersID) 
	{
		this.takersID = newTakersID;
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