var ready = function() {
    $(".page-load").each(function( index ) {
        var htmlFile = $(this).attr('id');
        $(this).load("forms/" + htmlFile + ".html");
    });

    $("body").on("tap", "#addcourse-btn", function(ev) {
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

    $("body").on("tap", "#resetcourses-btn", function(ev) {
    	ev.preventDefault();
		$("#dynamic-courses").val("");
    });
}

$(document).on('page:load', ready);
$(document).ready(ready)