$(document).ready(function () {
    //init tool menus
    request({
        url: 'tools/tools.json',
        method: 'get',
        success: initToolMenus
    });

});

/**init tool menus */
function initToolMenus(data) {
    $('.example').empty();

    var html = data.tools.reduce(function (html, tool, index) {
        return html + buildToolHtml(tool, (index + 1), 0, 0, 0);
    }, "");

    $(".all-menu").append(html); // 追加新元素

    //click menu item
    $('.tool-menu .menuitem').click(clickMenuItem);
}

function buildToolHtml(tool, first, second, third, forth) {
    let ul = "";
    if (tool.tools) {
        ul = tool.tools.reduce(function (html, tool, index) {
            sub_second = second;
            sub_third = third;
            sub_forth = forth;
            if (second == 0)
                sub_second = index + 1;
            else if (third == 0)
                sub_third = index + 1;
            else
                sub_forth = index + 1;

            return html + buildToolHtml(tool, first, sub_second, sub_third, sub_forth);
        }, "");

        ul = `<ul class="">${ul}</ul>`;
    }

    first = (first) + ".";
    second = (second != 0) ? (second) + "." : "";
    third = (third != 0) ? (third) + "." : "";
    forth = (forth != 0) ? (forth) + "." : "";
    clazz = (ul == "") ? 'leaf' : 'folder-open';
    href = (tool.url) ? `href="${tool.url}"` : '';
    let li = `<li class="menuitem" title="${tool.keywords || ''}" data-toggle="tooltip" data-placement="top" >
        <a ${href} target="contentFrame" class="${clazz}">
            <i class="fa hide-fa-check"></i>
            <b>${first}${second}${third}${forth}</b> ${tool.name_cn}
        </a>
        <span style="display:none">${tool.keywords}</span>
        ${ul}
    </li>`;

    return li;
}
