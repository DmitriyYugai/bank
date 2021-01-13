<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script>
        function validate() {
            if (document.querySelector('#number').value == '') {
                alert('Заполните все поля');
                return false;
            }
        }
    </script>

    <title>Счета</title>
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <h4>Список счетов</h4>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Номер</th>
                <th scope="col">Сумма денег</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${accounts}" var="account">
                    <tr>
                        <td>
                            <a href="/edit?clientId=${account.clientId}&number=${account.number}"><c:out value="${account.number}"/></a>
                        </td>
                        <td><c:out value="${account.sum}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <br><br><br>
    <div class="row">
        <h4>Добавление счёта</h4>
    </div>
    <c:if test="${not empty errorMessage}">
        <div class="row">
            <h6>${errorMessage}</h6>
        </div>
    </c:if>
    <div class="row">
        <form action="/account" method="post" onsubmit="return validate()">
            <div class="form-group">
                <label for="number">Номер</label>
                <input type="number" class="form-control" id="number" name="number" placeholder="Введите номер">
            </div>
            <div class="form-group">
                <input type="hidden" class="form-control" id="clientId" name="clientId" value="${clientId}">
            </div>
            <button type="submit" class="btn btn-primary">Добавить</button>
            <a href="/" class="btn btn-primary">На главную</a>
        </form>
    </div>
</div>

</body>
</html>
