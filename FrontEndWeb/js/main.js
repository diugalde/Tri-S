/* 
	TRI-S - Web App
	Developed by: Diego Ugalde Ávila - Luis E. Ugalde Barrantes. 2016.
	This code is licensed under the GNU GENERAL PUBLIC LICENSE (GPL) V3. See LICENSE file for details.
*/


jQuery(function($) {

	var elementSize = 250;

	function changePortfolioMargins() {
		$portfolio = $('.portfolio-items');
		var currPortfolioWidth = parseFloat($portfolio.css('width'));
		var currElements = Math.floor(currPortfolioWidth/elementSize);
		if(currElements <= 0) newPadding = 0;
		else newPadding = Math.floor((currPortfolioWidth-(currElements*elementSize))/2.0);
		$portfolio.css('margin-left', (newPadding+20) + 'px');
		$portfolio.css('margin-right', newPadding + 'px');
	}

	function loadPortfolio() {
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
	}

	$(window).load(function(){
		loadPortfolio();
		changePortfolioMargins();
	});

	$(window).smartresize(function(){
		$portfolio = $('.portfolio-items');
		$portfolio.isotope({
			masonry: { columnWidth: elementSize }
		});

		changePortfolioMargins();
		
		var $activeForm = $(".content__item--show");
		if($activeForm.length) {
			var newHeight = parseFloat($activeForm.height())+25;
			$("#theGrid").animate({height: newHeight}, "fast");
		}
	});	

	$("body").on("click", "#go-back-form", function() {
		$('input').removeClass('wrong-field');
	});

});