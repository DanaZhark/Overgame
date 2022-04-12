$(document).ready(function () {
    var username
    var password
    $('#email').on('input', function (e) {
        username = e.target.value
    })
    $('#password').on('input', function (e) {
        password = e.target.value
    })
    $('#button').click(function (e) {
        e.preventDefault()
        if (username?.length && password?.length) {
            $.ajax({
                url: "http://localhost:8080/v1/users/login",
                type: "POST",
                headers: {
                    "Content-Type": "application/json; charset=utf-8"
                },
                data: JSON.stringify({
                    username: username,
                    password: password
                })
            })
                .done(function (data) {
                    document.cookie.accessToken = data.accessToken;
                    sessionStorage.setItem('accessToken', data.accessToken)
                    sessionStorage.setItem('refreshToken', data.refreshToken)
                    $(location).attr('href', 'http://localhost:8080/overgame/user/success')
                })
        }
        return false
    })
});