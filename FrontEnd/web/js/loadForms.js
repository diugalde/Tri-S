var ready = function() {
	function loadFormsContent() {
		$("#form-content-section > div").each(function( index ) {
			var htmlFile = $(this).attr('linked-form');
			$(this).children().first().load("forms/" + htmlFile, function() {
				$(this).find(".bootstrap-datepicker").datepicker().on('changeDate', function (ev) {
					var inputField = $(this);
					( inputField.val() == '' ) ? inputField.prev().removeClass('float') : inputField.prev().addClass('float');
				});
			});
		});	
	}

	function floatLabels() {
		var inputFields = $('article .content__item .floating-labels .cd-label').next();
		$('body').on('change keyup', '.form-field', function(){
			var inputField = $(this);
			( inputField.val() == '' ) ? inputField.prev().removeClass('float') : inputField.prev().addClass('float');
		});

		$('body').on('change', '.bootstrap-datepicker', function(){
			console.log("holito");
			var inputField = $(this);
			( inputField.val() == '' ) ? inputField.prev().removeClass('float') : inputField.prev().addClass('float');
		});
	}

	loadFormsContent();
	floatLabels();
}

$(document).on('page:load', ready);
$(document).ready(ready);