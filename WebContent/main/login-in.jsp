<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="login-head.jsp" %>


<body>
<div class="login-container">
    <div class="head">ログイン</div>

    <c:if test="${not empty message}">
        <p style="color: red; text-align: center;">${message}</p>
    </c:if>

    <form action="../scoremanager/Login.action" method="post">
        <input type="text" name="id" class="login-input" placeholder="半角でご入力ください" value="admin" required />
		<input type="password" name="password" class="login-input" placeholder="30字以内の半角英数字でご入力ください" required />
        <div class="remember-me">
            <input type="checkbox" id="showPassword" />
            <label for="showPassword">パスワードを表示</label>
        </div>
        <button type="submit" class="login-button">ログイン</button>
    </form>
</div>

<%@ include file="../tool/footer.jsp" %>

<script>
    document.getElementById("showPassword").addEventListener("change", function () {
        const passwordField = document.querySelector('input[name="password"]');
        passwordField.type = this.checked ? "text" : "password";
    });
</script>

</body>
</html>
