package com.mychat.service;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.mychat.domain.User;
import com.mychat.reporitory.UserRepository;

@Stateless
public class UserServiceImpl implements UserService {

	@Inject
	private UserRepository repository;

	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub

		return repository.findByEmail(email);
	}

	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}
}
