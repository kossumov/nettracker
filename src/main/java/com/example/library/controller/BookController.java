package com.example.library.controller;

import java.util.List;
import com.example.library.model.AuthorDto;
import com.example.library.model.BookDto;
import com.example.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {
  private final BookService bookService;

  @GetMapping("/{id}")
  public BookDto getBookById(@PathVariable Long id) {
    return bookService.getBook(id);
  }

  @PostMapping()
  public BookDto addBook(@RequestBody BookDto bookDto) {
    return bookService.addBook(bookDto);
  }

  @GetMapping()
  public List<BookDto> getAllBooks() {
    return bookService.getAllBooks();
  }

  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
  }

  @PutMapping("/{id}")
  public BookDto updateBookById(@PathVariable Long id,
                                @RequestBody BookDto bookDto) {
    return bookService.updateBook(id, bookDto);
  }
}