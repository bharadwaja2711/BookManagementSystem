package com.manoj.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manoj.bookmanagement.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
