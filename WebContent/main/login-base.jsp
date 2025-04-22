<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>得点管理システム</title>
    <style>
        body {
            font-family: sans-serif;
            background-color: #ffffff;
            text-align: center;
            margin: 0;
            padding: 0;
        }

        .header, .footer {
            width: 100%;
        }

        .header {
            background-color: transparent;
        }

        .header .inner {
            max-width: 1000px;
            margin: 0 auto;
            padding: 30px 20px;
            font-size: 28px;
            font-weight: bold;
            text-align: left;
            background-color: #e7f0fb;
            border-radius: 0 0 10px 10px;
        }

        .head {
            background-color: #eeeeee;
            font-size: 18px;
            font-weight: bold;
            padding: 14px 0;
            text-align: center;
        }

        .login-container {
            background-color: white;
            width: 320px;
            margin: 40px auto;
            border-radius: 10px;
            box-shadow: 0px 2px 6px rgba(0,0,0,0.1);
            overflow: hidden;
        }

        .footer {
            background-color: transparent;
            color: #888;
            padding: 20px 0;
            font-size: 12px;
            position: fixed;
            width: 100%;
            bottom: 0;
            text-align: center;
        }

        .footer .inner {
            max-width: 1000px;
            margin: 0 auto;
            background-color: #f0f0f0;
            padding: 10px;
            border-radius: 10px 10px 0 0;
        }
    </style>
</head>
<body>

<div class="header">
    <div class="inner">得点管理システム</div>
</div>

<div class="login-container">
    <div class="head">ログイン</div>
    <jsp:include page="${content}" />
</div>

<%@ include file="../tool/footer.jsp" %>

</body>
</html>
