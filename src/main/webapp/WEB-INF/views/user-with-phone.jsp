<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 10/27/19
  Time: 5:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/media/css/style.css">

</head>
<body>
<jsp:include page="elements/header.jsp"/>

    <div class="outPopUp">
        <h1>The oldest user with phone number</h1>
        <table>
            <thead>
            <tr>
                <th class="th-center" colspan="5">User</th>
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

        <a class="btn btn-primary" href="/" role="button">Go back</a>
    </div>

</body>
</html>
