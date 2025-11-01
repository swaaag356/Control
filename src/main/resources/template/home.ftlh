<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title! "Библиотека"}</title>
</head>
<body>
<h1>Библиотека книг</h1>

<#if errormessage??>
    <p>${errormessage}</p>
</#if>

<p>
    <a href="/Control_war_exploded/show">Просмотреть все книги</a>
    <a href="/Control_war_exploded/books/add-book">Добавить новую книгу</a>
</p>
</body>
</html>
