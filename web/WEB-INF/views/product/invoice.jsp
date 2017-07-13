<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: misbahul
  Date: 21/06/17
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hartini Elektronik</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/semantic/dist/semantic.min.css">
</head>
<body>

<div class="ui container">
    <div class="ui vertical stripe segment" style="min-height: 480px">

        <h2 class="ui header">Invoice | Hartini Elektonik</h2>
        <p>Id pembelian: ${order.id}</p>
        <p>Name user: ${order.userByUserId.name}</p>
        <p>Tanggal pembelian: ${order.date}</p>

        <table class="ui table">
            <thead>
            <tr>
                <th>Nama</th>
                <th>Kategori</th>
                <th>Harga</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${itemList}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.itemCategoryByCategoryId.name}</td>
                    <td>Rp.<fmt:formatNumber currencyCode="id_ID" value="${item.price}"/></td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td>Rp.<fmt:formatNumber currencyCode="id_ID" value="${totalHarga}"/></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<script
        src="https://code.jquery.com/jquery-3.1.1.min.js"
        integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/semantic/dist/semantic.min.js"></script>
<script>
    $(document).ready(
        window.print()
    );
</script>
</body>
</html>
