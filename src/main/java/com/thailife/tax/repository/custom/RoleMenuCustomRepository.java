package com.thailife.tax.repository.custom;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thailife.tax.entity.RoleMenu;
import com.thailife.tax.entity.UserRole;

@Repository
public class RoleMenuCustomRepository extends SimpleJpaRepository<RoleMenu, String>{
	
	private EntityManager entityManager;
	
	public RoleMenuCustomRepository(EntityManager entityManager) {
		super(RoleMenu.class, entityManager);
		this.entityManager = entityManager;
	}
	
	 @Transactional
	    public RoleMenu saveEntity(RoleMenu entity){

	        entityManager.unwrap(Session.class).save(entity);
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();
	        return entity;
	    }
	    
	@Transactional
	public List<RoleMenu> saveEntityList(List<RoleMenu> objList) {
	        int i = 0;
	        for (RoleMenu item : objList) {
	            entityManager.unwrap(Session.class).save(item);

		        entityManager.unwrap(Session.class).flush();
	        }
	        entityManager.unwrap(Session.class).clear();

	        return objList;
	    }
	
	
	 @Transactional
	    public RoleMenu updateEntity(RoleMenu entity){

	        entityManager.unwrap(Session.class).update(entity);
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();
	        return entity;
	    }
	    
	@Transactional
	public List<RoleMenu> updateEntityList(List<RoleMenu> objList) {
	        int i = 0;
	        for (RoleMenu item : objList) {
	            entityManager.unwrap(Session.class).update(item);
//	            entityManager.unwrap(Session.class).flush();
//	            entityManager.unwrap(Session.class).clear();
	        }
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();

	        return objList;
	    }
	
	@Transactional
	public RoleMenu deleteEntity(RoleMenu entity) {

		entityManager.unwrap(Session.class).remove(entity);
		entityManager.unwrap(Session.class).flush();
		entityManager.unwrap(Session.class).clear();
		return entity;
	}

	@Transactional
	public List<RoleMenu> deleteEntityList(List<RoleMenu> objList) {
		int i = 0;
		for (RoleMenu item : objList) {
			entityManager.unwrap(Session.class).remove(item);
			entityManager.unwrap(Session.class).flush();
		}

		entityManager.unwrap(Session.class).clear();
		return objList;
	}

}
