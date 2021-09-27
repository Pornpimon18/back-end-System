/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thailife.tax.repository;

import com.thailife.tax.entity.TaxSystemInfo;
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
public interface TaxSystemInfoRepository extends JpaRepository<TaxSystemInfo, String>, JpaSpecificationExecutor<TaxSystemInfo> {

	@Query("SELECT t FROM TaxSystemInfo t where t.status='active'")
    public List<TaxSystemInfo> searchDataAll(); 
	
	@Query("SELECT t FROM TaxSystemInfo t where t.id=:id")
    public TaxSystemInfo searchDataById(@Param("id") String id); 
	
	@Query("SELECT t FROM TaxSystemInfo t where t.name LIKE CONCAT(:name,'%') and t.status='active'")
    public TaxSystemInfo searchDataByName(@Param("name") String name); 
	
	@Query("SELECT t FROM TaxSystemInfo t where t.name LIKE CONCAT(:name,'%') and t.status=:status")
    public List<TaxSystemInfo> searchDataByNameAndStatus(@Param("name") String name,@Param("status") Status status); 
	
	@Query("SELECT t FROM TaxSystemInfo t where t.name=:name and t.status='active'")
    public List<TaxSystemInfo> checkDupDataByNameAndStatus(@Param("name") String name); 
	
	
}
