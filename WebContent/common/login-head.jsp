<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>得点管理システム-ログイン</title>
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
            width: 400px;
            margin: 40px auto;
            border-radius: 10px;
            box-shadow: 0px 2px 6px rgba(0,0,0,0.1);
            overflow: hidden;
        }

        form {
            padding: 30px;
        }

        .login-input {
            width: 100%;
            padding: 12px 10px;
            margin: 12px 0;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 6px;
            background-color: #e8f1ff;
            box-sizing: border-box;
        }

        .remember-me {
            display: flex;
            align-items: center;
            font-size: 13px;
            margin: 10px 0 20px 0;
        }

        .remember-me input[type="checkbox"] {
            margin-right: 5px;
        }

        .login-button {
            background-color: #007bff;
            color: white;
            border: none;
            width: 100%;
            padding: 12px;
            font-size: 16px;
            border-radius: 6px;
            cursor: pointer;
            box-shadow: 0 2px 4px rgba(0,0,0,0.2);
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