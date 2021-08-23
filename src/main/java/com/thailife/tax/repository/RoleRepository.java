/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thailife.tax.repository;

import com.thailife.tax.entity.Menu;
import com.thailife.tax.entity.Role;
import com.thailife.tax.object.MenuObj;
import com.thailife.tax.object.RoleObj;

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
public interface RoleRepository extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

	@Query("SELECT r FROM Role r where r.status='ST001'")
    public List<Role> searchDataAll(); 
	
	@Query("SELECT r FROM Role r where r.id=:id")
    public Role searchDataById(@Param("id") String id); 
	
	@Query("SELECT r FROM Role r where r.roleName LIKE CONCAT(:roleName,'%') and r.status='ST001'")
    public Role searchDataByRoleName(@Param("roleName") String roleName); 
	
	@Query("SELECT r FROM Role r where r.roleName LIKE CONCAT(:roleName,'%') and r.status LIKE CONCAT(:status,'%')")
    public List<Role> searchDataByRoleNameAndStatus(@Param("roleName") String roleName,@Param("status") String status); 
	
	@Query("SELECT r FROM Role r where r.roleName=:roleName and r.status=:status")
    public List<Role> checkDupDataByRoleNameAndStatus(@Param("roleName") String roleName,@Param("status") String status); 
	
	@Query("SELECT r FROM Role r JOIN UserRole ur on r.id = ur.roleId where ur.roleId=:roleId and ur.status=:status")
    public List<Role> checkUserUseRole(@Param("roleId") String roleId,@Param("status") String status); 
	
//	@Modifying
//    @Query("UPDATE EdcDataProcessingControl e SET e.processState = :processState , e.status = :status , e.updatedBy = :updatedBy , e.updatedDate = :updatedDate WHERE e.id = :id")
//    @Transactional
//    public void updateDataProcessingControl(@Param("id") String id,@Param("processState") String processState,@Param("status") String status,@Param("updatedBy") String updatedBy,@Param("updatedDate") Date updatedDate);

	
	
}
