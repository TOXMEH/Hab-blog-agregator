/**
 * Created by anton on 20.03.17.
 */
$(function () {
    let login = $('#inputLogin');
    let pass = $('#inputPass');
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

        let expires = options.expires;

        if (typeof expires === "number" && expires) {
            let d = new Date();
            d.setTime(d.getTime() + expires * 1000);
            expires = options.expires = d;
        }
        if (expires && expires.toUTCString) {
            options.expires = expires.toUTCString();
        }

        value = encodeURIComponent(value);

        let updatedCookie = name + "=" + value;

        for (let propName in options) {
            updatedCookie += "; " + propName;
            let propValue = options[propName];
            if (propValue !== true) {
                updatedCookie += "=" + propValue;
            }
        }

        document.cookie = updatedCookie;
    }


    $('#authorizeButton').on('click', function () {
        if (login.val() === '') {
            alert("Вы должны ввести имя пользователя");
        } else if (pass.val() === '') {
            alert("Вы должны ввести пароль пользователя");
        }
        let queryParameters = $.param(getAttributes());
        $.ajax({
            url: '/api/auth?' + queryParameters,
            type: 'GET',
            success: function (result) {
                setCookie("name", login.val());
                console.log(login.val());
                console.log(result.redirect);
                console.log(document.cookie);
                if (result.redirect === "admin") {
                    window.location.replace("admin.html");
                } else if (result.redirect === "writer") {
                    window.location.replace("writer.html");
                } else if (result.redirect === "reader") {
                    window.location.replace("reader.html");
                }
            },
            error: function (result) {
                alert("Пользователь с таким именем и паролем не найден");
            }

        });


    });

    $('#registrateButton').on('click', function () {
        if (login.val() === '') {
            alert("Вы должны ввести имя пользователя");
        } else if (pass.val() === '') {
            alert("Вы должны ввести пароль пользователя");
        }
        let queryParameters = $.param(getAttributesWithRadio());
        let role = $('input[name=role]:checked').val();
        $.ajax({
            url: '/api/auth?' + queryParameters,
            type: 'PUT',
            success: function (result) {
                setCookie("name", login.val());
                if (role === "writer") {
                    window.location.replace("writer.html");
                } else if (role === "reader") {
                    window.location.replace("reader.html");
                }
            },
            error: function (result) {
                alert("Пользователь с таким именем(логином) уже существует");
            }

        });
    });

});