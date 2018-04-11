package com.cooksys.spring_assessment.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.spring_assessment.entity.Addresses;

public interface AddressesRepository extends JpaRepository<Addresses, Long>{

	List<Addresses> findAllDtosByCity(String city);

	List<Addresses> findAllDtosByState(String state);

	List<Addresses> findAllDtosByCityAndState(String city, String state);

	List<Addresses> findAddressesById(Long id);

	Collection<Addresses> findAddressByResidentsId(Long id);

	void deleteAllUsersByResidentsId(Long id);

	List<Addresses> findAllResidentsById(Long id);

	int deleteResidentsById(Long id);

}
