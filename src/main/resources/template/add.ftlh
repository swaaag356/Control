<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить книгу</title>
</head>
<body>
<h1>Добавить новую книгу</h1>
<form method="post" action="/add">
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
<p><a href="/">На главную</a></p>
</body>
</html>