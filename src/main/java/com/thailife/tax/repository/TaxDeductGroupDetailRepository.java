/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thailife.tax.repository;

import com.thailife.tax.entity.TaxDeductGroupDetail;

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
public interface TaxDeductGroupDetailRepository extends JpaRepository<TaxDeductGroupDetail, String>, JpaSpecificationExecutor<TaxDeductGroupDetail> {

	@Query("SELECT t FROM TaxDeductGroupDetail t where t.taxDeductGroupDetailPK.year=:year and t.taxDeductGroupDetailPK.deductGroupId=:deductGroupId and t.taxDeductGroupDetailPK.effectiveDate=(SELECT MAX(r.taxDeductGroupDetailPK.effectiveDate) FROM TaxDeductGroupDetail r where r.taxDeductGroupDetailPK.year=:year)")
    public List<TaxDeductGroupDetail> searchDataByYearAndDeductGroupId(@Param("year") String year,@Param("deductGroupId") String deductGroupId);
	
	@Query("SELECT t FROM TaxDeductGroupDetail t where t.taxDeductGroupDetailPK.year=:year and CURRENT_DATE< t.taxDeductGroupDetailPK.effectiveDate and t.taxDeductGroupDetailPK.deductGroupId=:deductGroupId")
    public List<TaxDeductGroupDetail> searchDataMoreCurrentDate(@Param("year") String year,@Param("deductGroupId") String deductGroupId); 
	
	@Query("SELECT t FROM TaxDeductGroupDetail t where t.taxDeductGroupDetailPK.year=:year and t.taxDeductGroupDetailPK.effectiveDate=:effectiveDate and t.taxDeductGroupDetailPK.deductGroupId=:deductGroupId")
    public List<TaxDeductGroupDetail> searchDataByYearAndEffectiveDate(@Param("year") String year,@Param("effectiveDate") Date effectiveDate,@Param("deductGroupId") String deductGroupId); 
	
	@Query("SELECT t FROM TaxDeductGroupDetail t where t.taxDeductGroupDetailPK.year=:year and t.taxDeductGroupDetailPK.taxDeductId=:taxDeductId and  t.taxDeductGroupDetailPK.effectiveDate=:effectiveDate and t.taxDeductGroupDetailPK.deductGroupId=:deductGroupId")
    public List<TaxDeductGroupDetail> checkDupData(@Param("year") String year,@Param("taxDeductId") String taxDeductId,@Param("effectiveDate") Date effectiveDate,@Param("deductGroupId") String deductGroupId); 
	
	
}
