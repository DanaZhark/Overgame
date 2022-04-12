$(document).ready(function () {

    function refreshToken() {
        $.ajax({
            url: "http://localhost:8080/v1/users/refresh",
            type: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            data: JSON.stringify({
                refreshToken: sessionStorage.getItem('refreshToken')
            }),
            error: function (error) {
                if (error.status === 401) {
                    $(location).attr('href', 'http://localhost:8080/overgame/user/login')
                }
            }
        })
            .done(function (val) {
                sessionStorage.setItem('accessToken', val.accessToken)
                sessionStorage.setItem('refreshToken', val.refreshToken)
            })
    }

    $.ajax({
        url: "http://localhost:8080/v1/users/me",
        type: "GET",
        headers: {
            "Authorization": "Bearer " + sessionStorage.getItem('accessToken'),
            "Content-Type": "application/json; charset=utf-8"
        }
    })
        .done(function (data) {
            var usernameElements = document.getElementsByClassName('demo');
            for (var index = 0; index < usernameElements.length; index++) {
                usernameElements[index].innerHTML = data.username;
            }
        })
        .fail(function (err) {
            if (err.status === 401) {
                refreshToken()
            }
        });

    var username
    var email
    var password
    var confirmPassword
    $('#username').on('input', function (e) {
        username = e.target.value
    })
    $('#email').on('input', function (e) {
        email = e.target.value
    })
    $('#password').on('input', function (e) {
        password = e.target.value
    })
    $('#confirmPassword').on('input', function (e) {
        confirmPassword = e.target.value
    })
    $('#button').click(function (e) {
        e.preventDefault()
        if (username?.length && email?.length) {
            $.ajax({
                url: "http://localhost:8080/v1/users/register",
                type: "POST",
                headers: {
                    "Content-Type": "application/json; charset=utf-8"
                },
                data: JSON.stringify({
                    username: username,
                    email: email,
                    roleCode: "OVERGAME_ADMIN_ROLE"
                })
            })
                .done(function (data) {
                    document.cookie.accessToken = data.accessToken;
                    sessionStorage.setItem('accessToken', data.accessToken)
                    sessionStorage.setItem('refreshToken', data.refreshToken)
                    $(location).attr('href', 'http://localhost:8080/overgame/user/login')
                })
        }
        return false
    })
})
