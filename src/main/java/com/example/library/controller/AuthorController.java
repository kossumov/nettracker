package com.example.library.controller;

import java.util.List;
import com.example.library.model.AuthorDto;
import com.example.library.service.AuthorService;
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
@RequestMapping("/author")
public class AuthorController {
  private final AuthorService authorService;

  @GetMapping("/{id}")
  public AuthorDto getAuthorById(@PathVariable Long id) {
    return authorService.getAuthor(id);
  }

  @PostMapping()
  public AuthorDto addAuthor(@RequestBody AuthorDto authorDto) {
    return authorService.addAuthor(authorDto);
  }

  @GetMapping()
  public List<AuthorDto> getAllAuthors() {
    return authorService.getAllAuthors();
  }

  @DeleteMapping("/{id}")
  public void deleteAuthor(@PathVariable Long id) {
    authorService.deleteAuthor(id);
  }

  @PutMapping("/{id}")
  public AuthorDto updateAuthorById(@PathVariable Long id,
                                    @RequestBody AuthorDto authorDto) {
    return authorService.updateAuthor(id, authorDto);
  }
}