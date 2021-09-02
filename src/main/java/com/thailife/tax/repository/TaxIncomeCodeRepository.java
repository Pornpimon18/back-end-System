/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thailife.tax.repository;

import com.thailife.tax.entity.TaxIncomeCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.thailife.tax.object.criteria.RoleObjC;

import java.util.Date;
import java.util.List;
/**
 *
 * @author Parach
 */
@Repository
@Transactional
public interface TaxIncomeCodeRepository extends JpaRepository<TaxIncomeCode, String>, JpaSpecificationExecutor<TaxIncomeCode> {

	@Query("SELECT t FROM TaxIncomeCode t where t.status='ST001'")
    public List<TaxIncomeCode> searchDataAll(); 
	
	@Query("SELECT t FROM TaxIncomeCode t where t.id=:id")
    public TaxIncomeCode searchDataById(@Param("id") String id); 
	
	@Query("SELECT t FROM TaxIncomeCode t where t.name LIKE CONCAT(:name,'%') and t.status='ST001'")
    public TaxIncomeCode searchDataByName(@Param("name") String name); 
	
	@Query("SELECT t FROM TaxIncomeCode t where t.name LIKE CONCAT(:name,'%') and t.status LIKE CONCAT(:status,'%')")
    public List<TaxIncomeCode> searchDataByNameAndStatus(@Param("name") String roleName,@Param("status") String status); 
	
	@Query("SELECT t FROM TaxIncomeCode t where t.name=:name and t.status=:status")
    public List<TaxIncomeCode> checkDupDataByNameAndStatus(@Param("name") String roleName,@Param("status") String status); 
	
	
}
