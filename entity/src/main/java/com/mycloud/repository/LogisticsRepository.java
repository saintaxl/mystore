/**
 * 
 */
package com.mycloud.repository;

/**
 * @author Shawn
 *
 */
import java.io.Serializable;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.mycloud.entity.Logistics;

@Repository
public interface LogisticsRepository extends BaseRepository<Logistics, Serializable> {


	Logistics findByCompanyName(String companyName);
	
	Logistics findByLogisticsNo(String logisticsNo);
	
}
