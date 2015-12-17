/**
 * main.js
 * http://www.codrops.com
 *
 * Licensed under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 * 
 * Copyright 2015, Codrops
 * http://www.codrops.com
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

		/**
	 * gets the viewport width and height
	 * based on http://responsejs.com/labs/dimensions/
	 */
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
				classie.add(contentItems[0], 'content__item--show');
				// show close control
				classie.add(closeCtrl, 'close-button--show');
				// sets overflow hidden to the body and allows the switch to the content scroll
				//classie.addClass(bodyEl, 'noscroll');

				isAnimating = false;
				var newHeight = parseFloat($('#content1').prop('scrollHeight')); 
				$("#theGrid").animate({height: newHeight}, "slow");
				$("html, body").animate({ scrollTop: 0 }, "slow");
				$(".portfolio-items").hide();

			});
		}

		function hideContent() {
			var gridItem = gridItems[current], contentItem = contentItems[0];

			classie.remove(contentItem, 'content__item--show');
			classie.remove(contentItemsContainer, 'content--show');
			classie.remove(closeCtrl, 'close-button--show');
			classie.remove(bodyEl, 'view-single');

			setTimeout(function() {
				var dummy = gridItemsContainer.querySelector('.placeholder');

				//classie.removeClass(bodyEl, 'noscroll');

				dummy.style.WebkitTransform = 'translate3d(' + 0 + 'px, ' + 0 + 'px, 0px) scale3d(' + gridItem.offsetWidth/gridItemsContainer.offsetWidth + ',' + gridItem.offsetHeight/getViewport('y') + ',1)';
				dummy.style.transform = 'translate3d(' + gridItem.offsetLeft + 'px, ' + 0 + 'px, 0px) scale3d(' + gridItem.offsetWidth/gridItemsContainer.offsetWidth + ',' + gridItem.offsetHeight/getViewport('y') + ',1)';

				onEndTransition(dummy, function() {
					// reset content scroll..
					//contentItem.parentNode.scrollTop = 0;
					gridItemsContainer.removeChild(dummy);
					classie.remove(gridItem, 'grid__item--loading');
					classie.remove(gridItem, 'grid__item--animate');
					lockScroll = false;

					$(".tabs").fadeIn(500);
					$(".portfolio-items").show();
					$("#theGrid").css('height','auto');

				});

				// reset current
				current = -1;
			}, 25);
		}

		init();

	})();
}

$(document).on('page:load', ready);
$(document).ready(ready);
