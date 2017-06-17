/**
 * Created by anton on 22.03.17.
 */
$(function () {
        function getCookie(name) {
            var matches = document.cookie.match(new RegExp(
                "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
            ));
            return matches ? decodeURIComponent(matches[1]) : undefined;
        }

        function subscribeButton(item) {
            console.log("/api/hubs/reader?hub=" + item.name + "&reader=" + getCookie("name"));
            return $("<button>")
                .text("Подписаться")
                .addClass('btn btn-default btn-xs')
                .click(function (e) {
                    let queryParameters = $.param({"hub": item.name, reader: getCookie("name")});
                    $.ajax({
                        type: "PUT",
                        url: "/api/hubs/reader?" + queryParameters
                    });
                    location.reload();
                });
        }

        function unsubscribeButton(item) {
            return $("<button>")
                .text("Отписаться")
                .click(function (e) {
                    let queryParameters = $.param({"hub": item.name, reader: getCookie("name")});
                    $.ajax({
                        type: "DELETE",
                        url: "/api/hubs/reader?" + queryParameters,
                    });
                    location.reload();
                });
        }

        $("#jsGrid").jsGrid({
            height: "450px",
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
                        url: "/api/hubs/reader/" + getCookie("name")
                    });
                }
            },
            fields: [
                {
                    name: "name", title: "Наименование", type: "text", width: 200, validate: {
                    validator: "required",
                    message: function () {
                        return "Хаб должен иметь наименование";
                    }
                }, editing: false
                },
                {
                    name: "description", title: "Описание", type: "text", width: 350, validate: {
                    validator: "required",
                    message: function () {
                        return "Хаб должен иметь описание";
                    }
                }, sorting: false

                },
                {
                    name: "subscribers",
                    title: "Количество подписчиков",
                    type: "number",
                    width: 50,
                    editing: false,
                    inserting: false,
                    align: "center"

                },
                {
                    type: "control",
                    deleteButton: false,
                    editButton: false,
                    itemTemplate: function (value, item) {
                        var $result = jsGrid.fields.control.prototype.itemTemplate.apply(this, arguments);
                        var $customButton;

                        if (item.subscribed) {
                            $customButton = unsubscribeButton(item);
                        } else {
                            $customButton = subscribeButton(item);
                        }

                        return $result.add($customButton);
                    }

                }
            ]
        });

    }
);