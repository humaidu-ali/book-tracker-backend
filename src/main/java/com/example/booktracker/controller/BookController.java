package com.example.booktracker.controller;

import com.example.booktracker.exception.ResourceNotFoundException;
import com.example.booktracker.model.Book;
import com.example.booktracker.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Integer id)
        throws ResourceNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: " + id));
        return ResponseEntity.ok().body(book);

    }

    @PostMapping("/books/add")
    public Book addBook( @RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/books/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Integer id, @RequestBody Book bookDetails)
        throws ResourceNotFoundException{
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book not found for this id :: " + id));

                book.setId(bookDetails.getId());
                book.setTitle(bookDetails.getTitle());
                book.setAuthor(bookDetails.getAuthor());
                book.setPublicationYear(bookDetails.getPublicationYear());
                final Book updatedBook = bookRepository.save(book);
                return ResponseEntity.ok(updatedBook);

    }

    @DeleteMapping("/books/delete/{id}")
    public Map<String,Boolean>deleteBook(@PathVariable(value = "id") Integer id )
        throws ResourceNotFoundException{
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(" Book not found by this id :: " + id));

        bookRepository.delete(book);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }


}
