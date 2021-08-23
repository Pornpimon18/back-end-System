package com.thailife.tax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thailife.tax.base.ServiceBase;
import com.thailife.tax.entity.GroupMenu;
import com.thailife.tax.repository.GroupMenuRepository;


@Service
public class GroupMenuService extends ServiceBase {
	
	@Autowired
	private GroupMenuRepository groupMenuRepository;
	
	@Autowired
	public GroupMenuService(GroupMenuRepository groupMenuRepository) {
		this.groupMenuRepository = groupMenuRepository;
	}
	
	public List<GroupMenu> searchGroupMenuAll() {
		return groupMenuRepository.searchGroupMenuAll();
	}

}
