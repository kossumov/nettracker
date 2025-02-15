package com.example.library.mapper;

import com.example.library.entity.AuthorEntity;
import com.example.library.model.AuthorDto;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorMapper {

  AuthorDto toDto(AuthorEntity authorEntity);

}
