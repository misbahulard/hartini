<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: misbahul
  Date: 12/06/17
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserList</title>
</head>
<body>
<h1><core:out value="${userData.username}"/></h1>
<h1><core:out value="${userData.email}"/></h1>
</body>
</html>
