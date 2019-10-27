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
    <title>The oldest users</title>
</head>
<body>

    <h1>The oldest users</h1>
    <table>
        <thead>
            <tr>
                <th>Users</th>
            </tr>
            <tr>
                <th>#</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Age</th>
                <th>Phone number</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user" varStatus="userStatus">
               <tr>
                   <td>${userStatus.count}</td>
                   <td>${user.firstName}</td>
                   <td>${user.lastName}</td>
                   <td>${user.age}</td>
                   <td>${user.phoneNumber}</td>
                   <td><a href="/users/users-sorted-by-age/delete/${user.userId}">delete</a></td>
               </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
