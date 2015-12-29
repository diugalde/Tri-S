var ready = function() {

	$("body").on("click", ".form-link", function() {
		recaptchaId = $(this).attr("href");
		rId = "recaptcha-" + recaptchaId.substring(1, recaptchaId.length);
		tempWidgetId = $("#"+rId).attr("widget-id");
		if(typeof tempWidgetId === "undefined") {
			tempId = grecaptcha.render(rId, {'sitekey' : '6LdD3RMTAAAAAHOdlx1SDF3XkblVuFh0LYXCLE8d'});
			$("#"+rId).attr("widget-id", tempId);
		}else{
			grecaptcha.reset(tempWidgetId);
		}
	});

}

$(document).on('page:load', ready);
$(document).ready(ready);