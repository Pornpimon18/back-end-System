/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thailife.tax.repository;

import com.thailife.tax.entity.Menu;

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
public interface MenuRepository extends JpaRepository<Menu, String>, JpaSpecificationExecutor<Menu> {

	@Query("SELECT m FROM Menu m")
    public List<Menu> searchDataAllMenu(); 
	
	@Query("SELECT m FROM Menu m where m.id=:id")
    public Menu menuFindById(@Param("id") String id); 
	
	@Query("SELECT m FROM Menu m WHERE m.id IN (SELECT rm.menuId.id FROM RoleMenu rm WHERE rm.roleId.id IN (SELECT ur.roleId.id FROM UserRole ur JOIN User u on u.id = ur.userId.id WHERE u.id= :id))")
    public List<Menu> menuFromUserPermission(@Param("id") String id); 
	
	
	
	
//	@Modifying
//    @Query("UPDATE EdcDataProcessingControl e SET e.processState = :processState , e.status = :status , e.updatedBy = :updatedBy , e.updatedDate = :updatedDate WHERE e.id = :id")
//    @Transactional
//    public void updateDataProcessingControl(@Param("id") String id,@Param("processState") String processState,@Param("status") String status,@Param("updatedBy") String updatedBy,@Param("updatedDate") Date updatedDate);

	
	
}
