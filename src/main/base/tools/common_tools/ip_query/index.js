$(document).ready(function () {
    enableCrossDomain();
    parseCurrentIp();
    displayReferences();

    $('button').click(function click(e) {
        var result = checkIpDomain();
        if (result.domain)
            queryDomain(result.domain);
        else
            queryIp(result.ip);
    });
});


function parseCurrentIp() {
    $('#localIp').html(returnCitySN.cip);
    $('#nation').html(returnCitySN.cname);
}

function queryIp(ip) {
    var url = `http://www.ip138.com/ips138.asp?ip=${ip}&action=2`;
    request({
        url: url,
        method: 'get',
        datatype: 'text',
        contentType: 'text/html; charset=gb2312',
        success: function (str) {
            var start = str.indexOf('<ul class="ul1"><li>本站数据');
            var end = str.indexOf('</ul>', start);
            $('#comment').html(str.substring(start, end))
        }
    });
}

function queryDomain(domain) {
    var url = `http://site.ip138.com/${domain}/whois.htm`;
    request({
        url: url,
        method: 'get',
        datatype: 'text',
        contentType: 'text/html; charset=gb2312',
        success: function (str) {
            var start = str.indexOf('<div class="whois" id="whois">');
            var end = str.indexOf('</div>', start);
            $('#comment').html(str.substring(start, end))
        }
    });
}

function checkIpDomain() {
    $("#ipdomain").focus();

    var value = $("#ipdomain").val();
    value = value.replace(/^http(s)?:\/\//, '').replace(/\/$/, '');
    if (!value.length) {
        showErrWin("请输入IP或则域名！");
        return false;
    } else if (value.match(/[A-Za-z_-]/)) {
        if (!value.match(reg['domain'])) {
            showErrWin('域名格式错误！');
            return false;
        }
        return {
            domain: value
        };
    } else {
        var arr = value.split(".");
        if (arr.length != 4) {
            aleshowErrWinrt("不是正确的IP");
            return false;
        } else {
            for (var i = 0; i < 4; i++) {
                if (isNaN(arr[i]) || arr[i].length < 0 || arr[i] > 255) {
                    aleshowErrWinrt("不是正确的IP");
                    return false;
                }
            }
        }
        return {
            ip: value
        };
    }

}

///////////////////////////////////////////////////////
var reg = {
    mobile: /^1[3|4|5|6|7|8|9][0-9]{5,9}$/,
    zip: /^\d{4,6}$/,
    zone: /^0\d{2,6}$/,
    id: /^\d{15}$|^\d{18}$|^\d{17}[xX]$/,
    domain: /^([a-zA-Z0-9][-a-zA-Z0-9]{0,62}\.)+([a-zA-Z]{2,63})\.?$/
};
var check = {
    'mobile': function () {
        var value = this.mobile.value.trim();
        if (!value.length) {
            alert('手机号不能为空！');
            this.mobile.focus();
            return false;
        } else if (!value.match(reg['mobile'])) {
            alert('不是完整的11位手机号或者正确的手机号前七位！');
            this.mobile.focus();
            return false;
        }
    },
    'ip': function () {
        var value = $("ipdomain").ip.value.trim();
        value = value.replace(/^http(s)?:\/\//, '').replace(/\/$/, '');
        if (!value.length) {
            this.ip.focus();
            return false;
        } else if (value.match(/[A-Za-z_-]/)) {
            if (!value.match(reg['domain'])) {
                alert('域名格式错误！');
                this.ip.focus();
                return false;
            }
        } else {
            var arr = value.split(".");
            if (arr.length != 4) {
                alert("不是正确的IP");
                this.ip.focus();
                return false;
            } else {
                for (var i = 0; i < 4; i++) {
                    if (isNaN(arr[i]) || arr[i].length < 0 || arr[i] > 255) {
                        alert("不是正确的IP");
                        this.ip.focus();
                        return false;
                    }
                }
            }
        }
        this.ip.value = value;
    },
    'zip': function () {
        var value = this.zip.value.trim();
        if (!value.match(reg['zip'])) {
            alert('请输入邮政编码前4-6位！');
            this.zip.focus();
            return false;
        }
    },
    'zone': function () {
        var value = this.zone.value.trim();
        if (!value.match(reg['zone'])) {
            alert('请输入以“0”开头的3-7位区号！');
            this.zone.focus();
            return false;
        }
    },
    'area': function () {
        var value = this.area.value.trim();
        if (!value.length) {
            alert('请输入地址！');
            this.area.focus();
            return false;
        } else if (value.length < 2) {
            alert('地址至少要有2个字！');
            this.area.focus();
            return false;
        }
    },
    'id': function () {
        var value = this.userid.value.trim();
        if (!value.match(reg['id'])) {
            alert('请输入15位或18位身份证号！');
            this.userid.focus();
            return false;
        }
    }
}