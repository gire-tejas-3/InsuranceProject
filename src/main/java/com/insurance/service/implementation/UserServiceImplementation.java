package com.insurance.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(Integer id, User user) throws Exception {
		if (!userRepository.existsById(id)) {
			throw new Exception("User Not Found with id: " + id);
		}
		return userRepository.save(user);

	}

	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

}
