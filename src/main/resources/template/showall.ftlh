<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Все книги</title>
</head>
<body>
<h1>Все книги</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Автор</th>
        <th>Жанр</th>
        <th>Страниц</th>
        <th>Действие</th>
    </tr>
    <#list books as book>
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.genre!''}</td>
            <td>${book.pages!''}</td>
            <td><a href="/showone?id=${book.id}">Редактировать</a></td>
        </tr>
    </#list>
</table>
<p><a href="/">На главную</a></p>
</body>
</html>