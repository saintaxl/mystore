/**
 * 
 */
package com.mycloud.store.controller.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycloud.entity.Category;
import com.mycloud.entity.Customer;
import com.mycloud.entity.Quantity;
import com.mycloud.repository.CategoryRepository;
import com.mycloud.store.controller.BaseController;

/**
 * @author Shawn
 *
 */
@Controller
public class InventoryController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping("/searchInventory.htm")
	public String searchInventory(Model model) {
		Customer customer = getCustomer();
		String customerNo = customer.getCustomerNo();
		model.addAttribute("customerNo", customerNo);
		
		List<Category> categorys = categoryRepository.findAll();
		model.addAttribute("categorys", categorys);
		return "/context/inventory/inventoryList";
	}

	

}
