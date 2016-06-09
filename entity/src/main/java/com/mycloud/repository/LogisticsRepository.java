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

import com.mycloud.entity.Logistics;

@Repository
public interface LogisticsRepository extends BaseRepository<Logistics, Serializable> {



	
}
