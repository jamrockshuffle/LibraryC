<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Damages (for Penalties)</title>

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
            <th>Damage Description</th>
            <th>Penalty Sum</th>
            <th>Update</th>
            <th>Delete</th>
            <th>Created At</th>
            <th>Updated At</th>
        </tr>

        <#list damages as damage>
            <tr>
                <td>${damage.id}</td>
                <td>${damage.damageDescription}</td>
                <td>${damage.penaltySum}</td>
                <td>
                    <a href="/ui/penaltysystem/update/${damage.id}">
                        <button class="btn btn-light btn-sm">Update</button>
                    </a>
                </td>
                <td>
                    <a href="/ui/penaltysystem/delete/${damage.id}">
                        <button class="btn btn-light btn-sm">Delete</button>
                    </a>
                </td>
                <td>${damage.created_at}</td>
                <td><#if damage.modified_at ?? > ${damage.modified_at}
                    <#else> NULL
                    </#if></td>
            </tr>
        </#list>
    </table>

    &nbsp;<a href="/ui/penaltysystem/create" class="btn btn-success">Create</a>

</div>

</body>
</html>