/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thailife.tax.repository;

import com.thailife.tax.entity.TaxDeductDetail;
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
public interface TaxDeductDetailRepository extends JpaRepository<TaxDeductDetail, String>, JpaSpecificationExecutor<TaxDeductDetail> {

	@Query("SELECT t FROM TaxDeductDetail t where t.taxDeductDetailPK.year=:year and t.taxDeductDetailPK.effectiveDate=(SELECT MAX(r.taxDeductDetailPK.effectiveDate) FROM TaxDeductDetail r where r.taxDeductDetailPK.year=:year)")
    public List<TaxDeductDetail> searchDataByYear(@Param("year") String year);
	
	@Query("SELECT t FROM TaxDeductDetail t where t.taxDeductDetailPK.year=:year and CURRENT_DATE< t.taxDeductDetailPK.effectiveDate")
    public List<TaxDeductDetail> searchDataMoreCurrentDate(@Param("year") String year); 
	
	@Query("SELECT t FROM TaxDeductDetail t where t.taxDeductDetailPK.year=:year and t.taxDeductDetailPK.effectiveDate=:effectiveDate")
    public List<TaxDeductDetail> searchDataByYearAndEffectiveDate(@Param("year") String year,@Param("effectiveDate") Date effectiveDate); 
	
	@Query("SELECT t FROM TaxDeductDetail t where t.taxDeductDetailPK.year=:year and t.taxDeductDetailPK.taxDeductId=:taxDeductId and  t.taxDeductDetailPK.effectiveDate=:effectiveDate")
    public List<TaxDeductDetail> checkDupData(@Param("year") String year,@Param("taxDeductId") String taxDeductId,@Param("effectiveDate") Date effectiveDate); 
	
	
}
