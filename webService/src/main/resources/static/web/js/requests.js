var ready = function() {

	var baseURL = "http://localhost:8282";

	$("body").on("submit", ".cd-form", function(event) {
		event.preventDefault();
		var form = $(this);
		$.ajax({
			method: 'POST',
			url: baseURL + "/request",
			data: form.serializeJSON(),
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
			$("html, body").animate({ scrollTop: 0 }, "slow");
		});
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