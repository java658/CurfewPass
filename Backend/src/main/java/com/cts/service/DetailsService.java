package com.cts.service;

import java.util.List;


import com.cts.model.Details;

public interface DetailsService {
	
public Details insert(Details user);
	
	public List<Details> getAllProcessing();
	
	public List<Details> getAllRequests();
	
	public List<Details> getAllFinalStage();
	
	public List<Details> getUserDetailsById(Integer id);
	
	public Details getElementById(int id);
	
	public void updateStatusToProcessing(Integer id);
	
	public void updateProcessingToFinalStage(Integer id);
	
	public void updateFinalStageToSuccess(Integer id);
	
	public void updateStatusToRejected(Integer id);
	

}
