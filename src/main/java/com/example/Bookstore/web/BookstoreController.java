package com.example.Bookstore.web;

import com.example.Bookstore.domain.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.Date;
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
		
		@Autowired
		private LoanRepository loanRepository;
		
		//login
	    @RequestMapping(value= {"/login", "/"})
	    public String login() {	
	        return "login";
	    }     
		
		// Show all books
		@RequestMapping(value= {"/bookstore"})
	    public String bookList(Model model) {	
	        model.addAttribute("books", bookRepository.findAll());
	        return "bookstore";
	    }
		
		//The attribute th:name="SAME TEXT" binds the value of the input fields to the RequestParam. 
	 	//we need to annotate @RequestParam(value = "SAME TEXT").
	    @RequestMapping(value = "/search")
	    public String bookSearch(@RequestParam(value = "byWhat") String byWhat, @RequestParam(value = "input") String input, Model model) {	      	
	    	if (byWhat == "year") {//parseInt() to convert String to Integer
	    		model.addAttribute("books", bookRepository.findByYear(Integer.parseInt(input)));
	    	} else if (byWhat == "title"){	
	    		model.addAttribute("books", bookRepository.findByTitle(input));
	    	} else if(byWhat =="category") {
	    		model.addAttribute("books", bookRepository.findByCategoryName(input));
	    	}    	
	    	return "bookstore";
	    }	       
	    
	    //User can borrow a book
	    @RequestMapping(value = "/borrow/{id}")
	    public String borrowBook(@PathVariable("id") Long bookId, Principal principal, Model model, RedirectAttributes redirectAttributes){
	    	
	    	String username = principal.getName();	    	    
	    	System.out.println("It's me: " + username);
	    	
	    	//check in the user collection, if the bookId already exists
	    	List<Loan> userloans = loanRepository.findByUserName(username);
	    	for (Loan loan : userloans) { //for each loan in user collection, check the book ID
	    			Long ucId = loan.getBook().getId();
	    			if (ucId == bookId) {
	    				System.out.println("Invalid request: you already a book of same book ID.");
	    				//alert message sends to user session bookstore
	    				redirectAttributes.addFlashAttribute("message", "The book already exists in your collection.");
	    				return "redirect:../bookstore";
	    			}
	    	}
	    	//user can carry on
	    	Loan loan = new Loan();
	    	
	    	loan.setUser(userRepository.findByName(username));
	    	
	    	Optional<Book> optional = bookRepository.findById(bookId);
	    	if(optional.isPresent()){
	    		System.out.println("I would like to read book: " + optional.get().getTitle());	
	    		loan.setBook(optional.get());
	    	}
	    	loan.setDate(new Date());
	    	loanRepository.save(loan);
	    	return "redirect:../collection";
	    }
	    
	    //User can check their personal collection of borrowed books ("ME" as user, the borrow list)
 		@RequestMapping(value= {"/collection"})
 	    public String myCollection(Principal principal, Model model) {
 			String username = principal.getName();	  
 	        model.addAttribute("loans", loanRepository.findByUserName(username));
 	        return "collection";
 	    }
 		
 		//User can return a book
	    @RequestMapping(value = "/return/{id}", method = RequestMethod.GET)
	    public String returnBook(@PathVariable("id") Long loanId, Principal principal, Model model) {
	    	loanRepository.deleteById(loanId);
	        return "redirect:../collection";
	    }
    
	      
	    //Add, Edit(update) and Delete books are reserved actions only for ADMIN
	    
	    //Add new book with existing category drop down list ordered by category name
  		@RequestMapping(value = "/add", method = RequestMethod.GET)
  		@PreAuthorize("hasAuthority('ADMIN')") //"addBook" method belongs to authentication of "ADMIN"
  	    public String addBook(Model model){
  	    	model.addAttribute("book", new Book());
  	    	model.addAttribute("categories", categoryRepository.findByOrderByNameAsc());
  	        return "addbook";
  	    }  
	    
	    //Edit a book record with category drop down list ordered by name
	    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	    @PreAuthorize("hasAuthority('ADMIN')") //"editBook" method must have authentication of "ADMIN"
	    public String editBook(@PathVariable("id") Long bookId, Model model){
	    	model.addAttribute("book", bookRepository.findById(bookId));
	    	model.addAttribute("categories", categoryRepository.findByOrderByNameAsc());
	        return "editbook";
	    }
	    
	  //Save new book record or update an existing record  
	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String save(Book book) {
	    	if (book.getId() == null){ //a new book
	    		bookRepository.save(book);  	
	    	}
	    	else { //an existing book
	    		book.setId(book.getId());
	    		bookRepository.save(book);
	    	}	
	    	return "redirect:bookstore";
	    }
	    
	    //Delete a book record
	    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    @PreAuthorize("hasAuthority('ADMIN')") //"delete" method must have authentication of "ADMIN"
	    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	    	bookRepository.deleteById(bookId);
	        return "redirect:../bookstore";
	    }
	    
	    //ADMIN also can check users & loan list 
	 	@RequestMapping(value= {"/userlist"})
	 	@PreAuthorize("hasAuthority('ADMIN')")
	     public String userList(Model model) {	
	         model.addAttribute("users", userRepository.findAll());
	         return "userlist";
	     }
	 	
	 	@RequestMapping("/searchUser")
	 	@PreAuthorize("hasAuthority('ADMIN')")
	    public String users(@RequestParam(value = "username") String user_name, Model model) {
	      model.addAttribute("users", userRepository.findByName(user_name));
	      return "userlist";
	    }
	 	
	 	@RequestMapping(value= {"/loanlist"})
	 	@PreAuthorize("hasAuthority('ADMIN')")
	 	public String loanList(Model model) {	
	 	     model.addAttribute("loans", loanRepository.findAll());
	 	     return "loanlist";
 	     }
 	 	
 	 	@RequestMapping("/searchLoan")
 	 	@PreAuthorize("hasAuthority('ADMIN')")
 	    public String loans(@RequestParam(value = "username") String user_name, Model model) {
 	      model.addAttribute("loans", loanRepository.findByUserName(user_name));
 	      return "loanlist";
 	    }

	 	
	 	
	//Following are Restful service and Rest API search
	//Returns a json file to http response body
	 		
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
    
    //REST API service to search book by Year
    //user must input an integer for {year}  e.g. http://localhost:8080/api/books/search/findByYear/?year=2019
    @RequestMapping(value="/api/books/search/findByYear/", method = RequestMethod.GET)
    public @ResponseBody List<Book> findBookYear(@RequestParam("year") Integer year){
    	return bookRepository.findByYear(year);
    }
    
    //REST API service to search book by Title
    //user must input a string for {title}  e.g. http://localhost:8080/api/books/search/findByTitle/?title=Lonely Planet India
    @RequestMapping(value="/api/books/search/findByTitle/", method = RequestMethod.GET)
    public @ResponseBody List<Book> findBookTitle(@RequestParam("title") String title){
    	return bookRepository.findByTitle(title);
    }
    
    //REST API service to search book by Category Name
    //user must input a string for {category_name}  e.g. http://localhost:8080/api/books/search/findByCategoryName/?category=Education
    @RequestMapping(value="/api/books/search/findByCategoryName/", method = RequestMethod.GET)
    public @ResponseBody List<Book> findBookCategory(@RequestParam("category") String category_name){
    	return bookRepository.findByCategoryName(category_name);
    }
    
    
   		
	
   
    
    
}