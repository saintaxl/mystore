/**
 * 
 */
package com.mycloud.repository;

/**
 * @author Shawn
 *
 */
import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.mycloud.entity.Configuration;

@Repository
public interface ConfigurationRepository extends BaseRepository<Configuration, Serializable> {

	public Configuration findByName(String name);
	
	public Configuration findByNameAndOperative(String name,Boolean operative);
	

	
}
