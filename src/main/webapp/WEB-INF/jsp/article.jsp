<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/css/main.css" />
    <title>${article.title}</title>
</head>
<header>

    <div class="add-article">
        <a class="action" href="/">Вернуться к списку новостей</a>
    </div>

</header>
<body>
<div class="article">
        <div class="article-title">
            ${article.title}
        </div>
        <div class="article-content">
            ${article.content}
        </div>
    <div class="article-subtitle">
            <span>${article.category.name} | <fmt:formatDate value="${article.datetime}" pattern="HH:mm"/> |
            <a class="action" href="/edit/${article.id}">Редактировать</a> |
            <a class="action" href="/remove/${article.id}">Удалить</a></span>
    </div>
</div>
</body>
</html>