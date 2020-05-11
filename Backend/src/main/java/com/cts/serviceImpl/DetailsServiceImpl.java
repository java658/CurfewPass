package com.cts.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Details;
import com.cts.repo.DetailsRepo;
import com.cts.service.DetailsService;

@Service
public class DetailsServiceImpl implements DetailsService{
	
Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	DetailsRepo detailsRepo;

	@Override
	public Details insert(Details user) {
		//Details details=new Details();
		user.setStatus("requested");
		user.setOpenDateTime(LocalDateTime.now());
		//BeanUtils.copyProperties(user, details);
		detailsRepo.save(user);
		return user;
	}
	
	@Override
	public List<Details> getAllRequests() {
		List<Details> entities = detailsRepo.findAllRequests();
		List<Details> details = new ArrayList<Details>();
		for(Details data: entities) {
		//Details userDTO = new Details();
		//BeanUtils.copyProperties(data, userDTO);
		details.add(data);
		}
		return details;
	}

	@Override
	public List<Details> getAllProcessing() {
		List<Details> entities = detailsRepo.findAllProcessing();
		List<Details> details = new ArrayList<Details>();
		for(Details data: entities) {
//		Details userDTO = new Details();
//		BeanUtils.copyProperties(data, userDTO);
		details.add(data);
		}
		return details;
	}
	
	@Override
	public List<Details> getAllFinalStage() {
		List<Details> entities = detailsRepo.findAllFinalStage();
		List<Details> details = new ArrayList<Details>();
		for(Details data: entities) {
//		Details userDTO = new Details();
//		BeanUtils.copyProperties(data, userDTO);
		details.add(data);
		}
		return details;
	}


	@Override
	public void updateStatusToProcessing(Integer id) {
		detailsRepo.updateStatusToProcessing(id);
	}

	@Override
	public void updateProcessingToFinalStage(Integer id) {
		detailsRepo.updateProcessingToFinalStage(id);
	}
	
	@Override
	public void updateFinalStageToSuccess(Integer id) {
		detailsRepo.updateFinalStageToSuccess(id);
	}
	
	@Override
	public void updateStatusToRejected(Integer id) {
		detailsRepo.updateStatusToRejected(id);
	}

	@Override
	public List<Details> getUserDetailsById(Integer id) {
		return detailsRepo.findUserDetailsById(id);
	}
	
	@Override
	public Details getElementById(int id) {
		Optional<Details> detail = detailsRepo.findById(id);
		Details detailid = detail.get();
		return detailid;
	}

	
}
