package com.insurance.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.exceptions.UserNotFoundException;
import com.insurance.model.User;
import com.insurance.repository.UserRepository;
import com.insurance.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> getAllUser(String role) {
		List<User> users;
		if (role != null) {
			users = userRepository.findAll().stream().filter(user -> user.getRole().equals(role)).toList();
		}
		users = userRepository.findAll();
		return users;
	}

	@Override
	public User updateUser(Integer id, User user) {
		User exsistingUser = userRepository.findById(id);

		if (exsistingUser == null) {
			throw new UserNotFoundException("User with id " + id + " not found");
		}

		exsistingUser.setUsername(user.getUsername());
		exsistingUser.setName(user.getName());
		exsistingUser.setMobileNo(user.getMobileNo());
		exsistingUser.setEmail(user.getEmail());
		exsistingUser.setRole(user.getRole());
		exsistingUser.setActive(user.isActive());
		exsistingUser.setPassword(user.getPassword());
		exsistingUser.setBirthDate(user.getBirthDate());
		exsistingUser.setGender(user.getGender());
		exsistingUser.setMaritalStatus(user.getMaritalStatus());
		exsistingUser.setAddress(user.getAddress());

		return userRepository.save(exsistingUser);
	}

	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

}
