<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Post</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>

<%-- Пост--%>
<h3>Тема: ${postDTO.getPost().getTopic()}</h3>
<p>${postDTO.post.getText()}</p>
<h6>Создан пользователем ${postDTO.getUser().getLogin()} в ${postDTO.getPost().getDate()}</h6>

<%-- Комментарии к посту--%>
<c:forEach var="comment" items="${commentList}" >
    <p><h5>${comment.getText()}</h5></p>
    <p>Created by (user_id) : ${comment.getUserId()}</p>
    <%--table border=1>
        <tr>
            <td>${comment.getText()}</td>
        </tr>
    </table--%>
</c:forEach>

<%-- Добавление нового комментария--%>
<form action="/new_comment" method="post">
    <fieldset>
        <legend>Новый Комментарий</legend>
        <input type="hidden" name="post_id" value=${postDTO.getPost().getId()}>
        <input type="hidden" name="user_id" value=${postDTO.getUser().getId()}>
        <input type="text" name="comment_text"/>
        <%--</p><textarea cols="50" rows="20" name="comment_text" autofocus></textarea></p>--%>
        <p><input type="file" name="file" accept=".txt" placeholder="Загрузить файл" ></p>
        <input type="submit" value="Добавить комментарий"></input>
    </fieldset>
    <p>PostID: ${postDTO.getPost().getId()}</p>
    <p>LoginID: ${postDTO.getUser().getLogin()}</p>
</form>

</body>
</html>
