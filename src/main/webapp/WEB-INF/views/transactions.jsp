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
        document.addEventListener('DOMContentLoaded', () => {
            const btn = document.querySelector('#filter');
            const select = document.querySelector('#name');
            const tbody = document.querySelector('tbody');
            btn.addEventListener('click', (e) => {
                e.preventDefault();
                if (select.value == '') {
                    alert('Заполните все поля');
                    return false;
                }
                fetch(`http://localhost:8080/transaction/filter?id=` + select.value, {
                    method: 'GET'
                })
                .then(response => {
                    return response.json();
                })
                .then(data => {
                    tbody.innerHTML = '';
                    for(let transaction of data) {
                        const tr = document.createElement('tr');
                        const id = document.createElement('td');
                        const type = document.createElement('td');
                        const sum = document.createElement('td');
                        const client = document.createElement('td');
                        const number = document.createElement('td');
                        const exchNumber = document.createElement('td');
                        id.innerText = transaction.id;
                        type.innerText = transaction.type.name;
                        sum.innerText = transaction.sum;
                        client.innerText = transaction.client.name;
                        sum.innerText = transaction.sum;
                        number.innerText = transaction.number;
                        exchNumber.innerText = transaction.exchNumber;
                        tr.append(id);
                        tr.append(type);
                        tr.append(sum);
                        tr.append(client);
                        tr.append(number);
                        if (transaction.number != transaction.exchNumber) {
                            tr.append(exchNumber);
                        }
                        tbody.append(tr);
                    }
                })
            })
        })
    </script>

    <title>Транзакции</title>
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <h4>Фильтр</h4>
    </div>
    <div class="row">
        <form>
            <div class="form-group">
                <label for="name">Имя</label>
                <select class="form-control" id="name" name="name">
                    <c:forEach var="client" items="${clients}">
                        <option value="${client.id}">${client.name}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary" id="filter">Отфильтровать</button>
            <a href="/transaction" class="btn btn-primary">Сбросить</a>
            <a href="/" class="btn btn-primary">На главную</a>
        </form>
    </div>
    <br><br>
    <div class="row">
        <h4>Список транзакций</h4>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Тип</th>
                <th scope="col">Сумма</th>
                <th scope="col">Клиент</th>
                <th scope="col">Номер счёта</th>
                <th scope="col">Номер счёта перевода</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${transactions}" var="transaction">
                <tr>
                    <td><c:out value="${transaction.id}"/></td>
                    <td><c:out value="${transaction.type.name}"/></td>
                    <td><c:out value="${transaction.sum}"/></td>
                    <td><c:out value="${transaction.client.name}"/></td>
                    <td><c:out value="${transaction.number}"/></td>
                    <c:if test="${transaction.type.id == 3}">
                        <td><c:out value="${transaction.exchNumber}"/></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
