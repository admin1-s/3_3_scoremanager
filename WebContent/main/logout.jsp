<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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

        .header {
            background-color: #e7f0fb;
            padding: 30px 20px;
            font-size: 28px;
            font-weight: bold;
            text-align: left;
            border-radius: 0 0 10px 10px;
            max-width: 1000px;
            margin: 0 auto;
        }

        .box {
            background-color: #ffffff;
            width: 650px;
            margin: 40px auto;
            border-radius: 10px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
            overflow: hidden;
        }

        .box .title {
    		text-align: left;
            background-color: #eeeeee;
            font-size: 28px;
            font-weight: bold;
            padding: 14px 0;
            min-height: 35px;
            padding-left: 16px;
        }

        .box .message {
            background-color: #7bc293;
            color: white;
            font-size: 14px;
            padding: 14px;
            margin-top: 20px;
        }

        .box .link {
            margin: 20px;
        }

        .box .link a {
            text-decoration: none;
            color: #007bff;
        }

        .footer {
            background-color: #f0f0f0;
            color: #888;
            padding: 20px 0;
            font-size: 12px;
            text-align: center;
            border-radius: 10px 10px 0 0;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>

<div class="header">得点管理システム</div>

<div class="box">
    <div class="title">ログアウト</div>
    <div class="message">ログアウトしました</div>
    <div class="link"><a href="../main/login-in.jsp">ログイン</a></div>
</div>

<div class="footer">
    © 2023 TIC<br>大原学園
</div>

</body>
</html>
