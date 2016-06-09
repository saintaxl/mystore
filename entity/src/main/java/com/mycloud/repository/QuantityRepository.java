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

import com.mycloud.entity.Quantity;

@Repository
public interface QuantityRepository extends BaseRepository<Quantity, Serializable> {



	
}
