<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Books</title>

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
            <th>Name</th>
            <th><a href="/ui/author/get/all">Author</a></th>
            <th><a href="/ui/genre/get/all">Genre</a></th>
            <th>Year of publishing</th>
            <th><a href="/ui/publisher/get/all">Publisher</a></th>
            <th><a href="/ui/stillage/get/all">Stillage</a></th>
            <th>Collateral Cost (UAH)</th>
            <th>Cost Per Day (UAH)</th>
            <th>Update</th>
            <th>Delete</th>
            <th>Created At</th>
            <th>Updated At</th>
        </tr>

        <#list books as book>
            <tr>
                <td>${book.id}</td>
                <td>${book.name}</td>
                <td>${book.authorName.name}</td>
                <td>${book.genreName.name}</td>
                <td>${book.yearOfPublishing}</td>
                <td>${book.publisherName.name}</td>
                <td>${book.stillageID.stillageId}</td>
                <td>${book.collateralCost}</td>
                <td>${book.costPerDay}</td>
                <td>
                    <a href="/ui/book/update/${book.id}">
                        <button class="btn btn-light btn-sm">Update</button>
                    </a>
                </td>
                <td>
                    <a href="/ui/book/delete/${book.id}">
                        <button class="btn btn-light btn-sm">Delete</button>
                    </a>
                </td>
                <td>${book.created_at}</td>
                <td><#if book.modified_at ?? > ${book.modified_at}
                    <#else> NULL
                    </#if></td>
            </tr>
        </#list>
    </table>

    &nbsp;<a href="/ui/book/create" class="btn btn-success">Create</a>
    <br>
    <br>
    <br>

    <div style="margin: 0 auto; width: 100%">
        <table class="table table-dark table-hover">
            <tr>
                <th>ЗАПИТИ</th>
            </tr>


            <tr>
                <td>Відсортована кількість книжок на автора</td>
                <td>
                    <a href="/api/author/get/sorted">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Відсортована кількість книжок на жанр</td>
                <td>
                    <a href="/api/genre/get/sorted">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Відсортована кількість книжок на видавця</td>
                <td>
                    <a href="/api/publisher/get/sorted">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Відсортована кількість книжок на стелаж</td>
                <td>
                    <a href="/api/stillage/get/sorted">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Відсортована кількість взятих читачами книжок на книгу</td>
                <td>
                    <a href="/api/book/get/sorted">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

        </table>
            </div>

</div>

</body>
</html>