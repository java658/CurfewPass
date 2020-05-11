package com.cts.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.model.Register;

	
	public interface RegisterRepo extends JpaRepository<Register, Integer>  {

		public Optional<Register> findByUserNameAndPassword(String userName, String password);



	}

