<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Workers</title>

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
            <th>Last Name</th>
            <th>First Name</th>
            <th>Patronymic</th>
            <th>Position</th>
            <th>Shift</th>
            <th>Birthday</th>
            <th>Hiring Date</th>
            <th>Address</th>
            <th>Phone Number</th>
            <th>Salary (UAH)</th>
            <th>Update</th>
            <th>Delete</th>
            <th>Created At</th>
            <th>Updated At</th>
        </tr>

        <#list workers as worker>
            <tr>
                <td>${worker.id}</td>
                <td>${worker.lastName}</td>
                <td>${worker.firstName}</td>
                <td>${worker.patronymic}</td>
                <td>${worker.position.name}</td>
                <td>${worker.shift.name}</td>
                <td>${worker.birthday}</td>
                <td>${worker.hiringDate}</td>
                <td>${worker.address}</td>
                <td>${worker.phoneNumber}</td>
                <td>${worker.salaryUAH}</td>
                <td>
                    <a href="/ui/personnel/update/${worker.id}">
                        <button class="btn btn-light btn-sm">Update</button>
                    </a>
                </td>
                <td>
                    <a href="/ui/personnel/delete/${worker.id}">
                        <button class="btn btn-light btn-sm">Delete</button>
                    </a>
                </td>
                <td>${worker.created_at}</td>
                <td><#if worker.modified_at ?? > ${worker.modified_at}
                    <#else> NULL
                    </#if></td>
            </tr>
        </#list>
    </table>

    &nbsp;<a href="/ui/personnel/create" class="btn btn-success">Create</a>

    <br>
    <br>
    <br>

    <div style="margin: 0 auto; width: 100%">
        <table class="table table-dark table-hover">
            <tr>
                <th>ЗАПИТИ</th>
            </tr>

            <tr>
                <td>Відсортована кількість виданих книжок на робітника</td>
                <td>
                    <a href="/api/personnel/get/sorted">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Сума всіх зарплат робітників</td>
                <td>
                    <a href="/api/personnel/get/allsalary">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Середня зарплата робітників</td>
                <td>
                    <a href="/api/personnel/get/averagesalary">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Кількість робітників на зміну</td>
                <td>
                    <a href="/api/shift/get/sorted">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Сума всіх зарплат робітників на зміну</td>
                <td>
                    <a href="/api/shift/get/allsalary">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Середня зарплата робітників на зміну</td>
                <td>
                    <a href="/api/shift/get/averagesalary">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Кількість робітників на посаду</td>
                <td>
                    <a href="/api/position/get/sorted">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Сума всіх зарплат робітників на посаду</td>
                <td>
                    <a href="/api/position/get/allsalary">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Середня зарплата робітників на посаду</td>
                <td>
                    <a href="/api/position/get/averagesalary">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

            <tr>
                <td>Кількість робітників да дату народження</td>
                <td>
                    <a href="/api/personnel/get/sortedbirth">
                        <button class="btn btn-light btn-sm">Перейти</button>
                    </a>
                </td>
            </tr>

        </table>
    </div>

</div>

</body>
</html>