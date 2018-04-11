package com.cooksys.spring_assessment.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.spring_assessment.dto.UsersDto;
import com.cooksys.spring_assessment.entity.Addresses;
import com.cooksys.spring_assessment.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>  {

	List<Users> findUsersById(Long id);

	Collection<Addresses> findAddressByAddressId(Long id);

	Collection<Users> findUsersRelationsByRelationsId(Long id);

	Collection<Users> findUsersByAddressId(Long id);

	Users Id(Long id);

}
