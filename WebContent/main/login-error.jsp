<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="login-head.jsp" %>

<div class="login-container">
    <div class="head">ログイン</div>
    <div class="error-message">
        ログインに失敗しました。IDまたはパスワードが確認できませんでした。
    </div>
    <form action="../scoremanager/LoginExecute.action" method="post">
        <input type="text" name="id" class="login-input" placeholder="ID" value="${id}" required />
        <input type="password" name="password" class="login-input" placeholder="パスワード" required />
        <div class="remember-me">
            <input type="checkbox" id="showPassword" />
            <label for="showPassword">パスワードを表示</label>
        </div>
        <button type="submit" class="login-button">ログイン</button>
    </form>
</div>

<script>
    document.getElementById("showPassword").addEventListener("change", function () {
        const passwordField = document.querySelector('input[name="password"]');
        passwordField.type = this.checked ? "text" : "password";
    });
</script>

<%@ include file="../tool/footer.jsp" %>
