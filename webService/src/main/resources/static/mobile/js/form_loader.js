/* 
	TRI-S - Mobile App
	Developed by: Diego Ugalde Ávila - Luis E. Ugalde Barrantes. 2016.
	This code is licensed under the GNU GENERAL PUBLIC LICENSE (GPL) V3. See LICENSE file for details.
*/

var ready = function() {

	// Loads every form inside forms folder.
    $(".page-load").each(function( index ) {
        var htmlFile = $(this).attr('id');
        $(this).load("forms/" + htmlFile + ".html");
    });


    // Click event listener in charge of adding items to the combined input (Enrolled courses).
    $("body").on("tap click", "#addcourse-btn", function(ev) {
    	ev.preventDefault();
		courseCode = $("#dynamic-course-code").val();
		courseName = $("#dynamic-course-name").val();
		courseNumber = $("#dynamic-course-number").val();

		if(courseCode.trim() != "" && courseName.trim() != "" && courseNumber.trim() != "") {
			row = " " + courseCode + " - " + courseName + " - " + courseNumber + "\n";
			$("#dynamic-courses").val($("#dynamic-courses").val()+row); 
			$("#dynamic-course-code").val("");
			$("#dynamic-course-name").val("");
			$("#dynamic-course-number").val("");
		}
    });


    // Click event listener in charge of removing items to the combined input (Enrolled courses).
    $("body").on("tap click", "#resetcourses-btn", function(ev) {
    	ev.preventDefault();
		$("#dynamic-courses").val("");
    });
}

$(document).on('page:load', ready);
$(document).ready(ready)