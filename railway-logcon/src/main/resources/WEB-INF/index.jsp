<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>DSTU kafka test</title>
    </head>

    <style>
        .center {
            text-align: center;
            font-size: x-large;
        }
    </style>

    <body>
        <h1> Hello world! </h1>
        <div class="center"> ${msg}!! </div>
    </body>

    <form action="producer/">
        <input  type = "submit" name = "submit" value = "Производитель" >
    </form>
    <br>
    <form action="consumer/add">
        <input  type = "submit" name = "submit" value = "Потребитель" >
    </form>
</html>