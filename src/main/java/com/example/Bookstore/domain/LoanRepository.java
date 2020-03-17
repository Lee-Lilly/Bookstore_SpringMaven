package com.example.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, Long> {
	List<Loan> findByUserName(String name);
	List<Loan> findByBookTitle(String book_title);
	List<Loan> findByBookId(Long book_id);
	List<Loan> findByBookCategoryName(String category_name);
	List<Loan> findByUserRole(String role);
}
