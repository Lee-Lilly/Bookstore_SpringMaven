package com.example.Bookstore.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "loans") //database name corresponding to this Entity
public class Loan {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Long id;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="book_id", nullable=false)
	private Book book;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	
	@Column(name="time")
	private Date date;  
	
	
	public Loan() {}
	
	public Loan(User user, Book book, Date date) {
		this.user = user;
		this.book = book;
		this.date = date;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public Date getDate() {
		return date;
	}	
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {		
		return "Loan [id=" + id + ", User_name= " + user.getName() + ", Borrowed_book= " + book.getTitle() + ", Time ="+ date + "]";
	}

}
