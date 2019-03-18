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
        <meta http-equiv="refresh" content="3" />
    </head>

    <style>
        TABLE {
            background: #dc0; /* Цвет фона таблицы */
            border: 5px double #000; /* Рамка вокруг таблицы */
           }
           TD, TH {
            padding: 5px; /* Поля вокруг текста */
            border: 1px solid #fff; /* Рамка вокруг ячеек */
           }
    </style>

    <body>
        <h1> Статистика </h1>

        <h3> Сводная информация </h1>
        <div>
            <div style="width: 50%; float:left;">
               <ul style="background: #ccc;">
                <c:forEach items="${infoMessages}" var="message">
                     <li>${message}</li>
                 </c:forEach>
               </ul>
            </div>

            <div style="width: 50%; float:left;">
               <ul style="background: red;">
                 <c:forEach items="${errorMessages}" var="message">
                     <li>${message}</li>
                 </c:forEach>
               </ul>
            </div>
        </div>

        <h3 style="clear:left;"> Общая статистика по элементам </h1>

        <div id="statistic">
            <c:forEach items="${areas}" var="area">
                <h3>code: ${area.areaCode} | esr: ${area.getEsr()}</h2>
                <table width="100%">
                    <tr>
                        <th>type</th>
                        <th>code</th>
                        <th>state</th>
                        <th>last updated</th>
                    </tr>
                    <c:forEach items="${area.getElements().values()}" var="element">
                        <tr>
                            <td>${element.getClass().getSimpleName()}</td>
                            <td>${element.getElementCode()}</td>
                            <td>${element.getState().getState()}</td>
                            <td>${element.getState().getLastChange()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:forEach>
        </div>
    </body>
</html>