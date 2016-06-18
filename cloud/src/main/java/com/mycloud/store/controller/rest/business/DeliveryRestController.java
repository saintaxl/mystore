package com.mycloud.store.controller.rest.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycloud.entity.Delivery;
import com.mycloud.store.controller.BaseController;
import com.mycloud.store.controller.form.DeliveryListForm;
import com.mycloud.store.controller.rest.model.DeliveryView;
import com.mycloud.store.controller.rest.model.JQueryDatatablesPage;
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

		String category = simplified.getSimplified();
		String categoryAcronym = Pinyin.String2Alpha(category);

		String value = customerAcronym + "-" + categoryAcronym + "-" + RandomStringUtils.random(5, false, true);

		SimplifiedResp resp = new SimplifiedResp();
		resp.setAcronym(value);
		return resp;
	}

	@RequestMapping(value = "/showDeliveryList",  method = { RequestMethod.POST })
	public JQueryDatatablesPage<DeliveryView> showDeliveryList(@ModelAttribute DeliveryListForm deliveryListForm,
	        @PageableDefault(value = 15, sort = { "id" }, direction = Direction.DESC) Pageable pageable, Model model) {

		Page<Delivery> page = deliveryService.searchDelivery(deliveryListForm, pageable);

		JQueryDatatablesPage<DeliveryView> pageview = transform2View(page);

		return pageview;
	}

	private JQueryDatatablesPage<DeliveryView> transform2View(Page<Delivery> page) {
		List<Delivery> content = page.getContent();
		
		List<DeliveryView> deliveryView = new ArrayList<DeliveryView>();
		for (Delivery delivery : content) {
			DeliveryView view = new DeliveryView();
			if(delivery.getLogistics()!=null && delivery.getLogistics().getArrivalDate()!=null){
				SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
				String formatdate = dateformat.format(delivery.getLogistics().getArrivalDate());
				view.setArrivalDate(formatdate);
			}
			view.setBarCode(delivery.getBarCode());
			view.setCategoryName(delivery.getCategory().getName());
			view.setColor(delivery.getColor());
			view.setDeliveryNo(delivery.getDeliveryNo());
			view.setId(delivery.getId());
			view.setLogisticsCompanyName(delivery.getLogistics().getCompanyName());
			view.setLogisticsNo(delivery.getLogistics().getLogisticsNo());
			view.setNote(delivery.getNote());
			view.setNumber(delivery.getNumber());
			view.setProductName(delivery.getProductName());
			view.setQuantityName(delivery.getQuantity().getName());
			view.setVolume(delivery.getVolume());
			view.setWeight(delivery.getWeight());
			deliveryView.add(view);
        }
		
		JQueryDatatablesPage page_ = new JQueryDatatablesPage();
		page_.setAaData(deliveryView);
		page_.setiTotalDisplayRecords(page.getSize());
		page_.setiTotalRecords(page.getTotalElements());
		page_.setsEcho("1");
	    return page_;
    }

}
