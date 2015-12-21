var ready = function() {
	function loadFormsContent() {
		$("#form-content-section > div").each(function( index ) {
			var htmlFile = $(this).attr('linked-form');
			$(this).children().first().load("forms/" + htmlFile, function() {
				$(this).find(".bootstrap-timepicker").datetimepicker({
					format: 'HH:ii p',
					autoclose: true,
					// todayHighlight: true,
					showMeridian: true,
					startView: 1,
					maxView: 1
				}).on('changeDate', function (ev) {
					var inputField = $(this);
					( inputField.val() == '' ) ? inputField.prev().removeClass('float') : inputField.prev().addClass('float');
				});
				$(this).find(".bootstrap-datepicker").datetimepicker({format: 'dd-mm-yyyy', autoclose: true, minView: 2}).on('changeDate', function (ev) {
					var inputField = $(this);
					( inputField.val() == '' ) ? inputField.prev().removeClass('float') : inputField.prev().addClass('float');
				});

				$(this).find(".toggle-bootstrap").bootstrapToggle();

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

	$("body").on("click", "label", function() {

		$(this).next().focus();
	});

}

$(document).on('page:load', ready);
$(document).ready(ready);