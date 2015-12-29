var ready = function() {

	var baseURL = "http://localhost:8282";

	$("body").on("submit", ".cd-form", function(event) {
		event.preventDefault();
		var recaptchaId = $(this).find(".recaptcha-container").attr("widget-id");
		if(typeof recaptchaId === "undefined") recaptchaResponse = null;
		else recaptchaResponse = grecaptcha.getResponse(recaptchaId);
		if(!recaptchaResponse || recaptchaResponse.length === 0) {
			generateNotification("error", "Debe solucionar el captcha para enviar el formulario.");
		}else {
			var form = $(this);
			var formData = JSON.parse(form.serializeJSON());
			formData.recaptchaResponse = grecaptcha.getResponse(recaptchaId);
			$.ajax({
				method: 'POST',
				url: baseURL + "/request",
				data: JSON.stringify(formData),
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				}
			}).success(function(data) {
				console.log(data);
				generateNotification(data.type, data.msg);
			}).fail(function(data) {
				generateNotification("error", "Hubo un error al enviar el formulario.");
			}).always(function() {
				grecaptcha.reset(recaptchaId);
				$("html, body").animate({ scrollTop: 0 }, "slow");
			});
		}
	});


	function generateNotification(notificationType, message) {
		//message = JSON.stringify(message);
		noty({
			text        : message,
			type        : notificationType,
			theme       : 'relax',
			dismissQueue: true,
			layout      : 'topRight',
			timeout			: false,
			maxVisible  : 1,
			killer			: true,
			animation   : {
				open  : 'animated flipInX',
				close : 'animated flipOutX'
			}
		});
	}
}

$(document).on('page:load', ready);
$(document).ready(ready);
