<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ошибка</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="container">
    <div class="error-box">
        <h1>Произошла ошибка</h1>
        <p>${error}</p>
        <a href="${pageContext.request.contextPath}/tours" class="btn">Вернуться к турам</a>
    </div>
</div>
</body>
</html>