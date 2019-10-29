<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 10/26/19
  Time: 11:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sorted users by age</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/media/css/style.css">

</head>
<body>

    <c:choose>
        <c:when test="${users.totalPages > 0}">
            <h1>Sorted users by age</h1>
            <table>
                <thead>
                <tr>
                    <th class="th-center" colspan="6">Users</th>
                </tr>
                <tr>
                    <th>Id</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Age</th>
                    <th>Phone number</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users.content}" var="user">
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.age}</td>
                        <td>${user.phoneNumber}</td>
                        <td><a class="btn btn-danger" href="/users/users-sorted-by-age/delete/${user.userId}" role="button">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:forEach var="i" begin="0" end="${users.totalPages - 1}">
                        <li class="page-item"><a class="page-link" href="/users/users-sorted-by-age?page=${i}">${i}</a></li>
                    </c:forEach>
                </ul>
            </nav>
            <a class="btn btn-danger" href="/users/users-sorted-by-age/delete-users" role="button">Delete all users</a>
        </c:when>
        <c:otherwise>
            <h2>Database is empty!</h2>
        </c:otherwise>
    </c:choose>

    <a class="btn btn-primary" href="/" role="button">Go back</a>


</body>
</html>
