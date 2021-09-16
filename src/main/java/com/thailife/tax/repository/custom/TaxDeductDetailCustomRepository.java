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

import com.thailife.tax.entity.TaxDeductDetail;

@Repository
public class TaxDeductDetailCustomRepository extends SimpleJpaRepository<TaxDeductDetail, String>{
	
	private EntityManager entityManager;
	
	public TaxDeductDetailCustomRepository(EntityManager entityManager) {
		super(TaxDeductDetail.class, entityManager);
		this.entityManager = entityManager;
	}
	
	 @Transactional
	    public TaxDeductDetail saveEntity(TaxDeductDetail entity){

	        entityManager.unwrap(Session.class).save(entity);
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();
	        return entity;
	    }
	    
	@Transactional
	public List<TaxDeductDetail> saveList(List<TaxDeductDetail> objList) {
	        for (TaxDeductDetail item : objList) {
	            entityManager.unwrap(Session.class).save(item);
	            entityManager.unwrap(Session.class).flush();
	        }
	        entityManager.unwrap(Session.class).clear();

	        return objList;
	    }
	
	
	 @Transactional
	    public TaxDeductDetail updateEntity(TaxDeductDetail entity){

	        entityManager.unwrap(Session.class).merge(entity);
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();
	        return entity;
	    }
	    
	@Transactional
	public List<TaxDeductDetail> updateEntityList(List<TaxDeductDetail> objList) {
	        int i = 0;
	        for (TaxDeductDetail item : objList) {
	            entityManager.unwrap(Session.class).merge(item);
	            entityManager.unwrap(Session.class).flush();
	        }
	       
	        entityManager.unwrap(Session.class).clear();
	        return objList;
	    }

	@Transactional
	public List<TaxDeductDetail> deleteEntityList(List<TaxDeductDetail> objList) {
		int i = 0;
		for (TaxDeductDetail item : objList) {
			entityManager.unwrap(Session.class).remove(item);
			entityManager.unwrap(Session.class).flush();
		}

		entityManager.unwrap(Session.class).clear();
		return objList;
	}
}
