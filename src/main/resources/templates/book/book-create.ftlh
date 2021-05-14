<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Book</title>

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

        <a href="http://localhost:1010/ui/book/get/all" class="fa fa-reply" title="Назад"></a>

        <legend style="text-align: center;">Add Book</legend>
        <hr class="h-color">
        <form name="member" action="" method="post">
            Name:<@spring.formInput "bookForm.name" "" "text"/>
            <br>
            <br>
            Author Name:<@spring.formSingleSelect "bookForm.authorName", authors, ""/>
            <br>
            <br>
            Genre Name:<@spring.formSingleSelect "bookForm.genreName", genres, ""/>
            <br>
            <br>
            Year of publishing:<@spring.formInput "bookForm.yearOfPublishing" "" "text"/>
            <br>
            <br>
            Publisher Name:<@spring.formSingleSelect "bookForm.publisherName", publishers, ""/>
            <br>
            <br>
            Stillage ID:<@spring.formSingleSelect "bookForm.stillageID", stillages, ""/>
            <br>
            <br>
            Collateral Cost:<@spring.formInput "bookForm.collateralCost" "" "text"/>
            <br>
            <br>
            Cost Per Day:<@spring.formInput "bookForm.costPerDay" "" "text"/>
            <br>
            <br>
            Description:<@spring.formInput "bookForm.description" "" "text"/>
            <br>
            <br>
            <input type="submit" class="stilisation" value="Create"/>
        </form>
    </fieldset>
</div>

</body>
</html>