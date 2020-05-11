package com.cts.controller;

import java.util.List;

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

import com.cts.model.Details;
import com.cts.repo.DetailsRepo;
import com.cts.service.DetailsService;

@CrossOrigin(origins="*")
@EnableEurekaClient
@RestController
public class DetailsController {
Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	DetailsService detailsService;
	
	@Autowired
	DetailsRepo detailsRepo;
	
	@PostMapping("/details")
	public ResponseEntity<Details> save(@RequestBody Details user){
		detailsService.insert(user);
		return new ResponseEntity<Details>(user,HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/details/requests")
	public ResponseEntity<?>getAllRequests() {
		List<Details> list = detailsService.getAllRequests();
		if(list.size()>0)
		{
			return new ResponseEntity<List<Details>>(list , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("No users found",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/details/process")
	public ResponseEntity<?>getAllProcessing() {
		List<Details> list = detailsService.getAllProcessing();
		if(list.size()>0)
		{
			return new ResponseEntity<List<Details>>(list , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("No users found",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/details/finalstage")
	public ResponseEntity<?>getAllFinalStage() {
		List<Details> list = detailsService.getAllFinalStage();
		if(list.size()>0)
		{
			return new ResponseEntity<List<Details>>(list , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("No users found",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/details/{id}/status/processing")
	public ResponseEntity<Void> updateToProcessing(@PathVariable Integer id){
		detailsService.updateStatusToProcessing(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/details/{id}/status/finalstage")
	public ResponseEntity<Void> updateToFinalStage(@PathVariable Integer id){
		detailsService.updateProcessingToFinalStage(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/details/{id}/status/success")
	public ResponseEntity<Void> updateToSuccess(@PathVariable Integer id){
		detailsService.updateFinalStageToSuccess(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/details/{id}/status/rejected")
	public ResponseEntity<Void> updateToRejected(@PathVariable Integer id){
		detailsService.updateStatusToRejected(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/details/position/{id}/status")
	public ResponseEntity<List<Details>> findStatus(@PathVariable Integer id){
		List<Details> details = detailsService.getUserDetailsById(id);
		return new ResponseEntity<List<Details>>(details,HttpStatus.OK);
	}
	
	@GetMapping("/details/{id}")
	public ResponseEntity<?> getElementById(@PathVariable("id") int id) {
		try {
			Details user=detailsService.getElementById(id);
			return new ResponseEntity<Details>(user,HttpStatus.OK);
		}catch(NoClassDefFoundError e){
			return new ResponseEntity<String>("No such user found",HttpStatus.OK);
		}
	}
	

}
