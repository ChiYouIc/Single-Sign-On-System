let originUrl = '';

window.onload = function () {
    const url = window.location.href;
    const pattern = new RegExp('originUrl=[a-zA-z]+://[^\\s]*', 'i');
    const origin = pattern.exec(url)[0];
    originUrl = origin.substr(origin.indexOf('=') + 1, origin.length)
}

function login() {

    let username = $('#username').val();
    let password = $('#password').val();

    console.log(username, password)

    $.ajax({
        url: '/sso/login',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        param: {"originUrl": originUrl},
        data: JSON.stringify({'username': username, 'password': password}),
        dataType: 'text',
        success: function (result) {

        },
        error: function (msg) {

        }
    })
}

function inputOnFocus(val) {
    val.parentNode.className += " input-active";
}

function inputOnblur(val) {
    let className = val.parentNode.className;
    let classNameList = className.split(" ");

    if (classNameList && classNameList.length > 1) {
        val.parentNode.className = classNameList[0];
    }
}