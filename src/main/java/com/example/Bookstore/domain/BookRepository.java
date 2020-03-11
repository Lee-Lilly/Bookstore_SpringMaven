package com.example.Bookstore.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, Long> { 
	
	List<Book> findByOrderByTitle();
	List<Book> findByOrderByYear();
	List<Book> findByCategoryName(@Param("category") String category_name);
	List<Book> findByTitle(@Param("title") String title);
	List<Book> findByYear(@Param("year") Integer year);
	
}