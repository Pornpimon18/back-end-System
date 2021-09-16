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

import com.thailife.tax.entity.TaxCatalog;
import com.thailife.tax.entity.TaxRate;


@Repository
public class TaxCatalogCustomRepository extends SimpleJpaRepository<TaxCatalog, String>{
	
	private EntityManager entityManager;
	
	public TaxCatalogCustomRepository(EntityManager entityManager) {
		super(TaxCatalog.class, entityManager);
		this.entityManager = entityManager;
	}
	
	 @Transactional
	    public TaxCatalog saveEntity(TaxCatalog entity){

	        entityManager.unwrap(Session.class).save(entity);
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();
	        return entity;
	    }
	    
	@Transactional
	public List<TaxCatalog> saveList(List<TaxCatalog> objList) {
	        for (TaxCatalog item : objList) {
	            entityManager.unwrap(Session.class).save(item);
	            entityManager.unwrap(Session.class).flush();
	        }
	        entityManager.unwrap(Session.class).clear();

	        return objList;
	    }
	
	
	 @Transactional
	    public TaxCatalog updateEntity(TaxCatalog entity){

	        entityManager.unwrap(Session.class).merge(entity);
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();
	        return entity;
	    }
	    
	@Transactional
	public List<TaxCatalog> updateEntityList(List<TaxCatalog> objList) {
	        int i = 0;
	        for (TaxCatalog item : objList) {
	            entityManager.unwrap(Session.class).merge(item);
	            entityManager.unwrap(Session.class).flush();
	        }
	       
	        entityManager.unwrap(Session.class).clear();
	        return objList;
	    }

}
