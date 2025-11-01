<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактировать книгу</title>
</head>
<body>
<h1>Редактировать книгу</h1>
<form method="post" action="/showone">
    <input type="hidden" name="id" value="${book.id}">

    <label>Название</label><br>
    <input type="text" name="title" value="${book.title}" required><br><br>

    <label>Автор</label><br>
    <input type="text" name="author" value="${book.author}" required><br><br>

    <label>Жанр</label><br>
    <input type="text" name="genre" value="${book.genre!''}"><br><br>

    <label>Страниц</label><br>
    <input type="number" name="pages" value="${book.pages!''}" min="1"><br><br>

    <button type="submit">Сохранить</button>
</form>
<p><a href="/show">Назад к списку</a></p>
</body>
</html>