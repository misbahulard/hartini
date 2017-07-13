<%--
  Created by IntelliJ IDEA.
  User: misbahul
  Date: 19/06/17
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin - Item Category</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/semantic/dist/semantic.min.css">
</head>
<style>
    .pushable {
        overflow-x: visible !important;
    }
</style>
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
                <a class="active section">Item Category</a>
            </div>

            <h3 class="ui header">Manage Item Category</h3>

            <a href="/admin/item-category/create" class="ui labeled icon primary button">
                <i class="add circle icon"></i>
                Add Item Category
            </a>

            <table class="ui table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${itemCategoryList}" var="item">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td class="collapsing">
                            <a class="ui vertical animated button positive" tabindex="0"
                               href="/admin/item-category/edit/${item.id}">
                                <div class="hidden content">Edit</div>
                                <div class="visible content">
                                    <i class="edit icon"></i>
                                </div>
                            </a>
                            <a class="ui vertical animated button negative" tabindex="0"
                               href="/admin/item-category/delete/${item.id}">
                                <div class="hidden content">Delete</div>
                                <div class="visible content">
                                    <i class="remove icon"></i>
                                </div>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <th colspan="3">
                        <div class="ui right floated pagination menu">
                            <a class="icon item">
                                <i class="left chevron icon"></i>
                            </a>
                            <c:forEach begin="1" end="${pagination}" varStatus="loop">
                                <a class="item" href="/admin/item/${loop.index}">${loop.index}</a>
                            </c:forEach>
                            <a class="icon item">
                                <i class="right chevron icon"></i>
                            </a>
                        </div>
                    </th>
                </tr>
                </tfoot>
            </table>

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
