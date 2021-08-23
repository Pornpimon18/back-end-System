package com.thailife.tax.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thailife.tax.base.ServiceBase;
import com.thailife.tax.entity.RoleMenu;
import com.thailife.tax.repository.RoleMenuRepository;
import com.thailife.tax.utils.IdGenerator;


@Service
public class RoleMenuService extends ServiceBase {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private RoleMenuRepository roleMenuRepository;
	
	@Autowired
	public RoleMenuService(RoleMenuRepository roleMenuRepository) {
		this.roleMenuRepository = roleMenuRepository;
	}
	
	public List<RoleMenu> searchDataAll() {
		return roleMenuRepository.searchDataAll();
	}
	
	public List<RoleMenu> searchDataByRoleId(String id) {
		return roleMenuRepository.searchDataByRoleId(id);
	}
	
	

	 public RoleMenuService(EntityManager entityManager) {
	        super();
	        this.entityManager = entityManager;
	    }
	
	   @Transactional
	    public RoleMenu saveEntity(RoleMenu entity) {
		   Session session = entityManager.unwrap(Session.class);
//	        entityManager.unwrap(Session.class).save(entity);
//	        entityManager.unwrap(Session.class).flush();
		   session.save(entity);
	        session.flush();
	        session.clear();
//	        roleMenuRepository.save(entity);
//	        entityManager.unwrap(Session.class).flush();
	        return entity;
	    }

	    @Transactional
	    public List<RoleMenu> saveEntityList(List<RoleMenu> entityList) {
	        int i = 0;
	        Session session = entityManager.unwrap(Session.class);
//	        int batchSize = rdMftConfigProperties.getRepositoryBatchSize();
	        for (RoleMenu item : entityList) {
	            i++;
	            
//	            roleMenuRepository.save(item);
//	            entityManager.unwrap(Session.class).save(item);
	            session.save(item);
	            session.flush();
	            session.clear();
//	            if (i % batchSize == 0)
//	                entityManager.flush();
	        }
	        entityManager.unwrap(Session.class).flush();
//	        session.flush();
//            session.clear();
//	        entityManager.unwrap(Session.class).flush();
	        return entityList;

	    }
	
	    
	    @Transactional
	    public RoleMenu updateEntity(RoleMenu entity) {
		   Session session = entityManager.unwrap(Session.class);
		   entityManager.unwrap(Session.class).update(entity);
	        entityManager.unwrap(Session.class).flush();
	        entityManager.unwrap(Session.class).clear();
	        return entity;
	    }

	    @Transactional
	    public List<RoleMenu> updateEntityList(List<RoleMenu> entityList) {
	        int i = 0;
//	        Session session = entityManager.unwrap(Session.class);
	        for (RoleMenu item : entityList) {
	            i++;
	            entityManager.unwrap(Session.class).update(item);
		        entityManager.unwrap(Session.class).flush();
//	            session.update(item);
//	            session.flush();
//	            session.clear();
	        }
	        entityManager.unwrap(Session.class).flush();
	        return entityList;

	    }

}
