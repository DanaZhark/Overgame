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
            document.getElementById("username").innerHTML = data.username;
        })
        .fail(function (err) {
            if (err.status === 401) {
                refreshToken()
            }
        });
})
