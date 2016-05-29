var navlink = function () {
	
	$('#dashboard').click(function() {
		$.get("dashboard.htm", function(data) {
			$("#sub_content").html(data);
		});
	});

	$('#firstmenu').click(function() {
		$.get("test.htm", function(data) {
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