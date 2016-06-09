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

import com.mycloud.entity.Category;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Serializable> {



	
}
