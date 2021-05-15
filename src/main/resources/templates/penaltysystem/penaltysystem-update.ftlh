<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Damage</title>


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
            background: #f8f9fa;
            color: black;
            padding: 5px 20px;
            font-size: 13pt;
            cursor: pointer;
            outline: none;
        }
        .stilisation:hover {
            transition: .1s;
            background: lightgrey;
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
    </style>
</head>
<body>

<div style="margin-top: 90px; width:100%">
    <fieldset style=" margin-left: auto; margin-right: auto">

        <a href="/ui/penaltysystem/get/all" class="fa fa-reply" title="Назад"></a>

        <legend style="text-align: center;">Update Damage</legend>
        <hr class="h-color">
        <form name="damage" action="" method="post">
            Damage Description:<@spring.formInput "penaltySystemUpdForm.damageDescription" "" "text"/>
            <br>
            <br>
            Penalty Sum:<@spring.formInput "penaltySystemUpdForm.penaltySum" "" "text"/>
            <br>
            <br>
            Description:<@spring.formInput "penaltySystemUpdForm.description" "" "text"/>
            <br>
            <br>
            <input type="submit" class="stilisation" value="Update"/>
        </form>
    </fieldset>
</div>

</body>
</html>