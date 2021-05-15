<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Penalties</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
          crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>

        body{
            background: #212529;
        }

        a{
            text-decoration: none;
            color: white;
        }

        a:hover{
            transition: .1s;
            color: lightgrey;
        }
    </style>
</head>
<body>

<a href="/" class="fa fa-reply" title="Назад"></a>

<div style="margin: 0 auto; width: 100%">
    <table class="table table-dark table-hover">
        <tr>
            <th>ID</th>
            <th>Rented Book</th>
            <th>Reader</th>
            <th>Penalty Clause</th>
            <th>Penalty Sum</th>
            <th>Update</th>
            <th>Delete</th>
            <th>Created At</th>
            <th>Updated At</th>
        </tr>

        <#list penalties as penalty>
            <tr>
                <td>${penalty.id}</td>
                <td>${penalty.rentedBook.book.name}</td>
                <td>${penalty.rentedBook.reader.lastName}</td>
                <td>${penalty.penaltyClause.damageDescription}</td>
                <td>${penalty.penaltyClause.penaltySum}</td>
                <td>
                    <a href="/ui/penalty/update/${penalty.id}">
                        <button class="btn btn-light btn-sm">Update</button>
                    </a>
                </td>
                <td>
                    <a href="/ui/penalty/delete/${penalty.id}">
                        <button class="btn btn-light btn-sm">Delete</button>
                    </a>
                </td>
                <td>${penalty.created_at}</td>
                <td><#if penalty.modified_at ?? > ${penalty.modified_at}
                    <#else> NULL
                    </#if></td>
            </tr>
        </#list>
    </table>

    &nbsp;<a href="/ui/penalty/create" class="btn btn-success">Create</a>

    <br>
    <br>
    <br>

    <div style="margin: 0 auto; width: 100%">
        <table class="table table-dark table-hover">
            <tr>
                <th>ЗАПИТИ</th>
            </tr>

            <tr>
                <td>Відсортована кількість штрафів (ушкоджень) на взяту читачем книгу</td>
                <td>
                    <a href="/api/penalty/get/sorted">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Відсортована кількість взятих книжок на штраф (ушкодження)</td>
                <td>
                    <a href="/api/penaltysystem/get/sorted">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

        </table>
    </div>

</div>

</body>
</html>