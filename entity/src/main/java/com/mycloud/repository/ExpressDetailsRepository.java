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

import com.mycloud.entity.ExpressDetails;

@Repository
public interface ExpressDetailsRepository extends BaseRepository<ExpressDetails, Serializable> {



	
}
