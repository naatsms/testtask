<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/main.css"/>
    <title>Edit article</title>
</head>
<body>
<h2>${title}</h2>
<form:form acceptCharset="UTF-8" method="post" modelAttribute="article" action="${url}">
    <table>
        <tr>
            <td><form:select path="category" items="${category}"/></td>
            <td><form:input size="85" path="title" value="${article.title}"/></td>
        </tr>
        <tr>
            <td colspan="2"><form:textarea cols="100" rows="10" path="content" value="${article.content}"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Сохранить изменения"></td>
        </tr>
    </table>
    <form:hidden path="id" value="${article.id}"/>
</form:form>
</body>
</html>
