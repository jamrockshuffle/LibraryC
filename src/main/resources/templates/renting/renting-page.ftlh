<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Rented Books</title>

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
            <th>Rented Book</th>
            <th>Collateral Cost (UAH)</th>
            <th>Cost Per Day (UAH)</th>
            <th>Renter</th>
            <th>Renting Date</th>
            <th>Expected Return Date</th>
            <th>Actual Return Date</th>
            <th>Penalty (UAH)</th>
            <th>Renting Person</th>
            <th>Update</th>
            <th>Delete</th>
            <th>Created At</th>
            <th>Updated At</th>
        </tr>

        <#list renteds as rented>
            <tr>
                <td>${rented.id}</td>
                <td>${rented.book.name}</td>
                <td>${rented.book.collateralCost}</td>
                <td>${rented.book.costPerDay}</td>
                <td>${rented.reader.lastName}</td>
                <td>${rented.rentingDate}</td>
                <td>${rented.expectedReturnDate}</td>
                <td>${rented.actualReturnDate}</td>
                <td>${rented.penaltySum}</td>
                <td>${rented.rentingPerson.lastName}</td>
                <td>
                    <a href="/ui/renting/update/${rented.id}">
                        <button class="btn btn-light btn-sm">Update</button>
                    </a>
                </td>
                <td>
                    <a href="/ui/renting/delete/${rented.id}">
                        <button class="btn btn-light btn-sm">Delete</button>
                    </a>
                </td>
                <td>${rented.created_at}</td>
                <td><#if rented.modified_at ?? > ${rented.modified_at}
                    <#else> NULL
                    </#if></td>
            </tr>
        </#list>
    </table>

    &nbsp;<a href="/ui/renting/create" class="btn btn-success">Create</a>

    <br>
    <br>
    <br>

    <div style="margin: 0 auto; width: 100%">
        <table class="table table-dark table-hover">
            <tr>
                <th>ЗАПИТИ</th>
            </tr>

            <tr>
                <td>Відсортована кількість взятих читачами книжок на книгу</td>
                <td>
                    <a href="/api/book/get/sorted">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Відсортована кількість взятих читачами книжок на жанр</td>
                <td>
                    <a href="/api/renting/get/allgenres">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Відсортована кількість взятих читачами книжок на автора</td>
                <td>
                    <a href="/api/renting/get/allauthors">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
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
                <td>Відсортована кількість взятих книжок на читача</td>
                <td>
                    <a href="/api/register/get/sorted">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Відсортована кількість виданих книжок на робітника</td>
                <td>
                    <a href="/api/personnel/get/sorted">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

        </table>
    </div>

</div>

</body>
</html>