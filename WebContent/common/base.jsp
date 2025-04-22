<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title><c:out value="${param.title}" /></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      font-family: sans-serif;
      margin: 0;
      background-color: #f5f5f5;
    }

    header {
      background-color: #e9f0fc;
      padding: 25px 30px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    header h1 {
      font-size: 32px;
      margin: 0;
    }

    .user-info {
      font-size: 14px;
    }

    nav {
      background-color: white;
      width: 200px;
      min-height: 100vh;
      padding: 20px;
      box-sizing: border-box;
      border-right: 1px solid #ccc;
    }

    nav a {
      display: block;
      margin: 10px 0;
      color: #0073e6;
      text-decoration: none;
    }

    nav a[href="#"] {
            color: #000;
            font-weight: normal;
            pointer-events: none;
        }

    .main {
      flex: 1;
      padding: 30px 0;
    }

    .wrapper {
      display: flex;
    }

    footer {
      text-align: center;
      font-size: 12px;
      color: #aaa;
      margin-top: 40px;
      clear: both;
    }
  </style>
</head>
<body>

<header>
  <h1>得点管理システム</h1>
  <div class="user-info">
    <c:choose>
            <c:when test="${not empty sessionScope.userName}">
                ${sessionScope.userName} 様　
                <a href="../scoremanager.main/LogoutAction">ログアウト</a>
            </c:when>
            <c:otherwise>
                <a href="../common/login-in.jsp">ログイン</a>
            </c:otherwise>
        </c:choose>
    </div>
</header>

<div class="wrapper">
  <!-- サイドバー -->
  <%@ include file="menu.jsp" %>

  <!-- メイン -->
  <div class="main container-fluid">
    <c:out value="${param.content}" escapeXml="false" />
  </div>
</div>

<footer>
  <p>&copy; 2023 TIC</p>
  <p>大原学園</p>
</footer>

</body>
</html>
