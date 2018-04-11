package com.cooksys.spring_assessment.service;

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
public class UsersService {

	private UsersRepository usersRepo;
	private UsersMapper userMap;
	private AddressesMapper addressMap;
	private AddressesRepository addressRepo;

	public UsersService(UsersRepository usersRepo, UsersMapper userMap, AddressesMapper addressMap, AddressesRepository addressRepo) {
		this.usersRepo = usersRepo;
		this.userMap = userMap;
		this.addressMap = addressMap;
		this.addressRepo = addressRepo;
	}
	
	public List<UsersDto> getAll() {
		// TODO Auto-generated method stub
		return usersRepo.findAll().stream().map(userMap::toDto).collect(Collectors.toList());
	}

	public List<UsersDto> getUserById(Long id) {
		// TODO Auto-generated method stub
		return usersRepo.findUsersById(id).stream().map(userMap::toDto).collect(Collectors.toList());
	}

	public List<AddressesDto> getUserAddress(Long id) {
		// TODO Auto-generated method stub
		return addressRepo.findAddressByResidentsId(id).stream().map(addressMap::toDto).collect(Collectors.toList());
	}

	public List<UsersDto> getUserRelations(Long id) {
		// TODO Auto-generated method stub
		return usersRepo.findUsersRelationsByRelationsId(id).stream().map(userMap::toDto).collect(Collectors.toList());
	}

	public Long post(UsersDto userDto) {
		return usersRepo.save(userMap.toEntity(userDto)).getId();
	}

	public void put(Long id, UsersDto usersDto) {
		usersDto.setId(id);
		usersRepo.save(userMap.toEntity(usersDto)).getId();
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
//		Tried this also but didn't work the same as the address
//		Users user = new Users();
//		for(Users relations : usersRepo.findAllRelationsById(id)) {
//			relations.getRelations().add(user);
//			relations.getRelations().remove(usersRepo.deleteRelationsById(id));
//		}
		usersRepo.deleteById(id);
		
	}

	@Transactional
	public void addRelations(Users user, Users relationUsers) {
		if(user.getRelations().contains(relationUsers) && relationUsers.getRelations().contains(user)) {
			// Do not add again!
		} else if (user.getRelations().contains(relationUsers)){
			relationUsers.getRelations().add(user);
		} else if (relationUsers.getRelations().contains(user)) {
			user.getRelations().add(relationUsers);
		} else {
			user.getRelations().add(relationUsers);
			relationUsers.getRelations().add(user);
		}
	}
	
	@Transactional
	public void addAddress(Users user, Addresses address) {
		if(user.getAddress() != null) {
			// Does not add another User
		} else {
			user.setAddress(address);
			address.getResidents().add(user);
		}
		
	}
}
