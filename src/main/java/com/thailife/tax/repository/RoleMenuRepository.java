/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thailife.tax.repository;
import com.thailife.tax.entity.RoleMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
/**
 *
 * @author Parach
 */
@Repository
@Transactional
public interface RoleMenuRepository extends JpaRepository<RoleMenu, String>, JpaSpecificationExecutor<RoleMenu> {
	

	@Query("SELECT rm FROM RoleMenu rm")
    public List<RoleMenu> searchDataAll();
	
	@Query("SELECT rm FROM RoleMenu rm where rm.roleId=:id")
    public List<RoleMenu> searchDataByRoleId(@Param("id") String id);
	
	
//	@Modifying
//    @Query("UPDATE EdcDataProcessingControl e SET e.processState = :processState , e.status = :status , e.updatedBy = :updatedBy , e.updatedDate = :updatedDate WHERE e.id = :id")
//    @Transactional
//    public void updateDataProcessingControl(@Param("id") String id,@Param("processState") String processState,@Param("status") String status,@Param("updatedBy") String updatedBy,@Param("updatedDate") Date updatedDate);

	
	
}
