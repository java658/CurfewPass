
package com.cts.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cts.entity.UserEntity;
import com.cts.repo.RegisterRepo;
import com.cts.service.RegisterService;

@CrossOrigin(origins="*")
@EnableEurekaClient
@RestController
public class RegisterController {
	
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RegisterService registerService;
	
	@Autowired
	RegisterRepo registerRepo;
	
	
	@GetMapping("/register")
	public ResponseEntity<?>getallusers() {
		List<UserEntity> list = registerService.getAllUsers();
		if(list.size()>0)
		{
			return new ResponseEntity<List<UserEntity>>(list , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("No users found",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/register/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
		try {
			UserEntity user=registerService.getUserById(id);
			return new ResponseEntity<UserEntity>(user,HttpStatus.OK);
		}catch(NoClassDefFoundError e){
			return new ResponseEntity<String>("No such user found",HttpStatus.OK);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserEntity> save(@RequestBody UserEntity user){
		registerService.insert(user);
		return new ResponseEntity<UserEntity>(user,HttpStatus.OK);
	}
	
	@GetMapping(value="/register/login")
	public ResponseEntity<?> login(HttpServletRequest request){
		String authorization=request.getHeader("Authorization");
		logger.info("Attempt to login through token --> {}",authorization);
		String userName=null;
		String password=null;
		if(authorization != null && authorization.startsWith("Basic")) {
			String base64Credentials = authorization.substring("Basic".length()).trim();
			byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
			String credentials = new String(credDecoded,StandardCharsets.UTF_8);
			userName=credentials.split(":")[0];
			password=credentials.split(":")[1];
			logger.info("Username {} and Password {}",userName,password);
		}
		try {
			UserEntity user= registerService.getUserByUserNameAndPassword(userName,password);
			logger.info("user logged --> {}",userName);
			System.out.println(user);
			return new ResponseEntity<UserEntity>(user,HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
			e.printStackTrace();
			logger.info("Unauthorized access Stack Trace--> {}", e.getStackTrace().toString());
			return new ResponseEntity<String>("No user found",HttpStatus.BAD_REQUEST);
		}
	}		

}	

