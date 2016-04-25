<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action='/login' method='post'>
    <input type='text' name='login' placeholder='Login'/>
    <input type='password' name='password' placeholder='Password'/>
    <button type='submit'>Login</button>
    <c:if test="${(not empty message) && ( message != null)}">
        <div>${message}</div>
    </c:if>
</form>
</body>
</html>
