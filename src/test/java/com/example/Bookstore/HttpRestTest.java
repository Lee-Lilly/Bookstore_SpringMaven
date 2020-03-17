package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRestTest {
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
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
