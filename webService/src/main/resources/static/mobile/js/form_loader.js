var ready = function() {
    $(".page-load").each(function( index ) {
        var htmlFile = $(this).attr('id');
        $(this).load("forms/" + htmlFile + ".html");
    });
}

$(document).on('page:load', ready);
$(document).ready(ready)