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


	$('#footer').on('click', function (e) {

		var notes = [];

		notes['alert'] = 'Best check yo self, you\'re not looking too good.';
		notes['error'] = 'Change a few things up and try submitting again.';
		notes['success'] = 'You successfully read this important alert message.';
		notes['information'] = 'This alert needs your attention, but it\'s not super important.';
		notes['warning'] = '<strong>Warning!</strong> <br /> Best check yo self, you\'re not looking too good.';
		notes['confirm'] = 'Do you want to continue?';

		e.preventDefault();

		var self = $(this);

		if (self.data('layout') == 'inline') {
			$(self.data('custom')).noty({
				text        : notes[self.data('type')],
				type        : self.data('type'),
				theme       : 'relax',
				dismissQueue: true,
				animation   : {
					open  : 'animated bounceInRight',
					close : 'animated bounceOutRight'
				},
				buttons     : (self.data('type') != 'confirm') ? false : [
					{addClass: 'btn btn-primary', text: 'Ok', onClick: function ($noty) {

						// this = button element
						// $noty = $noty element

						$noty.close();
						$(self.data('custom')).noty({force: true, text: 'You clicked "Ok" button', type: 'success'});
					}
					},
					{addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
						$noty.close();
						$(self.data('custom')).noty({force: true, text: 'You clicked "Cancel" button', type: 'error'});
					}
					}
				]
			});
			return false;
		}


		return false;
	});



}

$(document).on('page:load', ready);
$(document).ready(ready);