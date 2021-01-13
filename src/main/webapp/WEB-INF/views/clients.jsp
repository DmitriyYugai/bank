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
            if (document.querySelector('#name').value == '') {
                alert('Заполните все поля');
                return false;
            }
        }
    </script>

    <title>Клиенты</title>
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <h4>Список клиентов</h4>
    </div>
    <div class="row">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Имя</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${clients}" var="client">
                <tr>
                    <td><c:out value="${client.id}"/></td>
                    <td>
                        <a href="/account?id=${client.id}"><c:out value="${client.name}"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <br><br>
    <div class="row">
        <h4>Добавление клиента</h4>
    </div>
    <div class="row">
        <form action="/client" method="post" onsubmit="return validate()">
            <div class="form-group">
                <label for="name">Имя</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="Введите имя">
            </div>
            <button type="submit" class="btn btn-primary">Добавить</button>
            <a href="/transaction" class="btn btn-primary">Список транзакций</a>
        </form>
    </div>
</div>

</body>
</html>