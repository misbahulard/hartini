<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: misbahul
  Date: 21/06/17
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hartini Elektronik</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/semantic/dist/semantic.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>

<div class="ui yellow inverted top attached menu">
    <a class="active item" href="/">
        Hartini
    </a>
    <div class="ui dropdown item">
        Product
        <div class="menu">
            <c:forEach items="${category}" var="item">
                <a class="item" href="/product/category/${item.id}">
                        ${item.name}
                </a>
            </c:forEach>
        </div>
    </div>
    <div class="right menu">
        <c:set var="logged_in" value="${userData.id != null}" scope="page"/>
        <c:set var="cart_check" value="${cart.id.size() == null || cart.id.size() == 0}" scope="page"/>
        <c:if test="${logged_in}">
            <c:choose>
                <c:when test="${cart_check}">
                    <a class="ui item" href="#">
                        <i class="cart icon"></i>
                        Cart: ${cart.id.size()}
                    </a>
                </c:when>
                <c:when test="${!cart_check}">
                    <a class="ui item" href="/checkout">
                        <i class="cart icon"></i>
                        Cart: ${cart.id.size()}
                    </a>
                </c:when>
            </c:choose>
        </c:if>
        <c:if test="${!logged_in}">
            <a class="ui item" href="/login">
                Login
            </a>
        </c:if>
        <c:if test="${logged_in}">
            <div class="ui dropdown item">
                <i class="user icon"></i>
                    ${userData.username}
                <div class="menu">
                    <a class="item" href="/logout">
                        Logout
                    </a>
                </div>
            </div>
        </c:if>
    </div>
</div>

<div class="ui container">
    <div class="ui vertical stripe segment" style="min-height: 480px">

        <h2 class="ui header">Product Detail</h2>
        <div class="ui divider"></div>
        <div class="ui blue segment">
            <div class="ui grid">
                <div class="four wide column">
                    <img class="ui image medium" src="${pageContext.request.contextPath}/assets/img/item/${item.image}"
                         alt="${item.name}">
                </div>
                <div class="six wide column">
                    <h3 class="ui header">
                        ${item.name}
                        <small>(${item.itemCategoryByCategoryId.name})</small>
                    </h3>
                    <small>Price:</small>
                    <h2 class="ui header">
                        Rp.<fmt:formatNumber currencyCode="id_ID" value="${item.price}"/>,-
                    </h2>
                    <c:if test="${!logged_in}">
                        <a class="ui disabled labeled icon button primary" href="/product/buy/${item.id}">
                            <i class="cart icon"></i>
                            Add to cart
                        </a>
                        <h4>Log in to buy this product</h4>
                        <a class="ui right labeled icon button yellow" href="/login">
                            <i class="right arrow icon"></i>
                            Login
                        </a>
                    </c:if>
                    <c:if test="${logged_in}">
                        <a class="ui labeled icon button primary" href="/product/buy/${item.id}">
                            <i class="cart icon"></i>
                            Add to cart
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="ui inverted vertical footer segment">
    <div class="ui container">
        <div class="ui stackable inverted divided equal height stackable grid">
            <div class="three wide column">
                <h4 class="ui inverted header">INFO</h4>
                <div class="ui inverted link list">
                    <a href="#" class="item">Jl Soekarno-Hatta No 11 Surabaya</a>
                </div>
            </div>
            <div class="three wide column">
                <h4 class="ui inverted header">Social Media</h4>
                <div class="ui inverted link list">
                    <a href="#" class="item">
                        <i class="facebook icon"></i>
                        Facebook
                    </a>
                    <a href="#" class="item">
                        <i class="twitter icon"></i>
                        Twitter
                    </a>
                    <a href="#" class="item">
                        <i class="instagram icon"></i>
                        Instagram
                    </a>
                </div>
            </div>
            <div class="seven wide column">

            </div>
        </div>
    </div>
</div>

<script
        src="https://code.jquery.com/jquery-3.1.1.min.js"
        integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/semantic/dist/semantic.min.js"></script>
<script>
    $('.ui.dropdown')
        .dropdown()
    ;
</script>
</body>
</html>
