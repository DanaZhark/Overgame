$(document).ready(function () {

    $('.register-page-button').click(function (e) {
        e.preventDefault()
        $(location).attr('href', 'http://localhost:8080/overgame/user/register')
    })

    $('.main-page-button').click(function (e) {
        e.preventDefault()
        $(location).attr('href', 'http://localhost:8080/overgame/main')
    })

    $('.welcome-page-button').click(function (e) {
        e.preventDefault()
        $(location).attr('href', 'http://localhost:8080/overgame/welcome')
    })

    $('.login-page-button').click(function (e) {
        e.preventDefault()
        $(location).attr('href', 'http://localhost:8080/overgame/user/login')
    })

    $('.success-page-button').click(function (e) {
        e.preventDefault()
        $(location).attr('href', 'http://localhost:8080/success')
    })

    $('.logout-page-button').click(function (e) {
        e.preventDefault()
        $(location).attr('href', 'http://localhost:8080/overgame/user/logout')
    })

    $('.user-profile-page-button').click(function (e) {
        e.preventDefault()
        $(location).attr('href', 'http://localhost:8080/overgame/user/profile')
    })

    $('.user-edit-profile-page-button').click(function (e) {
        e.preventDefault()
        $(location).attr('href', 'http://localhost:8080/overgame/user/edit-profile')
    })
});