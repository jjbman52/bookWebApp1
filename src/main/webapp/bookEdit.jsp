<%-- 
    Document   : bookEdit
    Created on : Nov 16, 2017, 6:30:28 PM
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
                <h1>Edit Book</h1>
                <table>
                    <tr>
                        <td >${book.bookId}</td>
                        <td>${book.isbn}</td>
                        <td>${book.title}</td>
                        <td>${b.authorId}</td>
                        <td><input type="button" value="Delete" onclick="location.href = 'bc?action=delete&id=${book.bookId}'" /></td>
                    </tr>
                </table>
                <br>
                <form name="form" id="form" method="POST" action="bc?action=save&id=${book.bookId}">
                    <table border="1">
                        <tr>
                            <td>Enter Title:</td> <td><input type="text" value="${book.title}" name="title"></td>
                            <td>Enter isbn</td> <td><input type="text" value="${book.isbn}" name="isbn"></td>
                            <td>Enter Author ID</td> <td><input type="text" value="${book.author.authorId}" name="authorId"></td>
                            <td><input type="submit" name="submit" value="Save"></td>
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