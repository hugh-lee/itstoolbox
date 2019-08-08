$(document).ready(function () {
    
    //init search input
    $('#searchTxt').keyup(function (e) {
        filterMenu(e.target.value);
    });


    //share web
    shareWeb();

    // show or hide menu
    initMenuFunction();

    $('#feedback').click(function(e) {
        window.open('http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=RHBxcnd0cnV8BDU1aicrKQ');
    });
});





/** init recently menu */
function initRecentlyMenu() {
    $('.recently-menu').append(localStorage.getItem('recentlyMenuItem1') || "");
    $('.recently-menu').append(localStorage.getItem('recentlyMenuItem2') || "");
    $('.recently-menu').append(localStorage.getItem('recentlyMenuItem3') || "");

    $('.recently-menu .menuitem').click(function (i, val) {
        selectMenuItem(this);
    });
}

/** click menu item */
function clickMenuItem(e) {
    e.stopPropagation();

    $('.menuitem a i.fa-check').addClass('hide-fa-check');
    $('.menuitem a i.fa-check').removeClass('fa-check');

    if ($(this).find('ul').length == 0) {
        // recently menu
        var newHtml = this.outerHTML;
        if (newHtml != localStorage.getItem('recentlyMenuItem3') &&
            newHtml != localStorage.getItem('recentlyMenuItem2') &&
            newHtml != localStorage.getItem('recentlyMenuItem1')) {

            if (localStorage.getItem('recentlyMenuItem2') != null)
                localStorage.setItem('recentlyMenuItem3', localStorage.getItem('recentlyMenuItem2'));
            if (localStorage.getItem('recentlyMenuItem1') != null)
                localStorage.setItem('recentlyMenuItem2', localStorage.getItem('recentlyMenuItem1'));
            localStorage.setItem('recentlyMenuItem1', this.outerHTML);

            $('.recently-menu').prepend(this.outerHTML);

            $('.recently-menu .menuitem').each(function (i, val) {
                if (i > 2) {
                    $(val).remove();
                }
            });

            $('.recently-menu .menuitem').click(function (i, val) {
                selectMenuItem(this)
            });
        }

        selectMenuItem(this);
    } else {
        if ($(this).children('a').hasClass("folder-open")) {
            $(this).children('a').removeClass('folder-open');
            $(this).children('a').addClass('folder-close');

            $(this).children('ul').css({display:"none"});
        } else {
            $(this).children('a').removeClass('folder-close');
            $(this).children('a').addClass('folder-open');
            $(this).children('ul').css({display:"block"});
        }
    }
}

function selectMenuItem(menuItem) {
    $('.menuitem a i.fa-check').addClass('hide-fa-check');
    $('.menuitem a i.fa-check').removeClass('fa-check');

    $(menuItem).find("a i").addClass('fa-check');
    $(menuItem).find("a i").removeClass('hide-fa-check');
}

/**show or hide menu */
function initMenuFunction() {
    var toggle = {
        isOpen: false,
        open: function () {
            this.isOpen = true;
            $("body").addClass('status-show');
        },
        close: function () {
            this.isOpen = false;
            $("body").removeClass('status-show');
        }
    };

    $(".mod-mask").click(function () {
        toggle.close();
    });

    $(".menu").click(function () {
        if (toggle.isOpen) {
            toggle.close();
        } else {
            toggle.open();
        }
    });

    $(".side").click(function (e) {
        if (e.target.tagName == 'INPUT')
            return;

        if (e.target.tagName == "A") {
            $(".tool-menu .menuitem .active").removeClass('active');
            $(e.target).parent().addClass('active');
        }

        toggle.close();
    });

    $(".advertise").click(function () {
        toggle.close();
    });
}

/** filter nuenu according to the value of search box */
function filterMenu(filter) {
    filter = filter.toUpperCase();
    $(".tool-menu .menuitem").each(function () {
        if (!filter || filter.trim() == '') {
            $(this).removeClass('tool-menu-hide');
        } else if (this.innerText.indexOf(filter) == -1) {
            $(this).addClass('tool-menu-hide');
        } else {
            $(this).removeClass('tool-menu-hide');
        }
    });
}