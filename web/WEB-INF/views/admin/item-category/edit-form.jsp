<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: misbahul
  Date: 20/06/17
  Time: 8:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin - Item Category</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/semantic/dist/semantic.min.css">
</head>
<body>

<div class="ui inverted top attached menu">
    <a class="item toggle-sidebar">
        <i class="sidebar icon"></i>
        Hartini
    </a>
    <div class="right menu">
        <a class="item">
            Welcome, ${user.name}
        </a>
        <a class="item" href="/admin/logout">
            <i class="sign out icon"></i>
            Logout
        </a>
    </div>
</div>
<div class="ui bottom attached segment pushable">
    <div class="ui blue inverted vertical sidebar menu">
        <div class="item">
            <a class="ui avatar image" href="/">
                <img src="${pageContext.request.contextPath}/assets/img/admin.jpg">
            </a>
            <a href="/"><b>${user.name}</b></a>
        </div>
        <div class="item">
            <div class="header">Menu</div>
            <div class="menu">
                <a class="item" href="/admin/dashboard">
                    Dashboard
                </a>
                <a class="item" href="/admin/item/1">
                    Item
                </a>
                <a class="item" href="/admin/item-category/1">
                    Item Category
                </a>
            </div>
        </div>
    </div>
    <div class="pusher">
        <div class="ui basic segment">
            <div class="ui breadcrumb">
                <a class="section" href="/admin/dashboard">Home</a>
                <i class="right angle icon divider"></i>
                <a class="section" href="/admin/item-category/1">Item Category</a>
                <i class="right angle icon divider"></i>
                <a class="active section">Update</a>
            </div>

            <h3 class="ui header">Update Item Category</h3>

            <form:form cssClass="ui form" method="post" modelAttribute="itemCategory"
                       action="/admin/item-category/edit">
                <div class="field">
                    <label>Name</label>
                    <form:input path="name" type="text" placeholder="Name" value="${itemCategory.name}"></form:input>
                </div>
                <button class="ui primary button right floated" type="submit">Submit</button>
            </form:form>

        </div>
    </div>
</div>

<script
        src="https://code.jquery.com/jquery-3.1.1.min.js"
        integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/semantic/dist/semantic.min.js"></script>
<script>
    // using context
    $('.toggle-sidebar').click(function () {
        $('.ui.sidebar').sidebar({
            context: $('.bottom.segment'),
            dimPage: false,
            closable: false
        })
            .sidebar('toggle', '.menu .item');
    });

</script>
</body>
</html>
