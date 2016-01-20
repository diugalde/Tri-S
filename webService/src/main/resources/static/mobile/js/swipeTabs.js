/* 
    TRI-S - Mobile App
    Developed by: Diego Ugalde Ávila - Luis E. Ugalde Barrantes. 2016.
    This code is licensed under the GNU GENERAL PUBLIC LICENSE (GPL) V3. See LICENSE file for details.
*/

var ready = function() {

    function changeNavTab(left) {
        var $tabs = $("div[data-role=navbar] li a", $("div[data-role=page].ui-page-active"));
        var curidx = $tabs.closest("a.ui-btn-active").parent().index();
        var nextidx = 0;
        if (left) {
            nextidx = (curidx == $tabs.length - 1) ? 0 : curidx + 1;
        } else {
            nextidx = (curidx == 0) ? $tabs.length - 1 : curidx - 1;
        }
        $tabs.eq(nextidx).click();
        $tabs.eq(nextidx).focus();
        $.mobile.activePage.focus();
    }

    //Change current tab depending on the action.
    $("div[data-role=page]").on("swipeleft", function (event) {
        changeNavTab(true);
    });
    $("div[data-role=page]").on("swiperight", function (event) {
        changeNavTab(false);
    });
}

$(document).on('page:load', ready);
$(document).ready(ready);
