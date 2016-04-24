<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<form action='/signup' method='post'>
    <!--input type='number' name='user_id' placeholder='user_id'/><br/-->
    <input type='text' name='login' placeholder='Login'/><br/>
    <input type='password' name='password' placeholder='Password'/><br/>
    <input type='text' name='username' placeholder='Username'/><br/>
    <button type='submit'>Signup</button>
    <c:if test="${(not empty message) && ( message != null)}">
        <div>${message}</div>
    </c:if>
</form>
</body>
</html>
