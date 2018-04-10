package com.cooksys.spring_assessment.mapper;

import org.mapstruct.Mapper;

import com.cooksys.spring_assessment.dto.AddressesDto;
import com.cooksys.spring_assessment.entity.Addresses;

@Mapper(componentModel = "spring")
public interface AddressesMapper {

	Addresses toEntity(AddressesDto dto);
	
	AddressesDto toDto(Addresses entity);
}
