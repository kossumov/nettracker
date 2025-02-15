package com.example.library.mapper;

import com.example.library.entity.AuthorEntity;
import com.example.library.entity.BookEntity;
import com.example.library.model.AuthorDto;
import com.example.library.model.BookDto;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {

  BookDto toDto(BookEntity bookEntity);

}
