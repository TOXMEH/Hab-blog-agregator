/**
 * Created by anton on 20.03.17.
 */
$(function () {
    var login = $('#inputLogin');
    var pass = $('#inputPass');
    if (!navigator.cookieEnabled) {
        alert('Включите cookie для работы с этим сайтом');
    }

    function getAttributes() {
        return {
            name: $('#inputLogin').val().trim(),
            password: $('#inputPass').val().trim()
        }
    }

    function getAttributesWithRadio() {
        return {
            name: $('#inputLogin').val().trim(),
            password: $('#inputPass').val().trim(),
            role: $('input[name=role]:checked').val()
        }
    }


    function setCookie(name, value, options) {
        options = options || {};

        var expires = options.expires;

        if (typeof expires == "number" && expires) {
            var d = new Date();
            d.setTime(d.getTime() + expires * 1000);
            expires = options.expires = d;
        }
        if (expires && expires.toUTCString) {
            options.expires = expires.toUTCString();
        }

        value = encodeURIComponent(value);

        var updatedCookie = name + "=" + value;

        for (var propName in options) {
            updatedCookie += "; " + propName;
            var propValue = options[propName];
            if (propValue !== true) {
                updatedCookie += "=" + propValue;
            }
        }

        document.cookie = updatedCookie;
    }


    $('#authorizeButton').on('click', function () {
        if (login.val() == '') {
            alert("Вы должны ввести имя пользователя");
        } else if (pass.val() == '') {
            alert("Вы должны ввести пароль пользователя");
        }
        queryParameters = $.param(getAttributes());
        $.ajax({
            url: '/api/auth?' + queryParameters,
            type: 'GET',
            statusCode: {
                200: function (result) {
                    setCookie("name", login.val());
                    console.log(login.val());
                    console.log(result.redirect);
                    console.log(document.cookie);
                    if (result.redirect == "admin") {
                        window.location.replace("admin.html");
                    } else if (result.redirect == "writer") {
                        window.location.replace("writer.html");
                    } else if (result.redirect == "reader") {
                        window.location.replace("reader.html");
                    }
                },
                401: function (result) {
                    alert("Пользователь с таким логином и паролем не найден");
                }
            }
        });


    });

    $('#registrateButton').on('click', function () {
        if (login.val() == '') {
            alert("Вы должны ввести имя пользователя");
        } else if (pass.val() == '') {
            alert("Вы должны ввести пароль пользователя");
        }
        queryParameters = $.param(getAttributesWithRadio());
        var role = $('input[name=role]:checked').val();
        $.ajax({
            url: '/api/auth?' + queryParameters,
            type: 'PUT',
            statusCode: {
                200: function (result) {
                    setCookie("name", login.val());
                    if (role == "writer") {
                        window.location.replace("writer.html");
                    } else if (role == "reader") {
                        window.location.replace("reader.html");
                    }
                },
                401: function (result) {
                    alert("Пользователь с таким именем(логином) уже существует");
                }
            }
        });
    });

});