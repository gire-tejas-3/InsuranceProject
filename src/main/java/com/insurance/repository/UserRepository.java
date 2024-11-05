package com.insurance.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {

	public User findById(Integer id);

	public User findByUsername(String username);
}
