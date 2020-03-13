package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.Bookstore.domain.*;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@SuppressWarnings("unused")
	@Bean
	public CommandLineRunner bookStore(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository, LoanRepository loanRepository) {
		return (args) -> {
			log.info("give a couple of categories");
			Category category_Travel= categoryRepository.save(new Category("Travel"));
			Category category_Education = categoryRepository.save(new Category("Education"));
			Category category_Biography= categoryRepository.save(new Category("Biography"));
			Category category_Fiction= categoryRepository.save(new Category("Fiction"));
			Category category_Medichine = categoryRepository.save(new Category("Medicine"));
			Category category_Art= categoryRepository.save(new Category("Art"));
			Category category_History= categoryRepository.save(new Category("History"));
			Category category_Science= categoryRepository.save(new Category("Natural Science"));
			Category category_Technology= categoryRepository.save(new Category("Technology"));
			Category category_Economy= categoryRepository.save(new Category("Enconomy"));
			Category category_Philosophy= categoryRepository.save(new Category("Philosophy"));
			Category category_Psychology= categoryRepository.save(new Category("Psychology"));
			
			log.info("save a couple of books");
			Book book1 = bookRepository.save(new Book("Lonely Planet India", "Lonely Planet","9781787013698", category_Travel , 2016));
			Book book2 = bookRepository.save(new Book("French for Dummies", "Laura K. Lawless", "9781118004647", category_Education, 2017));
			Book book3 = bookRepository.save(new Book("Alvar Aalto tunnissa", "Roope Lipasti","9789525827729", category_Biography, 2019));
			Book book4 = bookRepository.save(new Book("Mikael Agricola tunnissa", "Roope Lipasti","9789526640099", category_Biography, 2019));
			bookRepository.save(new Book("Lonely Planet Portugal", "Lonely Planet","9781787010185", category_Travel, 2017));
			bookRepository.save(new Book("Travel through history - The Balkan", "Julia Worker","9781785385148", category_Travel, 2016));
			bookRepository.save(new Book("Phenomenal Learning from Finland ", "Kirsti Lonka","9789513774301", category_Education, 2016));
			
			log.info("fetch all categories");
			for (Category category : categoryRepository.findAll()) {
				log.info(category.toString());
			}
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
			
			log.info("create users");
			User admin = new User("admin", "admin1@git.com",
					"$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			User user1 = new User("sam", "sam@github.com",
			"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("user", "user@github.com",
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(admin);
						
			log.info("create loans");
			
			Loan loan1 = new Loan(admin, book1, LocalDateTime.now());
			loanRepository.save(loan1);
			Loan loan2 = new Loan(user2, book3, LocalDateTime.now());
			loanRepository.save(loan2);
			Loan loan3 = new Loan(user1, book2, LocalDateTime.now());
			loanRepository.save(loan3);
			
			
			log.info("fetch all loans");
			for (Loan loan : loanRepository.findAll()) {
				log.info(loan.toString());
			}
			

		};
	}
}
