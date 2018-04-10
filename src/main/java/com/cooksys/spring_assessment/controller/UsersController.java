package com.cooksys.spring_assessment.controller;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.spring_assessment.dto.AddressesDto;
import com.cooksys.spring_assessment.dto.UsersDto;
import com.cooksys.spring_assessment.entity.Addresses;
import com.cooksys.spring_assessment.entity.Users;
import com.cooksys.spring_assessment.service.UsersService;

@RestController
@RequestMapping("users")
public class UsersController {

	private UsersService usersService;

	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}
	
	@GetMapping
	public List<UsersDto> getUsers() {
		return usersService.getAll();
	}
	
	@GetMapping("{id}")
	public List<UsersDto> getUsersById(@PathVariable Long id){
		return usersService.getUserById(id);
	}
	
	@GetMapping("{id}/address")
	public List<AddressesDto> getUsersAddress(@PathVariable Long id){
		return usersService.getUserAddress(id);
	}
	
	@GetMapping("{id}/relations")
	public List<UsersDto> getUsersRelations(@PathVariable Long id){
		return usersService.getUserRelations(id);
	}
	
	@PostMapping
	public Long post(@RequestBody UsersDto userDto) {
		Long id = usersService.post(userDto);
		return id;
	}
	
	@PostMapping("{id}/relations/{relationId}")
	@Transactional
	public void attachRelations(@PathVariable(name="id") Users user, @PathVariable(name="relationId") List<Users> relationUsers) {
		user.setRelations(relationUsers);
		relationUsers.add(user);
	}
	
	@PostMapping("{id}/address/{addressId}")
	@Transactional
	public void attachAddress(@PathVariable(name="id") Users user, @PathVariable(name="addressId") Addresses address) {
		user.setAddress(address);
		address.getResidents().add(user);
	}
	
	@PutMapping("{id}")
	public void put(@PathVariable Long id, @RequestBody UsersDto usersDto) {
		usersService.put(id, usersDto);
	}
	
	@DeleteMapping("{id}")
	public void delte(@PathVariable Long id) {
		usersService.delete(id);
	}
}