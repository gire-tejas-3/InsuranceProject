package com.insurance.service;

import java.util.List;

import com.insurance.model.User;

public interface UserService {

	// Create
	public User createUser(User user);

	// Find
	public User findById(Integer id);

	public List<User> getAllUser();

	// Update
	public User updateUser(Integer id, User user) throws Exception;

	// Delete
	public void deleteUser(Integer id);
}
