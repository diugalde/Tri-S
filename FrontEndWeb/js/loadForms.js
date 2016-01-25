/* 
	TRI-S - Web App
	Developed by: Diego Ugalde Ávila - Luis E. Ugalde Barrantes. 2016.
	This code is licensed under the GNU GENERAL PUBLIC LICENSE (GPL) V3. See LICENSE file for details.
*/


var ready = function() {


	//Loads every html inside the forms folder. 
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

				$(this).find("#addcourse-btn").click(function(ev) {
					ev.preventDefault();
					courseCode = $("#dynamic-course-code").val();
					courseName = $("#dynamic-course-name").val();
					courseNumber = $("#dynamic-course-number").val();

					if(courseCode.trim() != "" && courseName.trim() != "" && courseNumber.trim() != "") {
						row = " " + courseCode + " - " + courseName + " - " + courseNumber + "\n";
						$("#dynamic-courses").val($("#dynamic-courses").val()+row); 
						$("#dynamic-course-code").val("");
						$("#dynamic-course-name").val("");
						$("#dynamic-course-number").val("");
					}
				});

				$(this).find("#resetcourses-btn").click(function(ev) {
					ev.preventDefault();
					$("#dynamic-courses").val("");
				});
			});
		});	
	}


	//Makes label float over the input box when user starts typing. 
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