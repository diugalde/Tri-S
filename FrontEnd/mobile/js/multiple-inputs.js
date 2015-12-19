$(document).ready(function() {
    
    $('#btnAdd').click( function() {

        $('#btnDel').removeAttr('disabled').button('enable');	// enable the "del" button

        // how many "duplicatable" input fields we currently have
        var num = $('.clonedInput').length;	

        // the numeric ID of the new input field being added	
        var newNum	= new Number(num + 1);		
        var newElem = $('#input' + num ).clone().attr('id', 'input' + newNum);

        newElem.children(':first').attr( 'id', 'name' + newNum ).attr('name', 'name_label[]');
        $('#input' + num).after(newElem);


        // business rule: limit the number of fields to 10
        if (newNum == 10) {
            $('#btnAdd' ).attr('disabled', 'disabled').button('disable');
            $('#btnAdd').parent().find('.ui-btn-text').text('maximum fields reached');
        }                        
    });

    $( '#btnDel' ).click( function() {
        // how many "duplicatable" input fields we currently have           
        var num = $( '.clonedInput' ).length;	

        // remove the last element	
        $('#input' + num ).remove();

        // enable the "add" button, since we've removed one				
        $('#btnAdd').removeAttr('disabled').button('enable');	
        $('#btnAdd').parent().find('.ui-btn-text').text('add another name');

        // if only one element remains, disable the "remove" button
        if ( num-1 == 1 )
            $('#btnDel' ).attr('disabled', 'disabled').button('disable');

    });  

});