<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Post</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>

<form action="/new_post" method="post">
    <fieldset>
        <legend>Создание нового поста</legend>
        <p><label>Заголовок: </label><input type="text" name="post_title" placeholder="Тема Поста" autofocus  ></p>
        </p><textarea cols="20" rows="20" name="post_text"></textarea></p>
        <p><input type="file" name="file" accept=".txt" placeholder="Загрузить файл" ></p>
        <input type="submit" value="Добавить пост"></input>
    </fieldset>

    <c:if test="${(not empty login) && ( login != null)}">
        <div>Вход выполнено под пользователем ${login}</div>
    </c:if>

</form>
</body>
</html>
