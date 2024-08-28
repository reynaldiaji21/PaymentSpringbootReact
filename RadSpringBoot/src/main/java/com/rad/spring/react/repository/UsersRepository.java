package com.rad.spring.react.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rad.spring.react.entity.Users;

public interface UsersRepository extends JpaRepository<Users, String>{

	public boolean existsByEmail(String email);
}
