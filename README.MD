# Проект - Банк
# Содержание
* [Использованные технологии](#Использованные-технологии)
* [Сборка](#Сборка)
* [Использование](#Использование)
* [Контакты](#Контакты)
## Использованные технологии
* Spring Boot
* Spring Core (DI, IoC)
* Spring MVC
* Spring JDBC
* PostgreSQL
* Tomcat
* Collections Framework
* Apache Commons DBCP (Database Connection Pooling)
* JSP
* JSTL
* HTML, Bootstrap
* JS
## Сборка
1. Установить JDK 14.
2. Скачать Maven.
3. Создать переменные окружения JAVA_HOME и M2_HOME и прописать в переменную окружения Path 
пути до бинарных файлов для JDK и для Maven:<br><br>
![Java Maven installing](img/build/java_maven.PNG)<br><br>
4. Установить сервер БД PostgreSQL. При установке задать:<br>
    * имя пользователя - postgres 
    * пароль - root
    * порт - 5432<br>
Также при установке сервера БД PostgreSQL нужно установить программу pg_Admin.

5. Скачать исходный код проекта с GitHub.
6. Перейти в корень проекта, где лежит файл pom.xml:<br><br>
![Переход в корень проекта 1](img/build/cd.PNG)<br><br>
![Переход в корень проекта 2](img/build/cd1.PNG)<br><br>
7. С помощью Maven осуществить сборку проекта:<br><br>
![Сборка проекта](img/build/package.PNG)<br><br>
При успешной сборке должна появиться папка target, в которой располагается war-архив:<br><br>
![Сборка прошла успешно](img/build/package1.PNG)<br><br>
![Папка target](img/build/target.PNG)<br><br>
![war-архив](img/build/target1.PNG)<br><br>
8. Запустить сервер БД, указав путь до папки data:<br><br>
![Запуск сервера БД](img/build/server_start.PNG)<br><br>
9. Запустить программу pg_Admin, установленную вместе с сервером БД PostgreSQL:<br><br>
![Запуск pg_Admin](img/build/pg_admin.PNG)<br><br>
10. Создать БД с названием bank:<br><br>
![БД forum](img/build/bank_db.PNG)<br><br>
11. Открыть Query Tool для созданной БД и запустить SQL-скрипт create.sql, 
находящийся в папке src/main/resources/db (папка src находится в корне скачанного проекта):<br><br>
![Скрипт create.sql](img/build/script.PNG)<br><br>
![Запуск скрипта security.schema.reg.sql](img/build/script1.PNG)<br><br>
Сборка завершена. Теперь можно проверить работу приложения.
## Использование
Переходим в папку target, где располагается собранный war-архив и запускаем Spring boot приложение:<br><br>
![Запуск приложения 1](img/usage/start.PNG)<br><br>
![Запуск приложения 2](img/usage/start1.PNG)<br><br>
Переходим на главную страницу приложения 
[http://localhost:8080/](http://localhost:8080/): <br><br>
![Главная страница](img/usage/main.PNG)<br><br>
Основные экраны приложения:<br><br>
![Экран 1](img/usage/screen1.PNG)<br><br>
![Экран 2](img/usage/screen2.PNG)<br><br>
![Экран 3](img/usage/screen3.PNG)<br><br>
![Экран 4](img/usage/screen4.PNG)<br><br>
## Контакты
Почта: dmitry_yugay@mail.ru<br>
Skype: live:.cid.9cdc925fb48220b4