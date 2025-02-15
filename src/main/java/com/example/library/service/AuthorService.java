package com.example.library.service;

import java.util.List;
import java.util.stream.Collectors;
import com.example.library.entity.AuthorEntity;
import com.example.library.model.AuthorDto;
import com.example.library.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorService {
  private final AuthorRepository authorRepository;
  private final KafkaService kafkaService;

  public List<AuthorDto> getAllAuthors() {
    ModelMapper modelMapper = new ModelMapper();
    return authorRepository.findAll()
        .stream()
        .map(author -> modelMapper.map(author, AuthorDto.class))
        .collect(Collectors.toList());
  }

  public AuthorDto getAuthor(Long id) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(
        authorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Author with ID " + id + " not found")),
        AuthorDto.class);
  }

  public AuthorDto addAuthor(AuthorDto authorDto) {
    AuthorEntity authorEntity = new AuthorEntity(authorDto.getName());
    authorRepository.save(authorEntity);

    kafkaService.sendMessage("author.events", "saved author " + authorEntity.getId());

    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(authorEntity, AuthorDto.class);
  }

  public void deleteAuthor(Long id) {
    authorRepository.deleteById(id);

    kafkaService.sendMessage("author.events", "deleted author " + id);
  }

  public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
    AuthorEntity authorEntity = authorRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Author with ID " + id + " not found"));
    authorEntity.setName(authorDto.getName());
    authorRepository.save(authorEntity);

    kafkaService.sendMessage("author.events", "updated author " + id);

    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(authorEntity, AuthorDto.class);
  }
}