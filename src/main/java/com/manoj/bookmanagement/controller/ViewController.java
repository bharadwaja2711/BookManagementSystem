package com.manoj.bookmanagement.controller;

import com.manoj.bookmanagement.model.Book;
import com.manoj.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private BookRepository bookRepository;
    
    @GetMapping("/")
    public String homePage() {
    	return "index";
    }

    @GetMapping("/books")
    public String viewBooksPage(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }
}
