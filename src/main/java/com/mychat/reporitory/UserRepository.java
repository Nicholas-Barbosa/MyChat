package com.mychat.reporitory;

import java.util.Optional;

import com.mychat.domain.User;

public interface UserRepository {

	Optional<User> findByEmail(String email);
	
	Optional<User> findByEmailAndPassword(String email,String password);
}
