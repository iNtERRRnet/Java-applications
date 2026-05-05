package application;

public class Book 
{
	private String name;
	private String author;
	private String genre;
	private String ID;
	private String loanDate;
	private String dueDate;
	private String takersID;
	
	public Book(String name, String author, String genre, String ID, String loanDate, String dueDate, String takersID)
	{
		this.name = name;
		this.author = author;
		this.genre = genre;
		this.ID = ID;
		this.loanDate = loanDate;
		this.dueDate = dueDate;
		this.takersID = takersID;
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

	public String getID() 
	{
		return this.ID;
	}

	public void setID(String newID) 
	{
		this.ID = newID;
	}

	public String getLoanDate() 
	{
		return this.loanDate;
	}

	public void setLoanDate(String newLoanDate) 
	{
		this.loanDate = newLoanDate;
	}

	public String getDueDate() 
	{
		return this.dueDate;
	}

	public void setDueDate(String newDueDate) 
	{
		this.dueDate = newDueDate;
	}
	
	public String getTakersID() 
	{
		return this.takersID;
	}

	public void setTakersID(String newTakersID) 
	{
		this.takersID = newTakersID;
	}
}