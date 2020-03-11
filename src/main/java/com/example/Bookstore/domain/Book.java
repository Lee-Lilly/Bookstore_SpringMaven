package com.example.Bookstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "books") //database name corresponding to this Entity
public class Book {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Long id;
	
	private String title;
	private String author;

	@Column(unique =true)
	private String isbn;  
	private Integer year;
	
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name="category_id")
	private Category category;
			
	public Book() {}
	
	public Book(String title, String author, String isbn, Category category, Integer year) {
		super();
		this.title = title;		
		this.author = author;
		this.isbn = isbn;
		this.category= category;
		this.year = year;				
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}	
	
	public String getAuthor(){
		return author;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public Integer getYear() {
		return year;
	}	
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		if (this.category != null) {
			return "Book [id=" + id + ", Title= " + title + ", Author= " + author + ", ISBN= " + isbn + ", Category= " + category.getName() + ", Year= " + year + "]";
		}
		else {
			return "Book [id=" + id + ", Title= " + title + ", Author= " + author + ", ISBN= " + isbn + ", Year= " + year + "]";
		}
	
	}	
	
}
