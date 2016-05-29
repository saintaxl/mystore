/**
 * 
 */
package com.mycloud.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.mycloud.repository.BaseRepository;

/**
 * @author Shawn
 *
 */
public class BaseRepositoryImpl <T, ID extends Serializable> extends SimpleJpaRepository<T, ID>  implements BaseRepository<T, ID>{
	
	private final EntityManager entityManager;
	 
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }
 

}
