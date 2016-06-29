package com.mycloud.store.controller.rest.business;

import java.text.SimpleDateFormat;
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
import com.mycloud.entity.Express;
import com.mycloud.entity.ExpressDetails;
import com.mycloud.entity.Inventory;
import com.mycloud.entity.Logistics;
import com.mycloud.entity.Quantity;
import com.mycloud.store.controller.BaseController;
import com.mycloud.store.controller.form.ExpressListForm;
import com.mycloud.store.controller.rest.model.CategoryView;
import com.mycloud.store.controller.rest.model.CustomerView;
import com.mycloud.store.controller.rest.model.ExpressDetailView;
import com.mycloud.store.controller.rest.model.ExpressView;
import com.mycloud.store.controller.rest.model.InventoryView;
import com.mycloud.store.controller.rest.model.JQueryDatatablesPage;
import com.mycloud.store.controller.rest.model.LogisticsView;
import com.mycloud.store.controller.rest.model.QuantityView;
import com.mycloud.store.service.ExpressService;

@RestController
public class ExpressRestController extends BaseController {
	
	
	@Autowired
	private ExpressService expressService;
	
	@RequestMapping(value = "/showExpressList", method = { RequestMethod.POST })
	public JQueryDatatablesPage<ExpressDetailView> showDeliveryList(@ModelAttribute ExpressListForm expressListForm,
	        @PageableDefault(value = 10, sort = { "id" }, direction = Direction.DESC) Pageable pageable, @RequestParam Integer echo, Model model) {

		Customer customer = getCustomer();
		Page<ExpressDetails> page = expressService.searchDetails(expressListForm, customer, pageable);

		JQueryDatatablesPage<ExpressDetailView> pageview = transform2View(page, echo);
		return pageview;
	}

	private JQueryDatatablesPage<ExpressDetailView> transform2View(Page<ExpressDetails> page, Integer echo) {
		List<ExpressDetails> content = page.getContent();
		
		List<ExpressDetailView> expressDetailViewList = new ArrayList<ExpressDetailView>();
		for (ExpressDetails detail : content) {
			ExpressDetailView detailView = new ExpressDetailView();
			detailView.setId(detail.getId());
			detailView.setNumber(detail.getNumber());
			buildCustomerView(detail,detailView);
			buildExpressView(detail, detailView);
			buildInventoryView(detail, detailView);
			expressDetailViewList.add(detailView);
        }

		JQueryDatatablesPage<ExpressDetailView> page_ = new JQueryDatatablesPage<ExpressDetailView>();
		page_.setAaData(expressDetailViewList);
		page_.setiTotalDisplayRecords(page.getTotalElements());
		page_.setiTotalRecords(page.getTotalElements());
		page_.setsEcho(echo);
		return page_;
	}

	private void buildInventoryView(ExpressDetails detail, ExpressDetailView detailView) {
	    InventoryView inventoryView = new InventoryView();
	    Inventory inventory = detail.getInventory();
	    if(inventory!=null){
	    	inventoryView.setBarCode(inventory.getBarCode());
	    	if(inventory.getCategory()!=null){
	    		buildCategoryView(inventory, inventoryView);
	    	}
	    	inventoryView.setColor(inventory.getColor());
	    	inventoryView.setId(inventory.getId());
	    	inventoryView.setNumber(inventory.getNumber());
	    	inventoryView.setProductName(inventory.getProductName());
	    	if(inventory.getQuantity()!=null){
	    		buildQuantityView(inventory, inventoryView);
	    	}
	    	inventoryView.setShelvesNo(inventory.getShelvesNo());
	    	inventoryView.setVolume(inventory.getVolume());
	    	inventoryView.setWeight(inventory.getWeight());
	    }
	    
	    detailView.setInventory(inventoryView);
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

	private void buildExpressView(ExpressDetails detail, ExpressDetailView detailView) {
		ExpressView expressView = new ExpressView();
		Express express = detail.getExpress();
		if(express!=null){
			if(detailView.getCustomer()!=null){
				expressView.setCustomer(detailView.getCustomer());
			}
			expressView.setExpressNo(express.getExpressNo());
			expressView.setId(express.getId());
			if(detail.getExpress().getLogistics()!=null){
				buildLogistics(detail, expressView);
			}
			expressView.setStatus(express.getStatus());
		}
	    detailView.setExpress(expressView);
    }

	private void buildLogistics(ExpressDetails detail, ExpressView express) {
		LogisticsView logisticsView = new LogisticsView();
		if(detail.getExpress() != null && detail.getExpress().getLogistics() != null){
			Logistics logistics = detail.getExpress().getLogistics();
			if (logistics.getArrivalDate() != null) {
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				String formatdate = dateformat.format(logistics.getArrivalDate());
				logisticsView.setArrivalDate(formatdate);
			}
			logisticsView.setName(logistics.getName());
			logisticsView.setMobile(logistics.getMobile());
			logisticsView.setAddress(logistics.getAddress());
			logisticsView.setProvince(logistics.getProvince());
			logisticsView.setCity(logistics.getCity());
			logisticsView.setDistrict(logistics.getDistrict());
			logisticsView.setMobile(logistics.getMobile());
			logisticsView.setPrice(logistics.getPrice());
			logisticsView.setCompanyName(logistics.getCompanyName());
			logisticsView.setId(logistics.getId());
			logisticsView.setLogisticsNo(logistics.getLogisticsNo());
			logisticsView.setNote(logistics.getNote());
		}
		express.setLogistics(logisticsView);
    }

	private void buildCustomerView(ExpressDetails details, ExpressDetailView detailView) {
		CustomerView customerView = new CustomerView();
		if(details.getCustomer()!=null){
			Customer customer = details.getCustomer();
			customerView.setAcronym(customer.getAcronym());
			customerView.setCustomerName(customer.getCustomerName());
			customerView.setCustomerNo(customer.getCustomerNo());
			customerView.setId(customer.getId());
		}
	    detailView.setCustomer(customerView);
    }
	
	

}
