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
                    element = element + "<div class=\"items-collection\">\n" +
                        "\n" +
                        "                <div class=\"items " + elem.id + " col-xs-6 col-sm-3 col-md-3 col-lg-3\">\n" +
                        "                    <div class=\"info-block block-info clearfix\">\n" +
                        "                        <div data-toggle=\"buttons\" class=\"btn-group bizmoduleselect\">\n" +
                        "                            <label class=\"btn btn-default\">\n" +
                        "                                <div class=\"itemcontent\">\n" +
                        "                                    <input type=\"checkbox\" name=\"var_id[]\" autocomplete=\"off\" value=\"\">\n" +
                        "                                    <span class=\"fa fa-car fa-2x\"></span>\n" +
                        "                                    <h5>" + elem.name + "</h5>\n" +
                        "                                </div>\n" +
                        "                            </label>\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                </div>"
                })
                $('#game-genres').append(element)
            }
        })
});