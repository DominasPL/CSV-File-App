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
    <title>Sorted users</title>
</head>
<body>

    <h1>Users sorted by age</h1>
    <table>
        <thead>
            <tr>
                <th>Users</th>
            </tr>
            <tr>
                <th>#</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Birth date</th>
                <th>Phone number</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user" varStatus="userStatus">
               <tr>
                   <td>${userStatus.count}</td>
                   <td>${user.firstName}</td>
                   <td>${user.lastName}</td>
                   <td>${user.birthDate}</td>
                   <td>${user.phoneNumber}</td>
               </tr>
            </c:forEach>
        </tbody>



    </table>

</body>
</html>
