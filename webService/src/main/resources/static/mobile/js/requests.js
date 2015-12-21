var ready = function() {

	var baseURL = "http://localhost:8282";


	$("body").on("submit", ".cd-form", function(event) {
		event.preventDefault();
		var form = $(this);
		var route = form.attr("request");
		$.ajax({
			method: 'POST',
			url: baseURL + "/" + route,
			data: form.serializeJSON(),
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			}
		}).success(function(data) {
			console.log(data);
			generateNotification("success", "Solicituuuuud enviada exitosamente. Se ha enviado un correo de recibido.");
		}).fail(function(data) {
			generateNotification("error", "Erroresssssssssss en los campos del formulario.");
		}).always(function() {
			$("html, body").animate({ scrollTop: 0 }, "slow");
		});
	});

	function generateNotification(notificationType, message) {
		message = JSON.stringify(message);
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