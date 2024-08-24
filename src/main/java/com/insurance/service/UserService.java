package com.insurance.service;

import java.util.List;

import com.insurance.model.User;

public interface UserService {

	// Create
	public User createUser(User user);

	// Find
	public User findById(Integer id);

	public List<User> getAllUser(String role);

	// Update
	public User updateUser(Integer id, User user);

	// Delete
	public void deleteUser(Integer id);
}
