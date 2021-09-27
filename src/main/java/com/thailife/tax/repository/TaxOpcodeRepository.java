/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thailife.tax.repository;

import com.thailife.tax.entity.TaxOpcode;
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
public interface TaxOpcodeRepository extends JpaRepository<TaxOpcode, String>, JpaSpecificationExecutor<TaxOpcode> {

	@Query("SELECT t FROM TaxOpcode t where t.status='active'")
    public List<TaxOpcode> searchDataAll(); 
	
	@Query("SELECT t FROM TaxOpcode t where t.id=:id")
    public TaxOpcode searchDataById(@Param("id") String id); 
	
	@Query("SELECT t FROM TaxOpcode t where t.name LIKE CONCAT(:name,'%') and t.status='active'")
    public TaxOpcode searchDataByName(@Param("name") String name); 
	
	@Query("SELECT t FROM TaxOpcode t where t.name LIKE CONCAT(:name,'%') and t.status=:status")
    public List<TaxOpcode> searchDataByNameAndStatus(@Param("name") String name,@Param("status") Status status); 
	
	@Query("SELECT t FROM TaxOpcode t where t.name=:name and t.status='active'")
    public List<TaxOpcode> checkDupDataByNameAndStatus(@Param("name") String name); 
	
	
}
