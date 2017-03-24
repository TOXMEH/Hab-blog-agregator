/**
 * Created by anton on 21.03.17.
 */
$(function () {
    function getCookie(name) {
        var matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
        ));
        return matches ? decodeURIComponent(matches[1]) : undefined;
    }

    $.ajax({
        type: "GET",
        url: "/api/hubs"
    }).done(function (hubs) {

        hubs.unshift({name: ""});


        $("#jsGrid").jsGrid({
            height: "600px",
            width: "100%",
            filtering: false,
            editing: true,
            inserting: true,
            sorting: true,
            paging: false,
            autoload: true,
            pageLoading: false,
            controller: {
                loadData: function () {
                    return $.ajax({
                        type: "GET",
                        url: "/api/articles/writer/" + getCookie("name"),
                    });
                },
                insertItem: function (item) {
                    return $.ajax({
                        type: "POST",
                        url: "/api/articles",
                        data: item
                    });
                },
                updateItem: function (item) {
                    return $.ajax({
                        type: "PUT",
                        url: "/api/articles?header=" + item.header + "&text=" + item.text + "&hubOne=" + item.hubOne + "&hubTwo=" + item.hubTwo + "&hubThree=" + item.hubThree,
                    });
                },
            },
            fields: [
                {
                    name: "header", title: "Заголовок", type: "text", validate: {
                    validator: "required",
                    message: function () {
                        return "Хаб должен иметь заголовок";
                    }
                }, editing: false
                },

                {
                    name: "text", title: "Текст", type: "text", validate: {
                    validator: "required",
                    message: function () {
                        return "Хаб должен иметь текст";
                    }
                }, sorting: false, width: 150

                },

                {
                    name: "writer",
                    title: "Автор (писатель)",
                    type: "text",
                    editing: false
                },

                {
                    name: "publicationYear",
                    title: "Год публикации",
                    type: "number",
                    editing: false,
                    inserting: false,
                },

                {
                    name: "timesStared",
                    title: "Количество раз статья была добавлена в избранное",
                    type: "number",
                    editing: false,
                    inserting: false

                },

                {
                    name: "timesWatchLater",
                    title: "Количество раз статья была добавлена в «Прочитать позже»",
                    type: "number",
                    editing: false,
                    inserting: false

                },

                {
                    name: "hubOne",
                    title: "1-й хаб",
                    type: "select",
                    items: hubs, valueField: "name", textField: "name",
                    validate: {
                        validator: "required",
                        message: function () {
                            return "Поле первого хаба должно быть заполнено";
                        }
                    },
                    sorting: false
                },

                {
                    name: "hubTwo",
                    title: "2-й хаб",
                    type: "select",
                    items: hubs, valueField: "name", textField: "name",
                    // editing: false,
                    sorting: false
                },

                {
                    name: "hubThree",
                    title: "3-й хаб",
                    type: "select",
                    items: hubs, valueField: "name", textField: "name",
                    // editing: false,
                    sorting: false
                },
                {type: "control", deleteButton: false}
            ]
        });

    });
});