/* 
	TRI-S - Mobile App
	Developed by: Diego Ugalde Ávila - Luis E. Ugalde Barrantes. 2016.
	This code is licensed under the GNU GENERAL PUBLIC LICENSE (GPL) V3. See LICENSE file for details.
*/


var ready = function() {

	var $tabButtons= $(".ui-tabs-nav").children();


	//Click event in charge of reseting the form to its original view.
    $(document).on("pagecontainerbeforechange", function (e, data) {
        if (typeof data.toPage == "string" && data.options.direction == "back") {
            $(".ui-tabs-active").children().first().addClass("ui-btn-active");
            $('input').removeClass('wrong-field');
        }
    });


    //Initializes fastclick to reduce delays.
    FastClick.attach(document.body);

}

$(document).on('page:load', ready);
$(document).ready(ready);


