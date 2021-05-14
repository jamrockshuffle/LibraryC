<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Member</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        fieldset{
            width: 280px;
            border-radius: 20px;
        }
        body{
            background-color: #212529;
            color: white;
            font-size: 13pt;

            font-family: Arial;
        }

        .stilisation{
            width: 293px;
            font-family: inherit;
            appearance: none;
            border: 0;
            border-radius: 5px;
            background: #198754;
            padding: 5px 20px;
            font-size: 13pt;
            cursor: pointer;
            outline: none;
            color: white;
        }
        .stilisation:hover {
            transition: .1s;
            background: #157347;
        }
        input{
            padding: 5px;
            width: 280px;
        }
        .h-color{
            color: #FFFFFF;
            background-color: #FFFFFF;
        }

        a{
            text-decoration: none;
            color: white;
        }

        a:hover{
            transition: .1s;
            color: lightgrey;
        }

        select
        {
            display: inline-block;
            width: 293px;
            height: 34px;
            line-height: 30px;
            position: relative;
        }

        select:before
        {
            content: ">";
            display: inline-block;
            background: white;
            position: absolute;
            right: -5px;
            top: 2px;
            z-index: 2;
            width: 50px;
            height: 26px;
            text-align: center;
            line-height: 26px;
            border: 2px solid #ddd;
            transform: rotate(90deg);
            cursor: pointer;
        }
    </style>
</head>
<body>

<div style="margin-top: 10px; width:100%">
    <fieldset style=" margin-left: auto; margin-right: auto">

        <a href="http://localhost:1010/ui/register/get/all" class="fa fa-reply" title="Назад"></a>

        <legend style="text-align: center;">Add Member</legend>
        <hr class="h-color">
        <form name="member" action="" method="post">
            Last Name:<@spring.formInput "registerForm.lastName" "" "text"/>
            <br>
            <br>
            First Name:<@spring.formInput "registerForm.firstName" "" "text"/>
            <br>
            <br>
            Patronymic:<@spring.formInput "registerForm.patronymic" "" "text"/>
            <br>
            <br>
            Birthday:<@spring.formInput "registerForm.birthday" "" "date"/>
            <br>
            <br>
            Address:<@spring.formInput "registerForm.address" "" "text"/>
            <br>
            <br>
            Phone Number:<@spring.formInput "registerForm.phoneNumber" "" "text"/>
            <br>
            <br>
            Category:<@spring.formSingleSelect "registerForm.category", categories, ""/>
            <br>
            <br>
            Description:<@spring.formInput "registerForm.description" "" "text"/>
            <br>
            <br>
            <input type="submit" class="stilisation" value="Create"/>
        </form>
    </fieldset>
</div>

</body>
</html>