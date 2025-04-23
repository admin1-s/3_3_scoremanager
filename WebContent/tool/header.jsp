<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    body {
        font-family: sans-serif;
        margin: 0;
        background-color: white;
    }

    header {
        background-color: #e9f0fc;
        padding: 25px 30px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }


    header h1 {
        font-size: 36px;
        margin: 0;
    }

    .user-info {
        font-size: 14px;
    }

    nav {
        background-color: white;
        width: 300px;
        float: left;
        padding: 20px;
        height: 100vh;
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
        margin-left: 100px;
        padding: 1px;
    }

    .card-container {
        display: flex;
        gap: 20px;
    }

    .card {
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        padding: 20px;
        width: 200px;
    }

    .card.red {
        background-color: #f8d7da;
    }

    .card.green {
        background-color: #d4edda;
    }

    .card.blue {
        background-color: #d1ecf1;
    }

    .card h3 {
        margin: 0 0 10px;
    }

    .card a {
        display: block;
        color: #000;
        text-decoration: none;
        margin: 5px 0;
    }

    footer {
        text-align: center;
        font-size: 12px;
        color: #aaa;
        margin-top: 40px;
        clear: both;
    }

    .wrapper {
    	display: flex;
	}
</style>

<header>
    <h1>得点管理システム</h1>
    <div class="user-info">
    <c:choose>
            <c:when test="${not empty teacher.id}">
                ${teacher.name} 様　
                <a href="../scoremanager.main/Logout.action">ログアウト</a>
            </c:when>
            <c:otherwise>
                <a href="../main/login-in.jsp">ログイン</a>
            </c:otherwise>
        </c:choose>
    </div>
</header>

<div class="wrapper">