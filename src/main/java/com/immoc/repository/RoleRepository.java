package com.immoc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.immoc.entity.Role;

/**
 * 角色数据DAO
 * @author Administrator
 *
 */
public interface RoleRepository extends CrudRepository<Role,Long>{

	
	List<Role> findRolesByUserId(Long userId);
}
