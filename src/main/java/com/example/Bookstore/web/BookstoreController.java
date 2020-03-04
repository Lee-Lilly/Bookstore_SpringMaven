package com.example.Bookstore.web;

import com.example.Bookstore.domain.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class BookstoreController {
	@Autowired
	private BookRepository bookRepository; 
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	//login
    @RequestMapping(value= {"/login"})
    public String login() {	
        return "login";
    }     
	
	// Show all books
	@RequestMapping(value= {"/bookstore","/"})
    public String bookList(Model model) {	
        model.addAttribute("books", bookRepository.findAll());
        return "bookstore";
    }
	
	//RESTful service to get all books
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book>bookListRest(){
		return (List<Book>) bookRepository.findAll();		
	}
	
	// RESTful service to get book by id
    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId){
    	return bookRepository.findById(bookId);
    }
   		
	// Add new book	with existing category drop down list ordered by category name
	@RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", categoryRepository.findByOrderByNameAsc());
        return "addbook";
    }     
	
	//Save new book, if bookId is "new"; else book records merge and Update. 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book) {
    	if (book.getId() == null){
    		bookRepository.save(book);  	
    	}
    	else {
    		book.setId(book.getId());
    		bookRepository.save(book);
    	}	
    	return "redirect:bookstore";
    } 
  
    //Edit a book with category drop down list ordered by name
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model){
    	model.addAttribute("book", bookRepository.findById(bookId));
    	model.addAttribute("categories", categoryRepository.findByOrderByNameAsc());
        return "editbook";
    }
    
    //Delete books and show user list only for ADMIN
    
    //Delete a book record
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')") //"delete" method must have authentication of "ADMIN"
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	bookRepository.deleteById(bookId);
        return "redirect:../bookstore";
    }
       
    // Show all users
 	@RequestMapping(value= {"/userlist"})
 	@PreAuthorize("hasAuthority('ADMIN')")
     public String userList(Model model) {	
         model.addAttribute("users", userRepository.findAll());
         return "userlist";
     }
   
    
    
}