jQuery(function($) {
	
	var elementSize = 250;
	
	function changePortfolioMargins() {
		$portfolio = $('.portfolio-items');
		var currPortfolioWidth = parseFloat($portfolio.css('width'));
		var currElements = Math.floor(currPortfolioWidth/elementSize);
		var newPadding = Math.floor((currPortfolioWidth-(currElements*elementSize))/2.0);
		console.log(newPadding);
		$portfolio.css('margin-left', (newPadding+20) + 'px');
		$portfolio.css('margin-right', newPadding + 'px');
	}

	//Isotope
	$(window).load(function(){
		$portfolio = $('.portfolio-items');
		$portfolio.isotope({
			itemSelector : 'li',
			resizable: false,
			masonry: { columnWidth: elementSize }
		});
		$portfolio_selectors = $('.portfolio-filter >li>a');
		$portfolio_li = $('.portfolio-filter >li');
		$portfolio_selectors.on('click', function(){
			$portfolio_li.removeClass('tab-current');
			$(this).parent().addClass('tab-current');
			$portfolio_selectors.removeClass('active');
			$(this).addClass('active');
			var selector = $(this).attr('data-filter');
			$portfolio.isotope({ filter: selector });
			return false;
		});

		changePortfolioMargins();
	});
	
	$(window).smartresize(function(){
		$portfolio = $('.portfolio-items');
		$portfolio.isotope({
			masonry: { columnWidth: elementSize }
		});
		
		changePortfolioMargins();
		
	});	
		
});