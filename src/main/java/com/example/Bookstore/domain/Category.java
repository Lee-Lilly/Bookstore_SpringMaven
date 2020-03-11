package com.example.Bookstore.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "categories") //database name corresponding to this Entity 
public class Category {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	@OneToMany(mappedBy="category", cascade = CascadeType.ALL)
	private List<Book> books;  
	
	public Category() {}
	
	public Category(String name) {
		super();
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long category_id) {
		this.id = category_id;
	}
	
	public String getName() {
		return name;
	}	
	
	public void setName(String name) {
		this.name = name;
	}	
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", Name=" + name + "]";
	}

}
