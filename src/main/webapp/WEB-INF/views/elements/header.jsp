<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 10/29/19
  Time: 4:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/media/css/style.css">
</head>
<body>
<header>
    <div class="main">
        <div class="logo">
            <img src="/media/images/csv-logo.png" alt="csv-logo">
        </div>
    </div>
    <ul>
        <li><a class="btn btn-success" href="/users/upload-file" role="button">Upload file</a></li>
        <li><a class="btn btn-success" href="/users/number-of-users" role="button">Number of users</a></li>
        <li><a class="btn btn-success" href="/users/users-sorted-by-age" role="button">Users sorted by age</a></li>
        <li><a class="btn btn-success" href="/users/oldest-user-with-phone" role="button">The oldest user with phone number</a></li>
        <li><a class="btn btn-success" href="/users/user-data-by-last-name" role="button">Find user by name</a></li>
    </ul>
</header>

</body>
</html>
