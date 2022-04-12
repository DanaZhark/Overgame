$(document).ready(function () {
    var username
    var email
    $('#username').on('input', function (e) {
        username = e.target.value
    })
    $('#email').on('input', function (e) {
        email = e.target.value
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
});