package com.CRUDOP.ProductManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.CRUDOP.ProductManagementSystem.entity.Book;
import com.CRUDOP.ProductManagementSystem.entity.User;
import com.CRUDOP.ProductManagementSystem.service.MyService;
import com.CRUDOP.ProductManagementSystem.service.UserService;

@Controller
public class MyController {

    @Autowired
    private MyService bookService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(ModelMap map) {
        map.put("pass", "Welcome to Book Management System");
        return "main";
    }

    @GetMapping("/add")
    public String showForm(ModelMap model) {
        model.addAttribute("book", new Book());
        model.addAttribute("userlist", userService.getAllUsers());
        return "add";
    }

    @PostMapping("/saveBook")
    public String save(@ModelAttribute Book book, ModelMap model) {
        bookService.saveBook(book);
        model.put("pass", "Book added successfully");
        return "main";
    }

    @GetMapping("/save")
    public String viewBooks(ModelMap model) {
        List<Book> allBooks = bookService.getAllBooks();
        model.put("books", allBooks);
        return "list";
    }

    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute Book book) {
        bookService.saveBook(book); // or call updateBook(book.getId(), book)
        return "redirect:/save";
    }

    @GetMapping("/books/edit/{id}")
    public String editBook(@PathVariable Long id, ModelMap model) {
        model.put("book", bookService.getBookById(id));
        return "edit";
    }

    @GetMapping("/books/delete/{id}")
    public String delete(@PathVariable Long id, ModelMap model) {
        bookService.delete(id, model);
        return viewBooks(model);
    }

    @GetMapping("/books/add")
    public String showAdd(ModelMap model) {
        model.put("book", new Book());
        model.put("userlist", userService.getAllUsers());
        return "add";
    }
    
    @GetMapping("/search")
    public String searchBooks(@RequestParam("keyword") String keyword, ModelMap model) {
        List<Book> result = bookService.searchBooks(keyword);
        model.put("books", result);
        return "search";
    }
    
    @GetMapping("/dashboard")
    public String showDashboard(ModelMap model) {
        List<Book> books = bookService.getAllBooks();
        List<User> users = userService.getAllUsers();

        int totalQuantity = books.stream().mapToInt(Book::getQuantity).sum();
        double totalValue = books.stream().mapToDouble(b -> b.getPrice() * b.getQuantity()).sum();

        model.put("bookCount", books.size());
        model.put("userCount", users.size());
        model.put("totalQuantity", totalQuantity);
        model.put("totalValue", totalValue);

        return "dashboard";
    }
    
    @GetMapping("/about")
    public String showAboutPage() {
        return "about"; 
    }
    @GetMapping("/books/detail/{id}")
    public String showBookDetails(@PathVariable Long id, ModelMap model) {
        Book book = bookService.getBookById(id);
        model.put("book", book);
        return "book_detail";
    }


}
