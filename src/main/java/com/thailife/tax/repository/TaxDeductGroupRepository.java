/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thailife.tax.repository;

import com.thailife.tax.entity.TaxDeductGroup;
import com.thailife.tax.utils.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
/**
 *
 * @author Parach
 */
@Repository
@Transactional
public interface TaxDeductGroupRepository extends JpaRepository<TaxDeductGroup, String>, JpaSpecificationExecutor<TaxDeductGroup> {

	@Query("SELECT t FROM TaxDeductGroup t where t.status='active'")
    public List<TaxDeductGroup> searchDataAll(); 
	
	@Query("SELECT t FROM TaxDeductGroup t where t.id=:id")
    public TaxDeductGroup searchDataById(@Param("id") String id); 
	
	@Query("SELECT t FROM TaxDeductGroup t where t.name LIKE CONCAT(:name,'%') and t.status='active'")
    public TaxDeductGroup searchDataByName(@Param("name") String name); 
	
	@Query("SELECT t FROM TaxDeductGroup t where t.name LIKE CONCAT(:name,'%') and t.status=:status")
    public List<TaxDeductGroup> searchDataByNameAndStatus(@Param("name") String name,@Param("status") Status status); 
	
	@Query("SELECT t FROM TaxDeductGroup t where t.name=:name and t.status='active'")
    public List<TaxDeductGroup> checkDupDataByNameAndStatus(@Param("name") String name); 
	
	
}
