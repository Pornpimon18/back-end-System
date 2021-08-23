package com.thailife.tax.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thailife.tax.entity.Role;
import com.thailife.tax.entity.UserRole;

@Repository
public class UserRoleCustomRepository extends SimpleJpaRepository<UserRole, String> {

	private EntityManager entityManager;

	public UserRoleCustomRepository(EntityManager entityManager) {
		super(UserRole.class, entityManager);
		this.entityManager = entityManager;
	}

	@Transactional
	public UserRole saveEntity(UserRole entity) {
		entityManager.unwrap(Session.class).save(entity);
		entityManager.unwrap(Session.class).flush();
		entityManager.unwrap(Session.class).clear();
		return entity;
	}

	@Transactional
	public List<UserRole> saveEntityList(List<UserRole> objList) {
		for (UserRole item : objList) {
			entityManager.unwrap(Session.class).save(item);
			entityManager.unwrap(Session.class).flush();
		}
		entityManager.unwrap(Session.class).clear();

		return objList;
	}

	@Transactional
	public UserRole updateEntity(UserRole entity) {

		entityManager.unwrap(Session.class).update(entity);
		entityManager.unwrap(Session.class).flush();
		entityManager.unwrap(Session.class).clear();
		return entity;
	}

	@Transactional
	public List<UserRole> updateEntityList(List<UserRole> objList) {
		int i = 0;
		for (UserRole item : objList) {
			entityManager.unwrap(Session.class).update(item);
			entityManager.unwrap(Session.class).flush();
		}

		entityManager.unwrap(Session.class).clear();
		return objList;
	}

	@Transactional
	public UserRole deleteEntity(UserRole entity) {

		entityManager.unwrap(Session.class).remove(entity);
		entityManager.unwrap(Session.class).flush();
		entityManager.unwrap(Session.class).clear();
		return entity;
	}

	@Transactional
	public List<UserRole> deleteEntityList(List<UserRole> objList) {
		int i = 0;
		for (UserRole item : objList) {
			entityManager.unwrap(Session.class).remove(item);
			entityManager.unwrap(Session.class).flush();
		}

		entityManager.unwrap(Session.class).clear();
		return objList;
	}
}
