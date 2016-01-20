/* 
	TRI-S - Mobile App
	Developed by: Diego Ugalde Ávila - Luis E. Ugalde Barrantes. 2016.
	This code is licensed under the GNU GENERAL PUBLIC LICENSE (GPL) V3. See LICENSE file for details.
*/


var ready = function() {

	var baseURL = "http://ws.tec.siua.ac.cr";

	//When a form is submitted, it creates a JSON with the field information and sends it to the Java Web Service.
	$("body").on("submit", ".cd-form", function(event) {
		$clickedForm = $(this);
		event.preventDefault();
		$("#loading-div").addClass("show-loading");
		var form = $(this);
		var formData = form.serializeJSON();
		formData.Queue = $(this).attr("request");
		formData.RequestorName = $(this).find(".requestor-name").val();
		console.log(formData);
		$.ajax({
			method: 'POST',
			url: baseURL + "/mobileRequest",
			data: JSON.stringify(formData),
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			}
		}).success(function(data) {
			console.log(data);
			generateNotification(data.type, data.msg);
			if(data.type === "success") {
				$clickedForm.trigger("reset");
				$('input').removeClass('wrong-field');
			}
			else highlightWrongFields($clickedForm, data.wrongFields.split(","));
		}).fail(function(data) {
			generateNotification("error", "Hubo un error al enviar el formulario.");
		}).always(function() {
			$("#loading-div").removeClass("show-loading");
			$("html, body").animate({ scrollTop: 0 }, "slow");
		});

	});


	//For every item inside wrongFields list, its input field is highlighted in red.
	function highlightWrongFields(form, wrongFields) {
		for(var i = 0; i < wrongFields.length; i++) {
			form.find("input[name='" + wrongFields[i] +"']").addClass("wrong-field");
		}
	}


	// Shows notification on the top right of the screen.
	function generateNotification(notificationType, message) {
		message = JSON.stringify(message);
		notyObject = noty({
			text        : message,
			type        : notificationType,
			theme       : 'relax',
			dismissQueue: true,
			layout      : 'topRight',
			timeout		: false,
			maxVisible  : 1,
			killer			: true,
			animation   : {
				open  : 'animated flipInX',
				close : 'animated flipOutX'
			}
		});
		notyObject.setTimeout(6000);
	}

}

$(document).on('page:load', ready);
$(document).ready(ready);
