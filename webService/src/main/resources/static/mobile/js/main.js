var ready = function() {

	var $tabButtons= $(".ui-tabs-nav").children();

    $(document).on("pagecontainerbeforechange", function (e, data) {
        if (typeof data.toPage == "string" && data.options.direction == "back") {
            $(".ui-tabs-active").children().first().addClass("ui-btn-active");
            $('input').removeClass('wrong-field');
        }
    });

    FastClick.attach(document.body);

}

$(document).on('page:load', ready);
$(document).ready(ready);


