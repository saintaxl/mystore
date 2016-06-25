package com.mycloud.store.controller.rest.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycloud.entity.Customer;
import com.mycloud.entity.DeliveryDetails;
import com.mycloud.store.controller.BaseController;
import com.mycloud.store.controller.form.DeliveryListForm;
import com.mycloud.store.controller.rest.model.CategoryView;
import com.mycloud.store.controller.rest.model.CustomerView;
import com.mycloud.store.controller.rest.model.DeliveryDetailView;
import com.mycloud.store.controller.rest.model.DeliveryView;
import com.mycloud.store.controller.rest.model.JQueryDatatablesPage;
import com.mycloud.store.controller.rest.model.LogisticsView;
import com.mycloud.store.controller.rest.model.QuantityView;
import com.mycloud.store.controller.rest.model.SimplifiedReq;
import com.mycloud.store.controller.rest.model.SimplifiedResp;
import com.mycloud.store.service.DeliveryService;
import com.mycloud.store.sso.UserInfo;
import com.mycloud.store.utils.Pinyin;

@RestController
public class DeliveryRestController extends BaseController {

	@Autowired
	private DeliveryService deliveryService;

	@RequestMapping(value = "/showBarcodeAcronym", method = { RequestMethod.POST }, produces = { "application/json" })
	public SimplifiedResp showBarcodeAcronym(@RequestBody SimplifiedReq simplified) {
		UserInfo userInfo = getUserInfo();
		String customerAcronym = userInfo.getCustomerAcronym();
		if (StringUtils.isEmpty(customerAcronym)) {
			customerAcronym = Pinyin.String2Alpha(userInfo.getCustomerName());
		}
		
		Customer customer = getCustomer();
		
		String category = simplified.getSimplified();
		String barCode = getBarCode(customer.getCustomerNo(), customerAcronym, category);

		SimplifiedResp resp = new SimplifiedResp();
		resp.setAcronym(barCode);
		return resp;
	}

	@RequestMapping(value = "/showDeliveryList", method = { RequestMethod.POST })
	public JQueryDatatablesPage<DeliveryDetailView> showDeliveryList(@ModelAttribute DeliveryListForm deliveryListForm,
	        @PageableDefault(value = 10, sort = { "id" }, direction = Direction.DESC) Pageable pageable, @RequestParam Integer echo, Model model) {

		Customer customer = getCustomer();
		Page<DeliveryDetails> page = deliveryService.searchDetails(deliveryListForm, customer, pageable);

		JQueryDatatablesPage<DeliveryDetailView> pageview = transform2View(page, echo);
		return pageview;
	}

	private JQueryDatatablesPage<DeliveryDetailView> transform2View(Page<DeliveryDetails> page, Integer echo) {
		List<DeliveryDetails> content = page.getContent();
		
		
		List<DeliveryDetailView> deliveryDetailViewList = new ArrayList<DeliveryDetailView>();
		for (DeliveryDetails deliveryDetail : content) {
			DeliveryDetailView deliveryDetailView = new DeliveryDetailView();
			deliveryDetailView.setBarCode(deliveryDetail.getBarCode());
			
			CategoryView category = new CategoryView();
			category.setId(deliveryDetail.getCategory().getId());
			category.setName(deliveryDetail.getCategory().getName());
			deliveryDetailView.setCategory(category);
			
			deliveryDetailView.setColor(deliveryDetail.getColor());
			deliveryDetailView.setId(deliveryDetail.getId());
			deliveryDetailView.setNote(deliveryDetail.getNote());
			deliveryDetailView.setNumber(deliveryDetail.getNumber());
			deliveryDetailView.setProductName(deliveryDetail.getProductName());
			
			QuantityView  quantity = new QuantityView();
			quantity.setName(deliveryDetail.getQuantity().getName());
			quantity.setId(deliveryDetail.getQuantity().getId());
			deliveryDetailView.setQuantity(quantity);
			
			deliveryDetailView.setVolume(deliveryDetail.getVolume());
			deliveryDetailView.setWeight(deliveryDetail.getWeight());
			
			
			DeliveryView deliveryView =  new DeliveryView();
			deliveryView.setDeliveryNo(deliveryDetail.getDelivery().getDeliveryNo());
			
			LogisticsView logistics = new LogisticsView();
			logistics.setId(deliveryDetail.getDelivery().getLogistics().getId());
			logistics.setCompanyName(deliveryDetail.getDelivery().getLogistics().getCompanyName());
			logistics.setLogisticsNo(deliveryDetail.getDelivery().getLogistics().getLogisticsNo());
			if (deliveryDetail.getDelivery().getLogistics() != null && deliveryDetail.getDelivery().getLogistics().getArrivalDate() != null) {
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				String formatdate = dateformat.format(deliveryDetail.getDelivery().getLogistics().getArrivalDate());
				logistics.setArrivalDate(formatdate);
			}
			deliveryView.setLogistics(logistics);
			deliveryView.setStatus(deliveryDetail.getDelivery().getStatus());
			
			CustomerView customer = new CustomerView();
			customer.setAcronym(deliveryDetail.getCustomer().getAcronym());
			customer.setCustomerName(deliveryDetail.getCustomer().getCustomerName());
			customer.setCustomerNo(deliveryDetail.getCustomer().getCustomerNo());
			customer.setId(deliveryDetail.getCustomer().getId());
			deliveryView.setCustomer(customer);
			
			deliveryDetailView.setDelivery(deliveryView);
			
			deliveryDetailViewList.add(deliveryDetailView);
        }

		JQueryDatatablesPage<DeliveryDetailView> page_ = new JQueryDatatablesPage<DeliveryDetailView>();
		page_.setAaData(deliveryDetailViewList);
		page_.setiTotalDisplayRecords(page.getTotalElements());
		page_.setiTotalRecords(page.getTotalElements());
		page_.setsEcho(echo);
		return page_;
	}

}
