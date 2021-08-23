package com.thailife.tax.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thailife.tax.entity.Role;
import com.thailife.tax.object.criteria.RoleObjC;

@Repository
public class RoleCustomRepository extends SimpleJpaRepository<Role, String>{
	
	private SessionFactory sessionFactory;
	
	private EntityManager entityManager;
	
	public RoleCustomRepository(EntityManager entityManager) {
		super(Role.class, entityManager);
		this.entityManager = entityManager;
	}
	
	 @Transactional
	    public Role saveEntity(Role entity){

	        entityManager.unwrap(Session.class).save(entity);
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();
	        return entity;
	    }
	    
	@Transactional
	public List<Role> saveList(List<Role> objList) {
	        for (Role item : objList) {
	            entityManager.unwrap(Session.class).save(item);
	            entityManager.unwrap(Session.class).flush();
	        }
	        entityManager.unwrap(Session.class).clear();

	        return objList;
	    }
	
	
	 @Transactional
	    public Role updateEntity(Role entity){

	        entityManager.unwrap(Session.class).merge(entity);
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();
	        return entity;
	    }
	    
	@Transactional
	public List<Role> updateEntityList(List<Role> objList) {
	        int i = 0;
	        for (Role item : objList) {
	            entityManager.unwrap(Session.class).merge(item);
	            entityManager.unwrap(Session.class).flush();
	        }
	       
	        entityManager.unwrap(Session.class).clear();
	        return objList;
	    }

}
