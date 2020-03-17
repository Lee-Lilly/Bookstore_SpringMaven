package com.example.Bookstore;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
    private BookRepository bookRepository;
	
	@Test
    public void createNewBook() {
    	Book book = new Book("Ruokarouvan tytär", "Erja Manto","9789511316657", new Category("Fiction") , 2017);
    	bookRepository.save(book);
    	assertThat(book.getId()).isNotNull();
    } 
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = bookRepository.findByTitle("Lonely Planet India");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("Lonely Planet India");
	}
	
	@Test
	public void findByCategoryShouldReturnList() {
		List<Book> books = bookRepository.findByCategoryName("Education");
		assertThat(books).hasSize(2);
		assertThat(books.get(0).getTitle()).isEqualTo("French for Dummies");
		assertThat(books.get(1).getTitle()).isEqualTo("Phenomenal Learning from Finland");
	}
	
	@Test
	public void findByYearShouldReturnList() {
		List<Book> books = bookRepository.findByYear(2019);
		assertThat(books).hasSize(2);
	}


}
