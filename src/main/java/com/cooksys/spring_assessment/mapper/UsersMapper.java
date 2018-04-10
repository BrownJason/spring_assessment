package com.cooksys.spring_assessment.mapper;

import org.mapstruct.Mapper;

import com.cooksys.spring_assessment.dto.UsersDto;
import com.cooksys.spring_assessment.entity.Users;

@Mapper(componentModel = "spring")
public interface UsersMapper {

	Users toEntity(UsersDto dto);
	
	UsersDto toDto(Users entity);
}
