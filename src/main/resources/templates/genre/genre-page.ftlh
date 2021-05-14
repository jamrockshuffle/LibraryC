<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Genres</title>

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
            <th>Name</th>
            <th>Update</th>
            <th>Delete</th>
            <th>Created At</th>
            <th>Updated At</th>
        </tr>

        <#list genres as genre>
            <tr>
                <td>${genre.id}</td>
                <td>${genre.name}</td>
                <td>
                    <a href="/ui/genre/update/${genre.id}">
                        <button class="btn btn-light btn-sm">Update</button>
                    </a>
                </td>
                <td>
                    <a href="/ui/genre/delete/${genre.id}">
                        <button class="btn btn-light btn-sm">Delete</button>
                    </a>
                </td>
                <td>${genre.created_at}</td>
                <td><#if genre.modified_at ?? > ${genre.modified_at}
                    <#else> NULL
                    </#if></td>
            </tr>
        </#list>
    </table>

    &nbsp;<a href="/ui/genre/create" class="btn btn-success">Create</a>

</div>

</body>
</html>