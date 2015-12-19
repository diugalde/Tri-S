var ready = function() {

	var baseURL = "http://jsonplaceholder.typicode.com/posts";

	$("body").on("submit", ".cd-form", function(event) {
		event.preventDefault();
		var form = $(this);
		$.ajax({
			method: 'POST',
			url: baseURL,
			data: form.serialize()
		}).success(function(data) {
			console.log(data);
			generateNotification("success", "Solicitud enviada exitosamente. Se ha enviado un correo de recibido.");
		}).fail(function(data) {
			generateNotification("error", "Error en los campos del formulario.");
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