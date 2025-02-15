package com.example.library.service;

import java.util.List;
import java.util.stream.Collectors;
import com.example.library.entity.AuthorEntity;
import com.example.library.entity.BookEntity;
import com.example.library.model.AuthorDto;
import com.example.library.model.BookDto;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final KafkaService kafkaService;

  public List<BookDto> getAllBooks() {
    ModelMapper modelMapper = new ModelMapper();
    return bookRepository.findAll()
        .stream()
        .map(book -> modelMapper.map(book, BookDto.class))
        .collect(Collectors.toList());
  }

  public BookDto getBook(Long id) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(
        bookRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Book with ID " + id + " not found")),
        BookDto.class);
  }

  public BookDto addBook(BookDto bookDto) {
    BookEntity bookEntity = new BookEntity(bookDto.getTitle());
    AuthorEntity authorEntity = authorRepository.findById(bookDto.getAuthorId())
        .orElseThrow(() -> new EntityNotFoundException("Author with ID " + bookDto.getAuthorId() + " not found"));

    bookEntity.setAuthor(authorEntity);
    bookRepository.save(bookEntity);

    kafkaService.sendMessage("book.events", "saved book " + bookEntity.getId());

    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(bookEntity, BookDto.class);
  }

  public void deleteBook(Long id) {
    bookRepository.deleteById(id);

    kafkaService.sendMessage("book.events", "deleted book " + id);
  }

  public BookDto updateBook(Long id, BookDto bookDto) {
    BookEntity bookEntity = bookRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Book with ID " + id + " not found"));
    bookEntity.setTitle(bookDto.getTitle());
    AuthorEntity authorEntity = authorRepository.findById(bookDto.getAuthorId())
        .orElseThrow(() -> new EntityNotFoundException("Author with ID " + bookDto.getAuthorId() + " not found"));
    bookEntity.setAuthor(authorEntity);
    bookRepository.save(bookEntity);

    kafkaService.sendMessage("book.events", "updated book " + id);

    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(bookEntity, BookDto.class);
  }
}