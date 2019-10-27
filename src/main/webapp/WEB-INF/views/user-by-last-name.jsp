<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 10/27/19
  Time: 8:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User by last name</title>
</head>
<body>

    <h1>Founded user</h1>
    <table>
        <thead>
        <tr>
            <th>Users</th>
        </tr>
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Age</th>
            <th>Phone number</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.phoneNumber}</td>
                <td><a href="/users/users-sorted-by-age/delete/${user.userId}">delete</a></td>
            </tr>
        </tbody>
    </table>


</body>
</html>
