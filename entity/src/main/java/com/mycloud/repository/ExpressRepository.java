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

import com.mycloud.entity.Express;

@Repository
public interface ExpressRepository extends BaseRepository<Express, Serializable> {



	
}
