

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

    $("div[data-role=page]").on("swipeleft", function (event) {
        changeNavTab(true);
    });
    $("div[data-role=page]").on("swiperight", function (event) {
        changeNavTab(false);
    });
}

$(document).on('page:load', ready);
$(document).ready(ready);
