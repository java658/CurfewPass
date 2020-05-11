package com.cts.service;


import java.util.List;

import com.cts.entity.UserEntity;

public interface RegisterService {
	
	public UserEntity insert(UserEntity user);
	
	public List<UserEntity> getAllUsers();
	
	public UserEntity getUserById(int id);
	
	public UserEntity getUserByUserNameAndPassword(String userName, String password);

	
}
