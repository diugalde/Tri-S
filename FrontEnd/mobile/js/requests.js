var ready = function() {

	var baseURL = "http://192.168.1.108:3000/posts";

	$("body").on("submit", ".cd-form", function(event) {
		event.preventDefault();
		var form = $(this);
		$.ajax({
			method: 'POST',
			url: baseURL,
			data: form.serialize()
		}).success(function(data) {
			console.log(data);
			generateNotification("success", data);
		}).fail(function(data) {
			generateNotification("error", data);
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