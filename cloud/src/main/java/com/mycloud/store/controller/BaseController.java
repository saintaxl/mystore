package com.mycloud.store.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;

import com.mycloud.entity.Category;
import com.mycloud.entity.Customer;
import com.mycloud.entity.Delivery;
import com.mycloud.entity.Express;
import com.mycloud.entity.Inventory;
import com.mycloud.entity.Logistics;
import com.mycloud.entity.Quantity;
import com.mycloud.exception.BusinessException;
import com.mycloud.store.controller.rest.model.CategoryView;
import com.mycloud.store.controller.rest.model.CustomerView;
import com.mycloud.store.controller.rest.model.DeliveryView;
import com.mycloud.store.controller.rest.model.ExpressView;
import com.mycloud.store.controller.rest.model.InventoryView;
import com.mycloud.store.controller.rest.model.LogisticsView;
import com.mycloud.store.controller.rest.model.QuantityView;
import com.mycloud.store.exception.ErrorCode;
import com.mycloud.store.service.CustomUserDetails;
import com.mycloud.store.sso.UserInfo;
import com.mycloud.store.utils.Pinyin;

@Controller
public class BaseController {
	
	private Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired  
	protected  HttpServletRequest request;
	
	@Autowired  
	protected  HttpServletResponse response;
	
	
	protected void setPageheader(String sidebarName,String submenu,String title,String description,Model model){
		model.addAttribute("homepage", "/dashboard.htm");
		model.addAttribute("submenu", submenu);
		model.addAttribute("title", title);
		model.addAttribute("description", description);
	}
	
	protected UserInfo getUserInfo(){
		Object attribute = request.getSession().getAttribute("uservo");
		if(attribute ==null){
			return null;
		}
		return (UserInfo)attribute;
	}
	
	protected String getBarCode(String customerNo,String customerAcronym,String category) {
		String categoryAcronym = Pinyin.String2Alpha(category);
		String md5 = customerNo + customerAcronym + categoryAcronym;
		String md5value = null;
		try {
			String md5DigestAsHex = DigestUtils.md5DigestAsHex(md5.getBytes("UTF-8"));
			md5value = StringUtils.substring(md5DigestAsHex, 0, 6).toUpperCase();
        } catch (UnsupportedEncodingException e) {
	        throw new BusinessException(ErrorCode.MD5_DIGEST_ASHEX_ERROR);
        }
		String returnvalue = customerAcronym + "-" + categoryAcronym + "-" + md5value;
		return returnvalue;
    }
	
	protected Customer getCustomer(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		if(principal!=null){
			CustomUserDetails userDetails = (CustomUserDetails)principal;
			Customer customer = userDetails.getCustomer();
			return customer;
		}
		return null;
	}
	
	protected InventoryView buildInventoryView(Inventory inventory) {
		InventoryView inventoryView = new InventoryView();
		if (inventory != null) {
			inventoryView.setBarCode(inventory.getBarCode());
			if (inventory.getCategory() != null) {
				CategoryView categoryView = buildCategoryView(inventory.getCategory());
				inventoryView.setCategory(categoryView);
			}
			inventoryView.setColor(inventory.getColor());
			inventoryView.setId(inventory.getId());
			inventoryView.setNumber(inventory.getNumber());
			inventoryView.setProductName(inventory.getProductName());
			if (inventory.getQuantity() != null) {
				QuantityView quantityView = buildQuantityView(inventory.getQuantity());
				inventoryView.setQuantity(quantityView);
			}
			inventoryView.setShelvesNo(inventory.getShelvesNo());
			inventoryView.setVolume(inventory.getVolume());
			inventoryView.setWeight(inventory.getWeight());
		}

		return inventoryView;
	}

	protected QuantityView buildQuantityView(Quantity quantity) {
		QuantityView quantityView = new QuantityView();
		if (quantity != null) {
			quantityView.setName(quantity.getName());
			quantityView.setId(quantity.getId());
		}
		return quantityView;
	}

	protected CategoryView buildCategoryView(Category category) {
		CategoryView categoryView = new CategoryView();
		if (category != null) {
			categoryView.setId(category.getId());
			categoryView.setName(category.getName());
		}
		return categoryView;
	}

	protected ExpressView buildExpressView(Express express) {
		ExpressView expressView = new ExpressView();
		if (express != null) {
			if (express.getCustomer() != null) {
				CustomerView customerView = buildCustomerView(express.getCustomer());
				expressView.setCustomer(customerView);
			}
			expressView.setExpressNo(express.getExpressNo());
			expressView.setId(express.getId());
			if (express.getLogistics() != null) {
				LogisticsView logisticsView = buildLogisticsView(express.getLogistics());
				expressView.setLogistics(logisticsView);
			}
			expressView.setStatus(express.getStatus());
		}
		return expressView;
	}

	protected LogisticsView buildLogisticsView(Logistics logistics) {
		LogisticsView logisticsView = new LogisticsView();
		if (logistics != null) {
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

		return logisticsView;
	}

	protected CustomerView buildCustomerView(Customer customer) {
		CustomerView customerView = new CustomerView();
		if (customer != null) {
			customerView.setAcronym(customer.getAcronym());
			customerView.setCustomerName(customer.getCustomerName());
			customerView.setCustomerNo(customer.getCustomerNo());
			customerView.setId(customer.getId());
		}
		return customerView;
	}
	
	protected DeliveryView buildDeliveryView(Delivery delivery) {
	    DeliveryView deliveryView =  new DeliveryView();
	    deliveryView.setId(delivery.getId());
	    if(delivery!=null){
	    	deliveryView.setDeliveryNo(delivery.getDeliveryNo());
	    	deliveryView.setStatus(delivery.getStatus());
	    }
	    if(delivery.getLogistics()!=null){
	    	LogisticsView logisticsView = buildLogisticsView(delivery.getLogistics());
	    	deliveryView.setLogistics(logisticsView);
	    }
	    if(delivery.getCustomer()!=null){
	    	CustomerView customerView = buildCustomerView(delivery.getCustomer());
	    	deliveryView.setCustomer(customerView);
	    }
	    return deliveryView;
    }

	
}
