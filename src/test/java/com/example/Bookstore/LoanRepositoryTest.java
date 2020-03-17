package com.example.Bookstore;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.Loan;
import com.example.Bookstore.domain.LoanRepository;
import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LoanRepositoryTest {
	@Autowired
    private LoanRepository loanRepository;
	
	@Autowired
    private BookRepository bookRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Test
    public void createNewLoan() {
    	Book book = new Book("Ruokarouvan tytär", "Erja Manto","9789511316657", new Category("Fiction") , 2017);
    	bookRepository.save(book);
    	assertThat(book.getId()).isNotNull();
    	User user = new User("kiki", "kiki@github.com", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
    	userRepository.save(user);
    	assertThat(user.getId()).isNotNull();
    	Loan loan = new Loan(user, book, LocalDateTime.now());
    	loanRepository.save(loan);
    	assertThat(loan.getId()).isNotNull();
    } 
	
	@Test
	public void findByUserNameShouldReturnList() {
		List<Loan> loans = loanRepository.findByUserName("admin");
		assertThat(loans).hasSize(2);
		assertThat(loans.get(0).getBook().getTitle()).isEqualTo("Lonely Planet India");
		assertThat(loans.get(1).getBook().getTitle()).isEqualTo("Mikael Agricola tunnissa");
	}
	
	@Test
	public void findByUserRoleShouldReturnList() {
		List<Loan> loans = loanRepository.findByUserRole("USER");
		assertThat(loans).hasSize(2);
		assertThat(loans.get(0).getBook().getTitle()).isEqualTo("French for Dummies");
		assertThat(loans.get(1).getBook().getTitle()).isEqualTo("Alvar Aalto tunnissa");
	}
		
	@Test
	public void findByCategoryNameShouldReturnList() {
		List<Loan> loans = loanRepository.findByBookCategoryName("Biography");
		assertThat(loans).hasSize(2);
		assertThat(loans.get(0).getBook().getTitle()).isEqualTo("Alvar Aalto tunnissa");
		assertThat(loans.get(1).getBook().getTitle()).isEqualTo("Mikael Agricola tunnissa");
	}
	
	@Test
	public void findByBookTitleShouldReturnList() {
		List<Loan> loans = loanRepository.findByBookTitle("French for Dummies");
		assertThat(loans).hasSize(1);
		assertThat(loans.get(0).getUser().getName()).isEqualTo("sam");
	}
	
}
