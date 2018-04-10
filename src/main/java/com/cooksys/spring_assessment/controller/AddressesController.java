package com.cooksys.spring_assessment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.spring_assessment.dto.AddressesDto;
import com.cooksys.spring_assessment.dto.UsersDto;
import com.cooksys.spring_assessment.service.AddressesService;

@RestController
@RequestMapping("addresses")
public class AddressesController {

	private AddressesService addressesService;

	public AddressesController(AddressesService addressesService) {
		this.addressesService = addressesService;
	}
	
	@GetMapping("{city}&{state}")
	public List<AddressesDto> getAllAddresses(@RequestParam(value="city", required=false) String city, @RequestParam(value="state", required=false) String state){
		return addressesService.getAllAddresses(city, state);
	}
	
	@GetMapping("{id}")
	public List<AddressesDto> getAddressById(@PathVariable Long id){
		return addressesService.getById(id);
	}
	
	@GetMapping("{id}/residents")
	public List<UsersDto> getResidents(@PathVariable Long id){
		return addressesService.getResidents(id);
	}
	
	@PostMapping
	public Long post(@RequestBody AddressesDto addressesDto) {
		Long id = addressesService.post(addressesDto);
		return id;
	}
	
	@PutMapping("{id}")
	public void put(@PathVariable Long id, @RequestBody AddressesDto addressesDto) {
		addressesService.put(id, addressesDto);
	}
	
	@DeleteMapping("{id}")
	public void delte(@PathVariable Long id) {
		addressesService.delete(id);
	}
}
