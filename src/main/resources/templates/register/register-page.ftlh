<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Readers</title>

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

<a href="http://localhost:1010/" class="fa fa-reply" title="Назад"></a>

<div style="margin: 0 auto; width: 100%">
    <table class="table table-dark table-hover">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Patronymic</th>
            <th>Birthday</th>
            <th>Address</th>
            <th>Phone Number</th>
            <th>Category</th>
            <th>% discount</th>
            <th>Update</th>
            <th>Delete</th>
            <th>Created At</th>
            <th>Updated At</th>
        </tr>

        <#list members as member>
            <tr>
                <td>${member.id}</td>
                <td>${member.firstName}</td>
                <td>${member.lastName}</td>
                <td>${member.patronymic}</td>
                <td>${member.birthday}</td>
                <td>${member.address}</td>
                <td>${member.phoneNumber}</td>
                <td>${member.category.category}</td>
                <td>${member.category.discountPercentage}</td>
                <td>
                    <a href="/ui/register/update/${member.id}">
                        <button class="btn btn-light btn-sm">Update</button>
                    </a>
                </td>
                <td>
                    <a href="/ui/register/delete/${member.id}">
                        <button class="btn btn-light btn-sm">Delete</button>
                    </a>
                </td>
                <td>${member.created_at}</td>
                <td><#if member.modified_at ?? > ${member.modified_at}
                    <#else> NULL
                    </#if></td>
            </tr>
        </#list>
    </table>

    &nbsp;<a href="/ui/register/create" class="btn btn-success">Create</a>

    <br>
    <br>
    <br>

    <div style="margin: 0 auto; width: 100%">
        <table class="table table-dark table-hover">
            <tr>
                <th>ЗАПИТИ</th>
            </tr>

            <tr>
                <td>Відсортована кількість взятих книжок на читача</td>
                <td>
                    <a href="/api/register/get/sorted">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Відсортована кількість читачів на категорії знижок</td>
                <td>
                    <a href="/api/discountsystem/get/sorted">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Кількість читачів да дату народження</td>
                <td>
                    <a href="/api/register/get/sortedbirth">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

        </table>
    </div>

</div>

</body>
</html>