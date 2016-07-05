/**
 * 
 */
package com.mycloud.repository;

/**
 * @author Shawn
 *
 */
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mycloud.entity.Customer;
import com.mycloud.entity.Inventory;

@Repository
public interface InventoryRepository extends BaseRepository<Inventory, Serializable> {

	
	@Query(value="select sum(i.weight) , sum(i.volume) , sum(i.number) , i.customer.id from Inventory i where i.number > 0 group by i.customer.id")
	public List<Object[]> findSumVolumeGroupByCustomerId();

	public List<Inventory> findByCustomerAndNumberGreaterThan(Customer customer, Integer number);


	
}
