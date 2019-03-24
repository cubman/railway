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
                    drawPolygon();
                    setInterval(function() {
                        drawMessage();
                        drawPolygon();
                    }, 1000);
                });

                var drawMessage = function() {
                    var messageNotExists = document.getElementById('mes') == null;
                    $("#info").html("");
                    $.getJSON("http://localhost:8080/api/message/", function(messages) {
                        var div_data = "<ul id=info>";
                        if (messageNotExists) {
                            div_data = "<div class=prokrutka id=mes>" + div_data;
                        }

                        $.each(messages, function(i, message) {
                            div_data = div_data +
                            "<li>" +
                                "( " +
                                    message.messageLevel + " | " +
                                    message.code + " )" +
                                    message.description +
                            "</li>";
                        });

                        div_data = div_data + "</ul>";

                        if (messageNotExists) {
                            div_data = div_data + "</div>"

                            $(div_data).appendTo("#message");
                        } else {
                            $(div_data).appendTo("#info");
                        }
                    });
                };

                var drawPolygon = function() {
                    $.getJSON("http://localhost:8080/api/area/", function(areas) {
                            drawArea(areas);
                        }
                    );
                };

                var drawArea = function(areas) {
                    var h = 600;
                    var w = 1200;

                    var mainPaper = paper(w, h);
                    mainPaper.rect(0, 0, w, h).attr("fill", "#CAC8C8");


                    $.each(areas, function(i, area) {
                        <!--var div_data =-->
                        <!-- "<div >" + area.code + area.esr + "</div>";-->
                        <!--$(div_data).appendTo("#json"); -->

                        $.each(area.elements, function(i, element) {
                            <!--var div_data =
                            <!--"<div >" + element.code + " || " + element.type + "</div>";
                            <!--$(div_data).appendTo("#json");
                            var moveX = -380;
                            var moveY = -250;
                            $.each(element.figures, function(i, figure) {
                            <!--var div_data =
                            <!--"<div >(" + figure.x1 + ", " + figure.y1 + ", " + figure.x2 + ", " + figure.y2 + ")</div>";
                            <!--$(div_data).appendTo("#json");
                            if (figure.type == "line") {
                                drawLine(figure.x1 + moveX, figure.y1 + moveY, figure.x2 + moveX, figure.y2 + moveY, figure.color, figure.width,
                                <!--element.code + "(" + figure.x1 + ", " + figure.y1 + ", " + figure.x2 + ", " + figure.y2 + ")",
                                figure.id + " " + element.code,
                                mainPaper);
                            } else if (figure.type == "circle") {
                                drawCircle(figure.x + moveX, figure.y + moveY, figure.r, figure.color, figure.width, figure.id + " " + element.code, mainPaper);
                            } else if (figure.type == "label") {
                                drawLabel(figure.x + moveX, figure.y + moveY, figure.description, figure.color, figure.width, figure.id + " " + element.code, mainPaper);
                            } else if (figure.type == "mrLabel") {
                                drawMrLabel(figure.x + moveX, figure.y + moveY, figure.description, figure.textColor, figure.borderColor, figure.width, figure.id + " " + element.code, mainPaper);
                            }
                        });
                        });

                    });
                }
            </script>

            <script type="text/javascript">
                function randColor() {
                    var r = Math.floor(Math.random() * (256)),
                        g = Math.floor(Math.random() * (256)),
                        b = Math.floor(Math.random() * (256));
                    return '#' + r.toString(16) + g.toString(16) + b.toString(16);
                }

                var paper = function(width, height) {
                    $("#placeholder").html("");
                    return Raphael(document.getElementById('placeholder'), width, height);
                };

                var drawLine = function(x1, y1, x2, y2, color, width, text, paper) {

                    var c = paper.path("M" + x1 + "," + y1 + "L" + x2 + "," + y2);

                    c.attr({
                        "stroke": color,
                        title: text,
                        "stroke-width": width
                    });

                }

                var drawCircle = function(x, y, r, color, width, text, paper) {

                    var c = paper.circle(x, y, r);

                    c.attr({
                        "stroke": "#000",
                        title: text,
                        fill: color,
                        "stroke-width": width
                    });

                }

                 var drawLabel = function(x, y, description, color, width, text, paper) {

                    var c = paper.text(x, y, description);

                    c.attr({
                        "stroke": "#000",
                        title: text,
                        fill: color,
                        "font-size": width
                    });

                }

                var drawMrLabelText = function(x, y, description, textColor, width, text, paper) {
                    var contactText = paper.text(x, y, description);
                    contactText.attr({
                        "stroke": textColor,
                        title: text,
                        fill: textColor,
                        "font-size": width
                    });

                    return contactText;
                }

                var drawMrLabel = function(x, y, description, textColor, borderColor, width, text, paper) {

                    var contactText = drawMrLabelText(x, y, description, textColor, width, text, paper);

                    var dimensions = contactText.getBBox();
                    var contactRect = paper.rect(x - dimensions.width / 2, y - dimensions.height / 2, dimensions.width, dimensions.height);
                    contactRect.attr({
                        "stroke": borderColor,
                        fill: borderColor,
                        title: text
                    });

                    drawMrLabelText(x, y, description, textColor, width, text, paper);
                }

            </script>
            <style>
                .prokrutka {
                    height: 50%; /* высота нашего блока */
                    width: 100%; /* ширина нашего блока */
                    background: #fff; /* цвет фона, белый */
                    border: 1px solid #C1C1C1; /* размер и цвет границы блока */
                    overflow: auto;
                }
            </style>
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
        <div id="message">
        </div>
    </body>
</html>