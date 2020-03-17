package com.example.Bookstore;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;
import com.example.Bookstore.web.BookstoreController;
import com.example.Bookstore.web.UserController;
import static org.assertj.core.api.Assertions.*;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BookstoreApplicationTests {
	@LocalServerPort
	private int port;
	
	@Autowired
	private BookstoreController bController;
	
	@Autowired
	private UserController uController;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
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
	
	//HttpRequest Test for Log in form
	@Test
	public void loginShouldReturnDefaultMessage() throws Exception { 
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("Welcome to New Book Store!");
	}
	
	//HttpRequest Test for Sign-up form
	@Test
	public void signUpShouldReturnDefaultMessage() throws Exception { 
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/signup",
				String.class)).contains("Sign-up");
	}

}
