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

import com.mycloud.entity.Inventory;

@Repository
public interface InventoryRepository extends BaseRepository<Inventory, Serializable> {



	
}
