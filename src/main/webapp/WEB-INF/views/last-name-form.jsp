<%--
  Created by IntelliJ IDEA.
  User: dominik
  Date: 10/27/19
  Time: 7:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User by last name</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/media/css/style.css">
</head>
<body>

    <jsp:include page="elements/header.jsp"/>

    <div class="outPopUp">

        <h1>Please provide user last name: </h1>
        <form method="POST">
            <input type="text" name="last-name" placeholder="Please give a last name">
            <input type="submit" value="Submit">
        </form>

        <a class="btn btn-primary" href="/" role="button">Go back</a>
    </div>
</body>
</html>
