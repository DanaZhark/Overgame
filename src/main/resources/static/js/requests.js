$(document).ready(function () {
    $('.login-page-button').click(function (e) {
        e.preventDefault()
        $(location).attr('href', window.location = 'user/login')
    })

    $('.register-page-button').click(function (e) {
        e.preventDefault()
        $(location).attr('href', window.location = 'user/register')
    })

    $('.main-page-button').click(function (e) {
        e.preventDefault()
        $(location).attr('href', window.location = 'main')
    })

    $('.logout-button').click(function (e) {
        e.preventDefault()
        $(location).attr('href', window.location = 'user/logout')
    })

    $('.user-profile-button').click(function (e) {
        e.preventDefault()
        $(location).attr('href', window.location = 'user/profile')
    })
});