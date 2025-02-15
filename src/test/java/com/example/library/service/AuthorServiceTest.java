package com.example.library.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;
import com.example.library.entity.AuthorEntity;
import com.example.library.model.AuthorDto;
import com.example.library.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {
  @Mock
  private AuthorRepository authorRepository;
  @Mock
  private KafkaService kafkaService;
  @InjectMocks
  private AuthorService authorService;

  @Test
  void getAuthor_successful() {
    // given
    AuthorEntity authorEntity = new AuthorEntity();
    authorEntity.setId(10L);
    authorEntity.setName("Test Name");

    ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);

    // when
    when(authorRepository.findById(longCaptor.capture()))
        .thenReturn(Optional.of(authorEntity));

    // then
    AuthorDto result = authorService.getAuthor(10L);
    assertEquals(result.getName(), authorEntity.getName());
  }
}