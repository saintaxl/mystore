var sidebar = function () {
	
	$('#dashboard').click(function() {
		$.get("dashboard.htm", function(data) {
			$("#sub_content").html(data);
		});
	});

	$('#createDelivery').click(function() {
		$.get("createDelivery.htm", function(data) {
			setPageheader('入库单管理','创建入库单','创建入库单','表单填写');
			$("#sub_content").html(data);
		});
	});
	
	$('#searchDelivery').click(function() {
		$.get("searchDelivery.htm", function(data) {
			setPageheader('入库单管理','查询入库单','查询入库单','入库单查询');
			$("#sub_content").html(data);
		});
	});
	
	$('#createExpress').click(function() {
		$.get("createExpress.htm", function(data) {
			setPageheader('物流管理','创建发货单','创建发货单','表单填写');
			$("#sub_content").html(data);
		});
	});
	
	$('#searchExpress').click(function() {
		$.get("searchExpress.htm", function(data) {
			setPageheader('物流管理','查询发货单','查询发货单','发货单查询');
			$("#sub_content").html(data);
		});
	});
	
	$('#searchInventory').click(function() {
		$.get("searchInventory.htm", function(data) {
			setPageheader('库存管理','库存盘点','库存盘点','库存查询');
			$("#sub_content").html(data);
		});
	});
	
	
	$('#internal500').click(function() {
		$.get("internal_500.htm", function(data) {
			$("#sub_content").html(data);
		});
	});
	
	
	
}();

var setPageheader = function (sidebarName,submenu,title,description) {
	$("#sidebarName").html(sidebarName);
	$("#submenu").html(submenu);
	$("#title").html(title);
	$("#description").html(description);
};