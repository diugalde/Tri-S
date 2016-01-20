/* 
	TRI-S - Web App
	Developed by: Diego Ugalde Ávila - Luis E. Ugalde Barrantes. 2016.
	This code is licensed under the GNU GENERAL PUBLIC LICENSE (GPL) V3. See LICENSE file for details.
*/


var ready = function() {
	(function() {
		
		var bodyEl = document.body,
				docElem = window.document.documentElement,
				support = { transitions: Modernizr.csstransitions },
				// transition end event name
				transEndEventNames = { 'WebkitTransition': 'webkitTransitionEnd', 'MozTransition': 'transitionend', 'OTransition': 'oTransitionEnd', 'msTransition': 'MSTransitionEnd', 'transition': 'transitionend' },
				transEndEventName = transEndEventNames[ Modernizr.prefixed( 'transition' ) ],
				onEndTransition = function( el, callback ) {
					var onEndCallbackFn = function( ev ) {
						if( support.transitions ) {
							if( ev.target != this ) return;
							this.removeEventListener( transEndEventName, onEndCallbackFn );
						}
						if( callback && typeof callback === 'function' ) { callback.call(this); }
					};
					if( support.transitions ) {
						el.addEventListener( transEndEventName, onEndCallbackFn );
					}
					else {
						onEndCallbackFn();
					}
				},
				gridEl = document.getElementById('theGrid'),
				sidebarEl = document.getElementById('theSidebar'),
				gridItemsContainer = gridEl.querySelector('section.grid'),
				contentItemsContainer = gridEl.querySelector('section.content'),
				gridItems = gridItemsContainer.querySelectorAll('.grid__item'),
				contentItems = contentItemsContainer.querySelectorAll('.content__item'),
				closeCtrl = contentItemsContainer.querySelector('.close-button'),
				current = -1,
				lockScroll = false, xscroll, yscroll,
				isAnimating = false;

		function getViewport( axis ) {
			var client, inner;
			if( axis === 'x' ) {
				client = docElem['clientWidth'];
				inner = window['innerWidth'];
			}
			else if( axis === 'y' ) {
				client = docElem['clientHeight'];
				inner = window['innerHeight'];
			}

			return client < inner ? inner : client;
		}
		function scrollX() { return window.pageXOffset || docElem.scrollLeft; }
		function scrollY() { return window.pageYOffset || docElem.scrollTop; }

		function init() {
			initEvents();
		}

		function initEvents() {
			[].slice.call(gridItems).forEach(function(item, pos) {
				// grid item click event
				item.addEventListener('click', function(ev) {
					ev.preventDefault();
					if(isAnimating || current === pos) {
						return false;
					}
					isAnimating = true;
					// index of current item
					current = pos;
					// simulate loading time..
					classie.add(item, 'grid__item--loading');
					setTimeout(function() {
						classie.add(item, 'grid__item--animate');
						// reveal/load content after the last element animates out (todo: wait for the last transition to finish)
						setTimeout(function() { loadContent(item); }, 50);
					}, 100);
				});
			});

			closeCtrl.addEventListener('click', function() {
				// hide content
				hideContent();
			});

			// keyboard esc - hide content
			document.addEventListener('keydown', function(ev) {
				if(!isAnimating && current !== -1) {
					var keyCode = ev.keyCode || ev.which;
					if( keyCode === 27 ) {
						ev.preventDefault();
						if ("activeElement" in document)
							document.activeElement.blur();
						hideContent();
					}
				}
			} );
		}

		function loadContent(item) {
			
			itemID = item.getAttribute("linked-div");
			$selectedItem = $("#"+itemID);
			selectedItem = $selectedItem[0];

			rId = "recaptcha-" + $selectedItem.attr("form-name");
			tempWidgetId = $("#"+rId).attr("widget-id");
			if(typeof tempWidgetId === "undefined") {
				tempId = grecaptcha.render(rId, {'sitekey' : '6LdD2xUTAAAAALM_uw03yEXFXAk5PI-tjIYb7nPA'});
				$("#"+rId).attr("widget-id", tempId);
			}else{
				grecaptcha.reset(tempWidgetId);
			}
			//Remove tabs.
			$(".tabs").fadeOut(500);

			// add expanding element/placeholder 
			var dummy = document.createElement('div');
			dummy.className = 'placeholder';

			// set the width/heigth and position
			dummy.style.WebkitTransform = 'translate3d(' + (item.offsetLeft - 5) + 'px, ' + 0 + 'px, 0px) scale3d(' + item.offsetWidth/gridItemsContainer.offsetWidth + ',' + item.offsetHeight/getViewport('y') + ',1)';
			dummy.style.transform = 'translate3d(' + (item.offsetLeft - 5) + 'px, ' + 0 + 'px, 0px) scale3d(' + item.offsetWidth/gridItemsContainer.offsetWidth + ',' + item.offsetHeight/getViewport('y') + ',1)';

			// add transition class 
			classie.add(dummy, 'placeholder--trans-in');

			// insert it after all the grid items
			gridItemsContainer.appendChild(dummy);

			// body overlay
			classie.add(bodyEl, 'view-single');

			setTimeout(function() {
				// expands the placeholder
				dummy.style.WebkitTransform = 'translate3d(0px, ' + 0 + 'px, 0px)';
				dummy.style.transform = 'translate3d(0px, ' + 0 + 'px, 0px)';
				// disallow scroll
				//window.addEventListener('scroll', noscroll);
			}, 25);

			onEndTransition(dummy, function() {
				// add transition class 
				classie.remove(dummy, 'placeholder--trans-in');
				classie.add(dummy, 'placeholder--trans-out');
				// position the content container
				contentItemsContainer.style.top = 0 + 'px';
				// show the main content container
				classie.add(contentItemsContainer, 'content--show');
				// show content item:
				classie.add(selectedItem, 'content__item--show');
				// show close control
				classie.add(closeCtrl, 'close-button--show');
				// sets overflow hidden to the body and allows the switch to the content scroll
				//classie.addClass(bodyEl, 'noscroll');

				isAnimating = false;
				var newHeight = parseFloat($selectedItem.prop('scrollHeight'));
				$selectedItem.css("z-index", 9999);
				$("#theGrid").animate({height: newHeight}, "slow");
				$("html, body").animate({ scrollTop: 0 }, "slow");
				$(".portfolio-items").hide();

			});
		}

		function hideContent() {
			
			rId = "recaptcha-" + $selectedItem.attr("form-name");
			$("#"+rId).html("");
			//widgetId = $("#"+rId).attr("widget-id");
			//grecaptcha.reset(widgetId);
			
			var gridItem = gridItems[current], contentItem = selectedItem;

			classie.remove(contentItem, 'content__item--show');
			classie.remove(contentItemsContainer, 'content--show');
			classie.remove(closeCtrl, 'close-button--show');
			classie.remove(bodyEl, 'view-single');

			setTimeout(function() {
				var dummy = gridItemsContainer.querySelector('.placeholder');

				dummy.style.WebkitTransform = 'translate3d(' + 0 + 'px, ' + 0 + 'px, 0px) scale3d(' + gridItem.offsetWidth/gridItemsContainer.offsetWidth + ',' + gridItem.offsetHeight/getViewport('y') + ',1)';
				dummy.style.transform = 'translate3d(' + gridItem.offsetLeft + 'px, ' + 0 + 'px, 0px) scale3d(' + gridItem.offsetWidth/gridItemsContainer.offsetWidth + ',' + gridItem.offsetHeight/getViewport('y') + ',1)';

				onEndTransition(dummy, function() {

					gridItemsContainer.removeChild(dummy);
					classie.remove(gridItem, 'grid__item--loading');
					classie.remove(gridItem, 'grid__item--animate');
					lockScroll = false;

					$(".tabs").fadeIn(500);
					$(".portfolio-items").show();
					$("#theGrid").css('height','auto');
					$selectedItem.css("z-index", -1);

				});

				// reset current
				current = -1;
			}, 5);
		}

		init();

	})();
}

$(document).on('page:load', ready);
$(document).ready(ready);