<%-- 
    Document   : bookList
    Created on : Nov 16, 2017, 3:46:51 PM
    Author     : jordanrehbein
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
        <title>Book List</title>
    </head>
    <div id="backround">
        <body>
            <div class="body">
                <h1>Book List</h1>
                <table border="1">
                    <c:forEach var = "b" items="${bookList}">
                        <tr>
                            <td>${b.bookId}</td>
                            <td>${b.isbn}</td>
                            <td>${b.title}</td>
                            <td>${b.author.authorName}</td>
                            <td><input type="button" value="Edit" onclick="location.href = 'bc?action=editDelete&id=${b.bookId}'" /></td>
                        </tr>
                    </c:forEach>
                </table>
                <input type="button" value="Add Book" onclick="location.href = 'bc?action=add'" />
                <jsp:include page="footer.jsp" />
            </div>
        </body>
    </div>
</html>
