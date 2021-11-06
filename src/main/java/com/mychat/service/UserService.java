package com.mychat.service;

import java.util.Optional;

import com.mychat.domain.User;

public interface UserService {

	Optional<User> findByEmail(String email);

	Optional<User> findByEmailAndPassword(String email, String password);
}
