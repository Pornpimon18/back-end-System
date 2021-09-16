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

import com.thailife.tax.entity.TaxDeduct;

@Repository
public class TaxDeductCustomRepository extends SimpleJpaRepository<TaxDeduct, String>{
	
	private EntityManager entityManager;
	
	public TaxDeductCustomRepository(EntityManager entityManager) {
		super(TaxDeduct.class, entityManager);
		this.entityManager = entityManager;
	}
	
	 @Transactional
	    public TaxDeduct saveEntity(TaxDeduct entity){

	        entityManager.unwrap(Session.class).save(entity);
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();
	        return entity;
	    }
	    
	@Transactional
	public List<TaxDeduct> saveList(List<TaxDeduct> objList) {
	        for (TaxDeduct item : objList) {
	            entityManager.unwrap(Session.class).save(item);
	            entityManager.unwrap(Session.class).flush();
	        }
	        entityManager.unwrap(Session.class).clear();

	        return objList;
	    }
	
	
	 @Transactional
	    public TaxDeduct updateEntity(TaxDeduct entity){

	        entityManager.unwrap(Session.class).merge(entity);
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();
	        return entity;
	    }
	    
	@Transactional
	public List<TaxDeduct> updateEntityList(List<TaxDeduct> objList) {
	        int i = 0;
	        for (TaxDeduct item : objList) {
	            entityManager.unwrap(Session.class).merge(item);
	            entityManager.unwrap(Session.class).flush();
	        }
	       
	        entityManager.unwrap(Session.class).clear();
	        return objList;
	    }

}
