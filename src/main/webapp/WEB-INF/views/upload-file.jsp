<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/media/css/style.css">
</head>
<body>

    <jsp:include page="elements/header.jsp"/>

    <div class="outPopUp">
        <h1>Add new file</h1>

        <form method="POST" enctype="multipart/form-data">
            <input type="file" name="file"> <br>
            <input type="submit" value="Submit">
        </form>

        <a class="btn btn-primary" href="/" role="button">Go back</a>
    </div>
</body>
</html>
