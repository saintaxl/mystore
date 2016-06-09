var sidebar = function () {
	
	$('#dashboard').click(function() {
		$.get("dashboard.htm", function(data) {
			$("#sub_content").html(data);
		});
	});

	$('#createDelivery').click(function() {
		$.get("createDelivery.htm", function(data) {
			setPageheader('发货单管理','创建发货单','创建发货单','表单填写');
			$("#sub_content").html(data);
		});
	});
	
	$('#secondmenu').click(function() {
		$.get("try.htm", function(data) {
			$("#sub_content").html(data);
		});
	});
	
	$('#internal404').click(function() {
		$.get("internal_404.htm", function(data) {
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