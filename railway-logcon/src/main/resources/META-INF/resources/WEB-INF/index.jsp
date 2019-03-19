<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>DSTU railway</title>
        <script type="text/javascript" src="/resources/js/raphael.min.js"></script>
        <script type="text/javascript">
            window.onload = function() {
                var ph = document.getElementById("placeholder");
                var paper = Raphael(10, 50, 1000, 1000);
                var c = paper.rect(200, 120, 600, 400);
                c.attr({
                    fill: "45-#000-#ccc:20-#000:50-#ccc:80-#000"
                });
            }
        </script>
    </head>

    <body>
        <h1> Логический контроль </h1>

        <form action="control/" method=POST>
            <input  type = "submit" name = "submit" value = "Изменить сосотояние объекта" >
        </form>
        <form action="statistic/" method=POST>
            <input  type = "submit" name = "submit" value = "Общая статистика" >
        </form>
        <div id="placeholder"></div>
    </body>
</html>