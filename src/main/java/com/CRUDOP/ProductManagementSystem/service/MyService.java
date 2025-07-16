package com.CRUDOP.ProductManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.CRUDOP.ProductManagementSystem.entity.Book;
import com.CRUDOP.ProductManagementSystem.repository.ProductRepository;

@Service
public class MyService {

    @Autowired
    private ProductRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void delete(Long id, ModelMap model) {
        bookRepository.deleteById(id);
        model.put("pass", "Book Deleted Successfully");
    }
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrGenreContainingIgnoreCase(keyword, keyword, keyword);
    }


    public Book updateBook(Long id, Book updatedBook) {
        if (bookRepository.existsById(id)) {
            updatedBook.setId(id);
            return bookRepository.save(updatedBook);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }
}
