package com.mycloud.store.controller.rest.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycloud.entity.Category;
import com.mycloud.entity.Customer;
import com.mycloud.entity.Inventory;
import com.mycloud.entity.Quantity;
import com.mycloud.store.controller.BaseController;
import com.mycloud.store.controller.form.InventoryListForm;
import com.mycloud.store.controller.rest.model.CategoryView;
import com.mycloud.store.controller.rest.model.InventoryView;
import com.mycloud.store.controller.rest.model.JQueryDatatablesPage;
import com.mycloud.store.controller.rest.model.QuantityView;
import com.mycloud.store.service.InventoryService;

@RestController
public class InventoryRestController extends BaseController {
	
	@Autowired
	private InventoryService inventoryService;

	@RequestMapping(value = "/showInventoryList", method = { RequestMethod.POST })
	public JQueryDatatablesPage<InventoryView> showInventoryList(@ModelAttribute InventoryListForm inventoryListForm,
	        @PageableDefault(value = 10, sort = { "id" }, direction = Direction.DESC) Pageable pageable, @RequestParam Integer echo, Model model) {

		Customer customer = getCustomer();
		Page<Inventory> page = inventoryService.searchInventory(inventoryListForm, customer, pageable);

		JQueryDatatablesPage<InventoryView> pageview = transform2View(page, echo);
		return pageview;
	}

	private JQueryDatatablesPage<InventoryView> transform2View(Page<Inventory> page, Integer echo) {
		List<Inventory> content = page.getContent();

		List<InventoryView> InventoryView = new ArrayList<InventoryView>();
		for (Inventory inventory : content) {
			InventoryView inventoryView = new InventoryView();
			inventoryView.setId(inventory.getId());
			inventoryView.setBarCode(inventory.getBarCode());
			inventoryView.setShelvesNo(inventory.getShelvesNo());
			inventoryView.setProductName(inventory.getProductName());
			inventoryView.setColor(inventory.getColor());
			inventoryView.setNumber(inventory.getNumber());
			buildQuantityView(inventory, inventoryView);
			inventoryView.setVolume(inventory.getVolume());
			inventoryView.setWeight(inventory.getWeight());
			buildCategoryView(inventory, inventoryView);
			InventoryView.add(inventoryView);
		}

		JQueryDatatablesPage<InventoryView> page_ = new JQueryDatatablesPage<InventoryView>();
		page_.setAaData(InventoryView);
		page_.setiTotalDisplayRecords(page.getTotalElements());
		page_.setiTotalRecords(page.getTotalElements());
		page_.setsEcho(echo);
		return page_;
	}
	
	private void buildQuantityView(Inventory inventory, InventoryView inventoryView) {
	    QuantityView  quantityView = new QuantityView();
	    if(inventory.getQuantity()!=null){
	    	Quantity quantity = inventory.getQuantity();
	    	quantityView.setName(quantity.getName());
	    	quantityView.setId(quantity.getId());
	    }
	    inventoryView.setQuantity(quantityView);
    }

	private void buildCategoryView(Inventory inventory, InventoryView inventoryView) {
	    CategoryView categoryView = new CategoryView();
	    if(inventory.getCategory()!=null){
	    	Category category =inventory.getCategory();
	    	categoryView.setId(category.getId());
	    	categoryView.setName(category.getName());
	    }
	    inventoryView.setCategory(categoryView);
    }

}
