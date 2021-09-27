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

import com.thailife.tax.entity.TaxSystemInfo;


@Repository
public class TaxSystemInfoCustomRepository extends SimpleJpaRepository<TaxSystemInfo, String>{
	
	private EntityManager entityManager;
	
	public TaxSystemInfoCustomRepository(EntityManager entityManager) {
		super(TaxSystemInfo.class, entityManager);
		this.entityManager = entityManager;
	}
	
	 @Transactional
	    public TaxSystemInfo saveEntity(TaxSystemInfo entity){

	        entityManager.unwrap(Session.class).save(entity);
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();
	        return entity;
	    }
	    
	@Transactional
	public List<TaxSystemInfo> saveList(List<TaxSystemInfo> objList) {
	        for (TaxSystemInfo item : objList) {
	            entityManager.unwrap(Session.class).save(item);
	            entityManager.unwrap(Session.class).flush();
	        }
	        entityManager.unwrap(Session.class).clear();

	        return objList;
	    }
	
	
	 @Transactional
	    public TaxSystemInfo updateEntity(TaxSystemInfo entity){

	        entityManager.unwrap(Session.class).merge(entity);
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();
	        return entity;
	    }
	    
	@Transactional
	public List<TaxSystemInfo> updateEntityList(List<TaxSystemInfo> objList) {
	        int i = 0;
	        for (TaxSystemInfo item : objList) {
	            entityManager.unwrap(Session.class).merge(item);
	            entityManager.unwrap(Session.class).flush();
	        }
	       
	        entityManager.unwrap(Session.class).clear();
	        return objList;
	    }

}
