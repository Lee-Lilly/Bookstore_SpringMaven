package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Bookstore.web.BookstoreController;
import com.example.Bookstore.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookstoreApplicationTests {	
	@Autowired
	private BookstoreController bController;
	
	@Autowired
	private UserController uController;
	
	
	//Smoke test
	@Test
	public void contextLoads_book() throws Exception {
		assertThat(bController).isNotNull();
	}
	
	//Smoke test
	@Test
	public void contextLoads_user() throws Exception {
		assertThat(uController).isNotNull();
	}
		
}
