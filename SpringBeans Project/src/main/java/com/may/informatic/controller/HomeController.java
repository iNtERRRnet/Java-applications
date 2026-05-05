package com.may.informatic.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.may.informatic.entity.Book;
import com.may.informatic.entity.Taker;
import com.may.informatic.repository.BookRepository;
import com.may.informatic.repository.TakerRepository;
import com.may.informatic.service.HomeService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController 
{
	@Autowired
	HomeService service;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private TakerRepository takerRepository;
	
	@GetMapping("/takers_home")
	public String getTaker(Model m)
	{
		m.addAttribute("selectedtaker", new Taker());
		m.addAttribute("listoftakers", service.getAllTakers());
		
		return "takers_home";
	}
	
	@GetMapping("/books_home")
	public String getBooks(Model m) 
	{
		m.addAttribute("selectedbook", new Book());
		m.addAttribute("listofbooks", service.getAllBooks());
		
		return "books_home";
	}
	
	@GetMapping("/deleteTaker/{id}")
	public String deleteTaker(@PathVariable int id, Model m) 
	{
		service.deleteTaker(id);
		m.addAttribute("listoftakers", service.getAllTakers());
		
		return "redirect:/takers_home";
	}

	@GetMapping("/updateTaker/{id}")
	public String updateTaker(@PathVariable int id, Model m) 
	{
		m.addAttribute("selectedtaker", service.selectTaker(id));
		m.addAttribute("listoftakers", service.getAllTakers());
		
		return "takers_home";
	}
	
	@PostMapping("/addTaker")
	public String saveTaker(@RequestParam("idRaw") String idRaw, @RequestParam("hasTakenBook") String hasTakenBook, @ModelAttribute Taker taker, Model m) 
	{
		try
	    {
	    	String numericId = idRaw.replaceAll("\\D", "");
	        taker.setId(Integer.parseInt(numericId));
	        
	        if(hasTakenBook.toLowerCase().equals("yes"))
	        {
	        	taker.setHasTakenBook("1");
	        }
	        else if(hasTakenBook.toLowerCase().equals("no"))
	        {
	        	taker.setHasTakenBook("0");
	        }
	    } 
	    catch (NumberFormatException e) 
	    {
	        e.printStackTrace();
	    }
		
		service.addTaker(taker);
		
		return "redirect:/takers_home";
	}
	
	@PostMapping("/updateTaker")
	public String updateTaker(@RequestParam("idRaw") String idRaw, @RequestParam("hasTakenBook") String hasTakenBook, @ModelAttribute Taker taker, Model m) 
	{
		try
	    {
	    	String numericId = idRaw.replaceAll("\\D", "");
	        taker.setId(Integer.parseInt(numericId));
	        
	        if(hasTakenBook.toLowerCase().equals("yes"))
	        {
	        	taker.setHasTakenBook("1");
	        }
	        else if(hasTakenBook.toLowerCase().equals("no"))
	        {
	        	taker.setHasTakenBook("0");
	        }
	    } 
	    catch (NumberFormatException e) 
	    {
	        e.printStackTrace();
	    }
		
		m.addAttribute("selectedtaker", new Taker());
		service.updateTaker(taker);
		
		return "redirect:/takers_home";
	}

	@GetMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable int id, Model m) 
	{
		service.deleteBook(id);
		m.addAttribute("listofbooks", service.getAllBooks());
		
		return "redirect:/books_home";
	}

	@GetMapping("/updateBook/{id}")
	public String updateBook(@PathVariable int id, Model m) 
	{
		m.addAttribute("selectedbook", service.selectBook(id));
		m.addAttribute("listofbooks", service.getAllBooks());
		
		return "books_home";
	}
	
	@PostMapping("/addBook")
	public String updateBook(@RequestParam("idRaw") String idRaw, @RequestParam("takersIdRaw") String takersIdRaw, @ModelAttribute Book book) 
	{
	    try
	    {
	    	String numericId = idRaw.replaceAll("\\D", "");
	        book.setId(Integer.parseInt(numericId));
	        
	    	String numericTakersId = takersIdRaw.replaceAll("\\D", "");
	        book.setTakersId(Integer.parseInt(numericTakersId));
	    } 
	    catch (NumberFormatException e) 
	    {
	        e.printStackTrace();
	    }
		
		service.addBook(book);
		
		return "redirect:/books_home";
	}
	
	@PostMapping("/updateBook")
	public String saveBook(@RequestParam("idRaw") String idRaw, @RequestParam("takersIdRaw") String takersIdRaw, @ModelAttribute Book book, Model m) 
	{
	    try
	    {
	    	String numericId = idRaw.replaceAll("\\D", "");
	        book.setId(Integer.parseInt(numericId));
	        
	    	String numericTakersId = takersIdRaw.replaceAll("\\D", "");
	        book.setTakersId(Integer.parseInt(numericTakersId));
	    } 
	    catch (NumberFormatException e) 
	    {
	        e.printStackTrace();
	    }
		
		m.addAttribute("selectedbook", new Book());
		service.updateBook(book);
		
		return "redirect:/books_home";
	}
	
	@GetMapping("/login")
	public String getMethodName()
	{
		return "login";
	}
	
	@PostMapping("/manageLogin")
	public String manageLogin(@RequestParam String username, @RequestParam String password, Model m)
	{
		boolean check = service.LoginSystem(username, password);
		if(!check)
		{
			m.addAttribute("error", "Invalid Credentials!");
			return "login";
		}
		
		m.addAttribute("selectedtaker", new Taker());
		m.addAttribute("listoftakers", service.getAllTakers());
		
		m.addAttribute("selectedbook", new Book());
		m.addAttribute("listofbooks", service.getAllBooks());
		
		return "books_home";
	}
	
	@GetMapping("/searchBooks")
	@ResponseBody
	public List<Book> searchBooks(@RequestParam("field") String field, @RequestParam("query") String query) 
	{
	    return switch(field.toLowerCase()) 
	    {
	        case "name" -> bookRepository.findByNameContainingIgnoreCase(query);
	        case "author" -> bookRepository.findByAuthorContainingIgnoreCase(query);
	        case "genre" -> bookRepository.findByGenreContainingIgnoreCase(query);
	        case "id" -> 
	        {
	            try 
	            {
	                yield List.of(bookRepository.findById(Integer.parseInt(query))
	                		.orElse(null));
	            } 
	            catch(NumberFormatException e) 
	            {
	                yield List.of();
	            }
	        }
	        case "takersid" -> 
	        {
	        	try
	        	{
	        		yield bookRepository.findByTakersId(Integer.parseInt(query));
	        	}
	        	catch(NumberFormatException e)
	        	{
	        		yield List.of();
	        	}
	        }
	        default -> List.of();
	    };
	}
	
	@GetMapping("/searchTakers")
	@ResponseBody
	public List<Taker> searchTakers(@RequestParam("field") String field, @RequestParam("query") String query) 
	{
	    return switch(field.toLowerCase())
		{
	    	case "name" -> takerRepository.findByNameContainingIgnoreCase(query);
	    	case "surname" -> takerRepository.findBySurnameContainingIgnoreCase(query);
	    	case "phonenumber" -> takerRepository.findByPhoneNumberContainingIgnoreCase(query);
	    	case "email" -> takerRepository.findByEmailContainingIgnoreCase(query);
	        case "id" -> 
	        {
	            try 
	            {
	                yield takerRepository.findById(Integer.parseInt(query))
		                .map(List::of)
		                .orElse(List.of());
	            } 
	            catch(NumberFormatException e) 
	            {
	                yield List.of();
	            }
	        }
	        case "hastakenbook" -> 
	        {
	            String normalized = query.trim().toLowerCase();
	            String value = switch (normalized) 
	            {
	                case "yes", "ye", "y" -> "1";
	                case "no", "n" -> "0";
	                default -> query;
	            };
	            yield takerRepository.findByHasTakenBookContainingIgnoreCase(value);
	        }
	    	default -> List.of();
		};
	}
	
	@GetMapping("/allBooks")
	@ResponseBody
	public List<Book> getAllBooks()
	{
		return service.getAllBooks();
	}
	
	@GetMapping("/allTakers")
	@ResponseBody
	public List<Taker> getAllTakers()
	{
		return service.getAllTakers();
	}
}