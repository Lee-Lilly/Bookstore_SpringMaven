package com.example.Bookstore.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {

	@NotNull
	@Size(min=2, max=30)
	private String title;
	
	@NotNull
	@Size(min=2, max=30)
	private Contributor[] contributors;
	
	@NotNull
	@Size(min=2, max=30)
	private String isbn;
	
	@NotNull
	@Size(min=2, max=30)
	private Float price;
	
	public String getTitle() {
		return title;
	}	
	public String getIsbn() {
		return isbn;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public Contributor[] getContributors(){
		return contributors;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
	
	public void setContributors(Contributor[] contributors) {
		this.contributors = contributors;
	}


	
	
}
