$(document).ready(function () {
    var element = '';
    $.ajax({
        url: "http://localhost:8080/v1/games",
        type: "GET",
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    })
        .done(function (data) {
            if (data.length) {
                $.each(data, function (index, elem) {
                    element = element +
                        "<div class=\"col-md-6 text-center\">\n" +
                        "                            <div class=\"thumbnail\">\n" +
                        "                                <a href=\"{% url 'post:detail' post.pk %}\"><img class=\"poster\"\n" +
                        "                                                                               src=" + elem.imgLink + "></a>\n" +
                        "                                <h5><a href=\"{% url 'post:detail' post.pk %}\"\n" +
                        "                                       style=\"font-family: 'Press Start 2P', cursive; color: #ffffff\">" + elem.name + "</a>\n" +
                        "                                </h5>\n" +
                        "                                <span class=\"more\">\n" +
                        "                        <p style=\"padding: 10px\">" + elem.description + "</p>\n" +
                        "                        <div class=\"item\">\n" +
                        "                            <div class=\"tagItem\">\n" +
                        "                                <a href=\"/post/allpost/{{ tag.slug }}\">{{tag.name}}</a>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                    </span>\n" +
                        "                                <div class=\"item\"\n" +
                        "                                     style=\"display: block; margin-left: auto; margin-right: auto; width: 350px;\">\n" +
                        "                                    <a href=\"{% url 'post:detail' post.pk %}\">\n" +
                        "                                        <button class=\"button\"\n" +
                        "                                                style=\"letter-spacing: 0px; width: 120px;background: rgb(115,158,225); color: #fff; float: left; padding: 5px;\"\n" +
                        "                                                title=\"PLAY\" type=\"button\">PLAY\n" +
                        "                                        </button>\n" +
                        "                                    </a>\n" +
                        "                                    <a href=\"\">\n" +
                        "                                        <button class=\"button\"\n" +
                        "                                                style=\"letter-spacing: 0px; width: 120px;background: rgb(115,158,225); color: #fff; float: right; padding: 5px;\"\n" +
                        "                                                title=\"MORE\" type=\"button\">MORE\n" +
                        "                                        </button>\n" +
                        "                                    </a>\n" +
                        "                                </div>\n" +
                        "                            </div>\n" +
                        "                        </div>"
                })
                $('#games-list').append(element)
            }
        })
});