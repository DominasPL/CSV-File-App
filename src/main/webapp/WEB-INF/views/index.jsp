<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 10/26/19
  Time: 10:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

    <h1>This is main page!</h1>
    <div class="btn-group" role="group" aria-label="Basic example">
        <a class="btn btn-success" href="/users/upload-file" role="button">Upload file</a>
        <a class="btn btn-success" href="/users/number-of-users" role="button">Number of users</a>
        <a class="btn btn-success" href="/users/users-sorted-by-age" role="button">Users sorted by age</a>
        <a class="btn btn-success" href="/users/oldest-user-with-phone" role="button">The oldest user with phone number</a>
        <a class="btn btn-success" href="/users/user-data-by-last-name" role="button">Find user by name</a>
    </div>


</body>
</html>
