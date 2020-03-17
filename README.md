# Project Name: Bookstore
# Spring Boot framework with Maven build, 
# Dependencies: Spring Boot Devtools, Spring Web and Thymleaf, H2 Database, Srping Security
# JPA's object-relational mapping (ORM) model. 
# JPA Entity: Book (id, title, author, isbn, category, year)
# JPA Entiry: Category (id, name)
# JPA Entity: User (id, name, email, role)
# JPA Entiry: Loan (User, Book)
# Spring Data CrudRepository interfaces
# CrudRepository: bookRepository
# CrudRepository: categoryRepository
# CrudRepository: userRepository
# CrudRepository: loanRepository
# Security ADMIN role: ADD, UPDATE, DELETE book records; CHECK users repository; CHECK loan repository; FORCE RETURN overdue loans;
# Security USER role: SEARCH books; BORROW books; RETURN books; SEARCH own collection;  
# Smoke Test, Http Test, JPA test
