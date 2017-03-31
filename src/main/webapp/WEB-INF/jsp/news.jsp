<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/css/main.css" />
    <title>News</title>
    <script>
        function collapsElement(clas) {
            if ( document.querySelector(clas).style.display != "none" ) {
                document.querySelector(clas).style.display = 'none';
            }
            else {
                document.querySelector(clas).style.display = '';
            }
        }
    </script>
</head>
<header>

    <%-- Filter --%>
    <div class="add-article">
        <a class="action" href="javascript:collapsElement('.news-filter')">Фильтровать</a>
        <c:if test="${filtered}">
        <a class="action" href="<c:url value="/"/>">Сбросить фильтр</a>
    </c:if>
    </div>
    <div class="news-filter" <c:if test="${!filtered}">style="display: none"</c:if>>
        <form action="filter" method="get">
            <input type="text" placeholder="по заголовку" name="title">
            <input type="text" placeholder="по содержанию" name="content">
            <select title="по категории" name="category">
                <option selected disabled>Категория</option>
            <c:forEach items="${category}" var="cat">
                <option value="${cat}">${cat.name}</option>
            </c:forEach>
            </select>
            <input type="submit" value="Фильтровать"/>
        </form>
    </div>

    <div class="add-article">
        <a class="header-action" href="<c:url value="add"/>">Добавить новость</a>
    </div>
</header>

<body>

<%-- List of articles --%>
<c:forEach items="${news}" var="one">
    <div class="article">

            <div class="article-title">
                <c:out value="${one.title}"/>
            </div>
            <div class="article-content">
                <c:out value="${one.content.length() > 300 ? one.content.substring(0, 300) : one.content}"/>
                <c:if test="${one.content.length() > 300}">...
                <br>
                <a class="article-link" href="article/${one.id}">Читать полностью</a>
                </c:if>
            </div>

        <div class="article-subtitle">
            <span><c:out value="${one.category.name}"/> | <fmt:formatDate value="${one.datetime}" pattern="HH:mm"/> |
            <a class="action" href="/edit/${one.id}">Редактировать</a> |
            <a class="action" href="/remove/${one.id}">Удалить</a></span>
        </div>
    </div>
</c:forEach>

<%-- Paging --%>
<c:if test="${pageCount > 10}">
    <div class="paging">
        <c:forEach begin="1" end="${pageCount % 10 == 0 ? pageCount / 10 : pageCount / 10 + 1}" var="page">
                <c:choose>
                    <c:when test="${page == pageNum}">
                        <span>${page}</span>
                    </c:when>
                    <c:otherwise>
                        <span><a class="action" href="?page=${page}">${page}</a></span>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
