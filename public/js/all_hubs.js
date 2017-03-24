$(function () {

    $("#jsGrid").jsGrid({
        height: "450px",
        width: "100%",
        filtering: false,
        editing: true,
        inserting: true,
        sorting: true,
        paging: false,
        autoload: true,
        pageLoading: false,
        // deleteConfirm: "Вы действительно хотите удалить хаб?",
        controller: {
            loadData: function () {
                return $.ajax({
                    type: "GET",
                    url: "/api/hubs"
                });
            },
            insertItem: function (item) {
                return $.ajax({
                    type: "POST",
                    url: "/api/hubs",
                    data: item
                });
            },
            updateItem: function (item) {
                return $.ajax({
                    type: "PUT",
                    url: "/api/hubs?name=" + item.name + "&description=" + item.description,
                    // data: {
                    //     "name": item.name,
                    //     "description": item.description
                    // }
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
            }

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
            {type: "control", deleteButton: false}
        ]
    });

});