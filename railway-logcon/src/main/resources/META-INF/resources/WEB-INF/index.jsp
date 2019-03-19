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
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>
        <script type="text/javascript">
            $(function() {
                $(document).ready(function() {
                    $.getJSON("http://localhost:8080/api/", function(data) {
                            t(data);
                        }
                    );
                    return false;
                });
            });

            var t = function(data) {
                $.each(data, function(i, data) {
                    var div_data =
                    "<div >" + data.code + data.esr + "</a></div>";
                    $(div_data).appendTo("#9lessonsLinks");
                });
            }
        </script>

        <script type="text/javascript">
            var paper = function() {
                return Raphael(10, 50, 1000, 1000);
            };

            var line = function(x, y, l, angle, paper) {
                var c = paper.rect(x, y, 3, l).transform(angle);
                c.attr({
                    fill: "45-#000-#ccc:20-#000:50-#ccc:80-#000"
                });
            }
            window.onload = function() {

                var mainPaper = paper();
                line(200, 120, 400, "r45", mainPaper);
                line(200, 120, 400, "r15", mainPaper);
                line(200, 120, 400, "r95", mainPaper);

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
        <div id="9lessonsLinks"></div>
    </body>
</html>