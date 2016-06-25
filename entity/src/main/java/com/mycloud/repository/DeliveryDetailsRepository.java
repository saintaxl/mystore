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

import com.mycloud.entity.DeliveryDetails;

@Repository
public interface DeliveryDetailsRepository extends BaseRepository<DeliveryDetails, Serializable> {



	
}
