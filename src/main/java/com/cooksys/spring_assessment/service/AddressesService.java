package com.cooksys.spring_assessment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cooksys.spring_assessment.dto.AddressesDto;
import com.cooksys.spring_assessment.dto.UsersDto;
import com.cooksys.spring_assessment.entity.Addresses;
import com.cooksys.spring_assessment.entity.Users;
import com.cooksys.spring_assessment.mapper.AddressesMapper;
import com.cooksys.spring_assessment.mapper.UsersMapper;
import com.cooksys.spring_assessment.repository.AddressesRepository;
import com.cooksys.spring_assessment.repository.UsersRepository;

@Service
public class AddressesService {

	private AddressesRepository addressRepo;
	private AddressesMapper addressMap;
	private UsersMapper userMap;
	private UsersRepository usersRepo;
	private UsersService usersService;

	public AddressesService(AddressesRepository addressRepo, AddressesMapper addressMap, UsersMapper userMap, UsersRepository usersRepo, UsersService usersService) {
		this.addressRepo = addressRepo;
		this.addressMap = addressMap;
		this.userMap = userMap;
		this.usersRepo = usersRepo;
		this.usersService = usersService;
	}

	public List<AddressesDto> getAllAddresses(String city, String state) {
		if (city != null && state != null) {
			return addressRepo.findAllDtosByCityAndState(city, state).stream().map(addressMap::toDto).collect(Collectors.toList());
		} else if(city != null) {
			return addressRepo.findAllDtosByCity(city).stream().map(addressMap::toDto).collect(Collectors.toList());
		} else if (state != null) {
			return addressRepo.findAllDtosByState(state).stream().map(addressMap::toDto).collect(Collectors.toList());
		} else {
			return addressRepo.findAll().stream().map(addressMap::toDto).collect(Collectors.toList());
		}
	}

	public List<AddressesDto> getById(Long id) {
		return addressRepo.findAddressesById(id).stream().map(addressMap::toDto).collect(Collectors.toList());
	}

	public List<UsersDto> getResidents(Long id) {
		return usersRepo.findUsersByAddressId(id).stream().map(userMap::toDto).collect(Collectors.toList());
	}

	public Long post(AddressesDto addressesDto) {
		return addressRepo.save(addressMap.toEntity(addressesDto)).getId();
	}

	public void put(Long id, AddressesDto addressesDto) {
		addressesDto.setId(id);
		addressRepo.save(addressMap.toEntity(addressesDto));
	}

	@Transactional
	public void delete(Long id) {
//		Tried this but got a Id doesn't exist, even though it does..
//		Users users = new Users();
//		for(Addresses address : addressRepo.findAllResidentsById(id)) {
//			address.getResidents().add(users);
//			address.getResidents().remove(addressRepo.deleteResidentsById(id));
//		}
		
		addressRepo.deleteById(id);
	}

}
