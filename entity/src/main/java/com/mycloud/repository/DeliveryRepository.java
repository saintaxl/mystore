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

import com.mycloud.entity.Delivery;

@Repository
public interface DeliveryRepository extends BaseRepository<Delivery, Serializable> {



	
}
