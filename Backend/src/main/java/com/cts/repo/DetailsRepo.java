package com.cts.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cts.model.Details;

public interface DetailsRepo extends JpaRepository<Details, Integer> {
	
    @Query(value = "SELECT * FROM data where status='requested' ", nativeQuery = true)
    List<Details> findAllRequests();
    
    @Query(value = "SELECT * FROM data  where status='Government Accepted' ", nativeQuery = true)
    List<Details> findAllProcessing();
    
    @Query(value = "SELECT * FROM data  where status='Doctor Accepted' ", nativeQuery = true)
    List<Details> findAllFinalStage();
    
    @Query(value = "SELECT * FROM data  WHERE id=?1 ", nativeQuery = true)
    public List<Details> findUserDetailsById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE data  SET status='Government Accepted' WHERE id=?1 ", nativeQuery = true)
    public void updateStatusToProcessing(Integer id);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE data  SET status='Doctor Accepted' WHERE id=?1 ", nativeQuery = true)
    public void updateProcessingToFinalStage(Integer id);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE data  SET status='Approved' WHERE id=?1 ", nativeQuery = true)
    public void updateFinalStageToSuccess(Integer id);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE user_data SET status='rejected' WHERE id=?1 ", nativeQuery = true)
    public void updateStatusToRejected(Integer id);
    

}
