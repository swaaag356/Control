<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить книгу</title>
</head>
<body>
<h1>Добавить новую книгу</h1>

<#if errormessage??>
    <p>${errormessage}</p>
</#if>

<form method="post" action="/Control_war_exploded/books/add-book">
    <label>Название</label><br>
    <input type="text" name="title" required><br><br>

    <label>Автор</label><br>
    <input type="text" name="author" required><br><br>

    <label>Жанр</label><br>
    <input type="text" name="genre"><br><br>

    <label>Страниц</label><br>
    <input type="number" name="pages" min="1"><br><br>

    <button type="submit">Добавить</button>
</form>
<p><a href="/Control_war_exploded/home">На главную</a></p>
</body>
</html>
