<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Posts</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>


<form action="/new_post" enctype="multipart/form-data" method="post">
    <fieldset>
        <legend>Создание нового поста</legend>
        <p><label>Заголовок: </label><input type="text" name="post_title" placeholder="Тема Поста" autofocus  ></p>
        </p><textarea cols="20" rows="20" name="post_text"></textarea></p>
        <p><input type="file" name="attachedFile" accept=".txt" placeholder="Загрузить файл" ></p>
        <input type="submit" value="Добавить пост"></input>
    </fieldset>

    <c:if test="${(not empty login) && ( login != null)}">
        <div>Вход выполнено под пользователем ${login}</div>
    </c:if>
    <c:if test="${(not empty message) && ( message != null)}">
        <div>${message}</div>
    </c:if>

    <table border="1">
        <th>Post Name</th><th>Автор</th><th>Дата создания</th>
        <c:forEach var="postDTO" items="${postDTOList}" >
            <tr>
                <td><a href="/post/${postDTO.getPost().getId()}">${postDTO.getPost().getTopic()}</a></td>
                <td>${postDTO.getUser().getLogin()}</td>
                <td>${postDTO.getPost().getDate()}</td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
