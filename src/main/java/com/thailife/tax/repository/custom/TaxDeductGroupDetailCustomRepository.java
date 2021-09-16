package com.thailife.tax.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thailife.tax.entity.TaxDeductGroupDetail;

@Repository
public class TaxDeductGroupDetailCustomRepository extends SimpleJpaRepository<TaxDeductGroupDetail, String>{
	
	private EntityManager entityManager;
	
	public TaxDeductGroupDetailCustomRepository(EntityManager entityManager) {
		super(TaxDeductGroupDetail.class, entityManager);
		this.entityManager = entityManager;
	}
	
	 @Transactional
	    public TaxDeductGroupDetail saveEntity(TaxDeductGroupDetail entity){

	        entityManager.unwrap(Session.class).save(entity);
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();
	        return entity;
	    }
	    
	@Transactional
	public List<TaxDeductGroupDetail> saveList(List<TaxDeductGroupDetail> objList) {
	        for (TaxDeductGroupDetail item : objList) {
	            entityManager.unwrap(Session.class).save(item);
	            entityManager.unwrap(Session.class).flush();
	        }
	        entityManager.unwrap(Session.class).clear();

	        return objList;
	    }
	
	
	 @Transactional
	    public TaxDeductGroupDetail updateEntity(TaxDeductGroupDetail entity){

	        entityManager.unwrap(Session.class).merge(entity);
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();
	        return entity;
	    }
	    
	@Transactional
	public List<TaxDeductGroupDetail> updateEntityList(List<TaxDeductGroupDetail> objList) {
	        int i = 0;
	        for (TaxDeductGroupDetail item : objList) {
	            entityManager.unwrap(Session.class).merge(item);
	            entityManager.unwrap(Session.class).flush();
	        }
	       
	        entityManager.unwrap(Session.class).clear();
	        return objList;
	    }

	@Transactional
	public List<TaxDeductGroupDetail> deleteEntityList(List<TaxDeductGroupDetail> objList) {
		int i = 0;
		for (TaxDeductGroupDetail item : objList) {
			entityManager.unwrap(Session.class).remove(item);
			entityManager.unwrap(Session.class).flush();
		}

		entityManager.unwrap(Session.class).clear();
		return objList;
	}
}
