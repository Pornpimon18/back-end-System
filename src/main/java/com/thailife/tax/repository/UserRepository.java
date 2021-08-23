/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thailife.tax.repository;

import com.thailife.tax.entity.Menu;
import com.thailife.tax.entity.RoleMenu;
import com.thailife.tax.entity.User;
import com.thailife.tax.object.MenuObj;

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
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

	@Query("SELECT u FROM User u")
    public List<User> searchDataAll(); 
	
	@Query("SELECT u FROM User u where u.id=:id")
    public User findByUserId(@Param("id") String id);
	
	@Query("SELECT u FROM User u where u.userName LIKE CONCAT(:userName,'%')")
    public User findByUserName(@Param("userName") String userName);
	
	@Query("SELECT u FROM User u where u.userName=:userName")
    public User findUserName(@Param("userName") String userName);
	
//	@Modifying
//    @Query("UPDATE EdcDataProcessingControl e SET e.processState = :processState , e.status = :status , e.updatedBy = :updatedBy , e.updatedDate = :updatedDate WHERE e.id = :id")
//    @Transactional
//    public void updateDataProcessingControl(@Param("id") String id,@Param("processState") String processState,@Param("status") String status,@Param("updatedBy") String updatedBy,@Param("updatedDate") Date updatedDate);

	
	
}
