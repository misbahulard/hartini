<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: misbahul
  Date: 20/06/17
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signup</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/semantic/dist/semantic.min.css">

    <style type="text/css">
        body {
            background: linear-gradient(to bottom, rgba(250,238,200,1) 0%, rgba(255,248,107,1) 100%);
        }

        body > .grid {
            height: 100%;
        }

        .image {
            margin-top: -100px;
        }

        .column {
            max-width: 450px;
        }
    </style>
</head>
<body>
<div class="ui middle aligned center aligned grid">
    <div class="column">
        <h2 class="ui yellow image header">
            <div class="content">
                Sign Up
            </div>
        </h2>
        <form:form cssClass="ui large form" method="post" modelAttribute="user" action="/signup">
            <div class="ui stacked segment">
                <div class="field">
                    <div class="ui left icon input">
                        <i class="user icon"></i>
                        <form:input path="username" type="text" placeholder="Username"></form:input>
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <form:input path="password" type="password" placeholder="Password"></form:input>
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="user icon"></i>
                        <form:input path="name" type="text" placeholder="Name"></form:input>
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="mail icon"></i>
                        <form:input path="email" type="email" placeholder="Username"></form:input>
                    </div>
                </div>
                <form:hidden path="roleId" value="2"></form:hidden>
                <button type="submit" class="ui fluid large yellow submit button">Login</button>
            </div>
        </form:form>
    </div>
</div>

<script
        src="https://code.jquery.com/jquery-3.1.1.min.js"
        integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/semantic/dist/semantic.min.js"></script>
</body>
</html>
