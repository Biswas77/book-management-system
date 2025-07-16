package com.CRUDOP.ProductManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CRUDOP.ProductManagementSystem.entity.Book;

public interface ProductRepository extends JpaRepository<Book, Long> {

	List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrGenreContainingIgnoreCase(String keyword,
			String keyword2, String keyword3);
	

}
