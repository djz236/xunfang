package com.immoc.repository;

import org.springframework.data.repository.CrudRepository;

import com.immoc.entity.User;

public interface UserRepository extends CrudRepository<User,Long> {

	User findByName(String userName);
}
