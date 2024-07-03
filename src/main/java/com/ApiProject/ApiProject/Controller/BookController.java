package com.ApiProject.ApiProject.Controller;

import com.ApiProject.ApiProject.Repository.BookRepository;
import com.ApiProject.ApiProject.model.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

//
@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    private BookRepository repository;

    @GetMapping
    public List<BookModel> getAllBook(){
        System.out.println("Getting all books");
        return repository.findAll();
    }
    //
    @PostMapping
    public BookModel createBook(@RequestBody BookModel book){
        return repository.save(book);
    }
    @GetMapping("/{id}")
    public BookModel getBookById(@PathVariable Long id){
        return repository.findById(id).
                orElseThrow(() -> new RuntimeException("Book not found with id" + id));
    }
    @PutMapping("/{id}")
    public BookModel updateBook(@PathVariable Long id,@RequestBody BookModel bookDetails){
        BookModel book = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Exception"));
        book.setAuthor(bookDetails.getAuthor());
        book.setGenre(bookDetails.getGenre());
        book.setIsbn(bookDetails.getIsbn());
        book.setTitle(bookDetails.getTitle());
        book.setPublicationDate(bookDetails.getPublicationDate());
        return repository.save(book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id){
        BookModel book = repository.findById(id).orElseThrow(() -> new NoSuchElementException("no id found"));
        repository.delete(book);
        return "Book deleted successfully!!";
    }
}
