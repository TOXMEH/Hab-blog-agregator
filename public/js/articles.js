/**
 * Created by anton on 22.03.17.
 */
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

    function stareButton(item) {
        return $("<button>")
            .text("Добавить в \"Избранное\"")
            .click(function (e) {
                $.ajax({
                    type: "PUT",
                    url: "/api/articles/stare?reader=" + getCookie("name") + "&article=" + item.header
                });
                location.reload();
            });
    }

    function watchLaterButton(item) {
        return $("<button>")
            .text("Добавить статью в \"Посмотреть позже\"")
            .click(function (e) {
                $.ajax({
                    type: "PUT",
                    url: "/api/articles/later?reader=" + getCookie("name") + "&article=" + item.header,
                });
                location.reload();
            });
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
            editing: false,
            inserting: false,
            sorting: true,
            paging: false,
            autoload: true,
            pageLoading: false,
            controller: {
                loadData: function () {
                    return $.ajax({
                        type: "GET",
                        url: "/api/articles/reader/" + getCookie("name")
                    });
                }
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
                    name: "hubOne",
                    title: "1-й хаб",
                    type: "select",
                    items: hubs, valueField: "name", textField: "name",
                    validate: "required",
                    // editing: true,
                    // inserting: true,
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
                {
                    type: "control",
                    deleteButton: false,
                    editButton: false
                }
                // {
                //     type: "control",
                //     deleteButton: false,
                //     editButton: false,
                //     itemTemplate: function (value, item) {
                //         var $result = jsGrid.fields.control.prototype.itemTemplate.apply(this, arguments);
                //         var $customButton;
                //         if (item.stared) {
                //             $customButton = stareButton(item);
                //         }
                //
                //         return $result.add($customButton);
                //     }
                // }, {
                //     type: "control",
                //     deleteButton: false,
                //     editButton: false,
                //     itemTemplate: function (value, item) {
                //         var $result = jsGrid.fields.control.prototype.itemTemplate.apply(this, arguments);
                //         var $customButton;
                //
                //         if (!item.watchLater) {
                //             $customButton = watchLaterButton(item);
                //         }
                //
                //         return $result.add($customButton);
                //     }
                // }
            ]
        });

    });
});