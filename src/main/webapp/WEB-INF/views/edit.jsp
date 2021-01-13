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
            const type = document.querySelector('#type');
            type.addEventListener('change', (e) => {
                const divs = document.querySelectorAll('.form-group');
                if (e.target.value == 3) {
                    divs[4].innerHTML = '<label for="exchNumber">Номер счёта</label>' +
                        '<input type="text" class="form-control" id="exchNumber" name="exchNumber" placeholder="Введите номер счёта">';
                } else {
                    divs[4].innerHTML = '<input type="hidden" class="form-control" id="exchNumber" name="exchNumber" value="${number}">';
                }
            });
        });

        function validate() {
            const type = document.querySelector('#type');
            const sum = document.querySelector('#sum');
            const exchNumber = document.querySelector('#exchNumber');
            if (type.value == '' || sum.value == '' || exchNumber.value == '') {
                alert('Заполните все поля');
                return false;
            }
        }
    </script>

    <title>Операции со счётом</title>
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <h4>Операции со счётом</h4>
    </div>
    <c:if test="${not empty errNoMoney}">
        <div class="row">
            <h6>${errNoMoney}</h6>
        </div>
    </c:if>
    <c:if test="${not empty errNoAcc}">
        <div class="row">
            <h6>${errNoAcc}</h6>
        </div>
    </c:if>
    <div class="row">
        <form action="/transaction" method="post" onsubmit="return validate()">
            <div class="form-group">
                <label for="type">Тип операции</label>
                <select class="form-control" id="type" name="type.id">
                    <c:forEach var="type" items="${types}">
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="sum">Сумма</label>
                <input type="number" class="form-control" id="sum" name="sum" placeholder="Укажите сумму">
            </div>
            <div class="form-group">
                <input type="hidden" class="form-control" id="clientId" name="client.id" value="${clientId}">
            </div>
            <div class="form-group">
                <input type="hidden" class="form-control" id="number" name="number" value="${number}">
            </div>
            <div class="form-group">
                <input type="hidden" class="form-control" id="exchNumber" name="exchNumber" value="${number}">
            </div>
            <button type="submit" class="btn btn-primary">Подтвердить</button>
        </form>
    </div>
</div>

</body>
</html>
