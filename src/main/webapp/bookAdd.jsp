<%-- 
    Document   : bookAdd
    Created on : Nov 16, 2017, 3:42:47 PM
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
        <title>Add Author</title>
    </head>
    <div id="backround">
        <body>
            <div class="body">
                <h1>Add Book</h1>
                <table border="1">
                    <c:forEach var = "b" items="${bookList}">
                        <tr>
                            <td>${b.bookId}</td>
                            <td>${b.title}</td>
                            <td>${b.isbn}</td>
                            <td>${b.author.authorId}</td>
                            <td><input type="button" value="Edit" onclick="location.href = 'bc?action=editDelete&id=${b.bookId}'" /></td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
                <form name="form1" id="form1" method="POST" action="bc?action=save">
                    <table border="1">
                        <tr>
                            <td>Enter Title:</td> <td><input type="text" value="" name="title"></td>
                            <td>Enter isbn</td> <td><input type="text" value="" name="isbn"></td>
                            <td>Enter Author ID</td> <td><input type="text" value="" name="authorId"></td>
                            <td><input type="submit" name="submit" value="Add"></td>
                        </tr>
                    </table>
                </form>
                <br>
                <input type="button" value="Back" onclick="location.href = 'bc?action=list'" />
                <jsp:include page="footer.jsp" />
            </div>
        </body>
    </div>
</html>

