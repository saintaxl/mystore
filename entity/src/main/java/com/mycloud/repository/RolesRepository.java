package com.mycloud.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.mycloud.entity.Role;

@Repository
public interface RolesRepository extends BaseRepository<Role, Serializable> {
	
	Role findByRole(String lastname); 

	
}
