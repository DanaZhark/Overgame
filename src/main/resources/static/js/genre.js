$(document).ready(function () {
    var element = '';
    $.ajax({
        url: "http://localhost:8080/v1/genres",
        type: "GET",
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    })
        .done(function (data) {
            if (data.length) {
                $.each(data, function (index, elem) {
                    element = element +
                        "                <div class=\"items " + elem.id + elem.code +
                        "                    <div class=\"info-block block-info clearfix\">\n" +
                        "                        <div data-toggle=\"buttons\" class=\"btn-group bizmoduleselect\">\n" +
                        "                            <label class=\"btn btn-default\">\n" +
                        "                                <div class=\"itemcontent\">\n" +
                        "                                    <input type=\"checkbox\" name=\"var_id[]\" autocomplete=\"off\" value=\"\">\n" +
                        "                                    <span class=\"fa fa-gamepad fa-2x\"></span>\n" +
                        "                                    <h6>" + elem.name + "</h6>\n" +
                        "                                </div>\n" +
                        "                            </label>\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                </div>"
                })
                $('#game-genres').append(element)
            }
        })
    var name_ru
    var name_en
    var description_ru
    var description_en
    var gameLink
    var imgLink
    var price
    var genreIds
    $('#game-ru-title').on('input', function (e) {
        name_ru = e.target.value
    })
    $('#game-en-title').on('input', function (e) {
        name_en = e.target.value
    })
    $('#game-ru-description').on('input', function (e) {
        description_ru = e.target.value
    })
    $('#game-en-description').on('input', function (e) {
        description_en = e.target.value
    })
    $('#game-link').on('input', function (e) {
        gameLink = e.target.value
    })
    $('#game-image-link').on('input', function (e) {
        imgLink = e.target.value
    })
    $('#game-price').on('input', function (e) {
        price = e.target.value
    })
    $('#genre-ids').on('input', function (e) {
        genreIds = e.target.value
    })
    $('#button').click(function (e) {
        e.preventDefault()
        if (username?.length && password?.length) {
            $.ajax({
                url: "http://localhost:8080/v1/games",
                type: "POST",
                headers: {
                    "Content-Type": "application/json; charset=utf-8"
                },
                data: JSON.stringify({
                    name: {
                        ru: name_ru,
                        en: name_en
                    },
                    description: {
                        ru: description_ru,
                        en: description_en
                    },
                    gameLink: gameLink,
                    imgLink: imgLink,
                    price: price,
                    genreIds: genreIds,
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