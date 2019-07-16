$(document).ready(function() {
	/* Core JS Functions */
	
	/* Collapsible Panels */
	$(".aiyun-panel.aiyun-collapsible .aiyun-panel-header")
		.append("<div class=\"aiyun-collapse-button aiyun-inset\"><span></span></div>")
			.find(".aiyun-collapse-button span")
				.live("click", function(event) {
					$(this).toggleClass("aiyun-collapsed")
						.parents(".aiyun-panel")
							.find(".aiyun-panel-body")
								.slideToggle("fast");
				});

	/* Side dropdown menu */
	$("div#aiyun-navigation ul li a, div#aiyun-navigation ul li span")
	.bind('click', function(event) {
		if($(this).next('ul').size() !== 0) {
			$(this).next('ul').slideToggle('fast', function() {
				$(this).toggleClass('closed');
			});
			event.preventDefault();
		}
	});
	
	/* Message & Notifications Dropdown */
	$("div#aiyun-user-tools .aiyun-dropdown-menu a").click(function(event) {
		$(".aiyun-dropdown-menu.toggled").not($(this).parent()).removeClass("toggled");
		$(this).parent().toggleClass("toggled");
		event.preventDefault();
	});
	
	$('html').click(function(event) {
		if($(event.target).parents('.aiyun-dropdown-menu').size() == 0) {
			$(".aiyun-dropdown-menu").removeClass("toggled");
		}
	});
	
	/* Side Menu Notification Class */
	$(".aiyun-nav-tooltip").addClass("aiyun-inset");
	
	/* Table Row CSS Class */
	$("table.aiyun-table tbody tr:even").addClass("even");
	$("table.aiyun-table tbody tr:odd").addClass("odd");
	
	/* File Input Styling */
	
	if($.fn.filestyle) {
		$("input[type='file']").filestyle({
			imagewidth: 78, 
			imageHeight: 28
		});
		$("input.file").attr("readonly", true);
	}
	
	/* Tooltips */
	
	if($.fn.tipsy) {
		var gravity = ['n', 'ne', 'e', 'se', 's', 'sw', 'w', 'nw'];
		for(var i in gravity)
			$(".aiyun-tooltip-"+gravity[i]).tipsy({gravity: gravity[i]});
			
		$('input[title], select[title], textarea[title]').tipsy({trigger: 'focus', gravity: 'w'});
	}
	
	/* Dual List Box */
	
	if($.configureBoxes) {
		$.configureBoxes();
	}
	
	if($.fn.placeholder) {
		$('[placeholder]').placeholder();
	}
});
