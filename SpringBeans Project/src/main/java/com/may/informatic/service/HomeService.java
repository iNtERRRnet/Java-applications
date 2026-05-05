package com.may.informatic.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.may.informatic.entity.Book;
import com.may.informatic.entity.Taker;
import com.may.informatic.entity.User;
import com.may.informatic.repository.BookRepository;
import com.may.informatic.repository.TakerRepository;
import com.may.informatic.repository.UserRepository;

@Service
public class HomeService 
{
	@Autowired
	BookRepository bkrep;
	
	@Autowired
	TakerRepository tkrep;
	
	@Autowired
	UserRepository userep;
	
	public List<Taker> getAllTakers()
	{
		return tkrep.findAll();
	}
	
	public Taker addTaker(Taker taker) 
	{
		Taker newTaker = new Taker();
		
		newTaker.setName(taker.getName());
		newTaker.setSurname(taker.getSurname());
		newTaker.setPhoneNumber(taker.getPhoneNumber());
		newTaker.setEmail(taker.getEmail());
		newTaker.setId(taker.getId());
		newTaker.setHasTakenBook(taker.getHasTakenBook());
		
		return tkrep.save(newTaker);
	}
	
	public Taker updateTaker(Taker taker)
	{
		 if(taker.getId() != 0) 
		 {
			 Taker existingTaker = tkrep.findById(taker.getId())
			                          .orElseThrow(() -> new RuntimeException("Taker not found."));
			 
			 existingTaker.setName(taker.getName());
			 existingTaker.setSurname(taker.getSurname());
			 existingTaker.setPhoneNumber(taker.getPhoneNumber());
			 existingTaker.setEmail(taker.getEmail());
			 existingTaker.setId(taker.getId());
			 existingTaker.setHasTakenBook(taker.getHasTakenBook());
			 
			 return tkrep.save(existingTaker);
		 } 
		 else 
		 {
		     return tkrep.save(taker);
		 }
	}
	
	public void deleteTaker(int id) 
	{
		tkrep.deleteById(id);
	}
	
	public Taker selectTaker(int id) 
	{
		return tkrep.findById(id).orElse(null);
	}
	
	public List<Book> getAllBooks()
	{
		return bkrep.findAll();
	}
	
	public Book addBook(Book book) 
	{
		Book newBook = new Book();
		
		newBook.setName(book.getName());
		newBook.setAuthor(book.getAuthor());
		newBook.setGenre(book.getGenre());
		newBook.setId(book.getId());
		if(book.getTakersId() == null || book.getTakersId().toString().isBlank())
		{
			newBook.setLoanDate(null);
			newBook.setDueDate(null);
			newBook.setTakersId(null);
		}
		else
		{
			newBook.setLoanDate(book.getLoanDate());
			newBook.setDueDate(book.getDueDate());
			newBook.setTakersId(book.getTakersId());
		}
		
		return bkrep.save(newBook);
	}
	
	public Book updateBook(Book book) 
	{
		 if(book.getId() != 0) 
		 {
			 Book existingBook = bkrep.findById(book.getId())
			                          .orElseThrow(() -> new RuntimeException("Book not found."));
			 
			 existingBook.setName(book.getName());
			 existingBook.setAuthor(book.getAuthor());
			 existingBook.setGenre(book.getGenre());
			 existingBook.setId(book.getId());
			 if(book.getTakersId() == null || book.getTakersId().toString().isBlank())
			 {
				 existingBook.setLoanDate(null);
				 existingBook.setDueDate(null);
				 existingBook.setTakersId(null);
			 }
			 else
			 {
				 existingBook.setLoanDate(book.getLoanDate());
				 existingBook.setDueDate(book.getDueDate());
				 existingBook.setTakersId(book.getTakersId());
			 }
			
			 return bkrep.save(existingBook);
		 } 
		 else 
		 {
		     return bkrep.save(book);
		 }
	}
	
	public void deleteBook(int id) 
	{
		bkrep.deleteById(id);
	}
	
	public Book selectBook(int id) 
	{
		return bkrep.findById(id).orElse(null);
	}
	
	public boolean LoginSystem(String username, String password)
	{
		User user = userep.findUserByUsername(username);
		return user != null && user.getPassword().equals(password);
	}
}