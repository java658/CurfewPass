package com.cts.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.entity.UserEntity;
import com.cts.model.Register;
import com.cts.repo.RegisterRepo;
import com.cts.service.RegisterService;


@Service
public class RegisterServiceImpl implements RegisterService{
	
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RegisterRepo registerRepo;

	@Override
	public UserEntity insert(UserEntity user) {
		Register register=new Register();
		user.setUser("ROLE_USER");
		BeanUtils.copyProperties(user, register);
		
		registerRepo.save(register);
		return user;
	}

	@Override
	public List<UserEntity> getAllUsers() {
		List<Register> entities = registerRepo.findAll();
		List<UserEntity> usersDTO = new ArrayList<UserEntity>();
		for(Register entity: entities) {
			UserEntity userDTO = new UserEntity();
			BeanUtils.copyProperties(entity, userDTO);
			usersDTO.add(userDTO);
		}
		logger.info("Entity: "+entities);
		logger.info("DTO: "+usersDTO);
		return usersDTO;
	}

	@Override
	public UserEntity getUserByUserNameAndPassword(String userName, String password) {
		Register user = registerRepo.findByUserNameAndPassword(userName, password).get();
		UserEntity userDTO = new UserEntity();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}
	
	@Override
	public UserEntity getUserById(int id) throws NoClassDefFoundError{
		Optional<Register> user= registerRepo.findById(id);
		UserEntity register=new UserEntity();
		BeanUtils.copyProperties(user.get(), register);
		return register;
	}


}
