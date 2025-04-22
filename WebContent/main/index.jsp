<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../tool/header.jsp" %>
<%@ include file="menu.jsp" %>

<title>得点管理システム - topページ</title>
<div class="main">

    <h2>メニュー</h2>

    <div class="card-container">
        <div class="card red">
            <h3>② 学生管理</h3>
            <a href="../scoremanager.main/studentlist3">学生管理</a>
        </div>

        <div class="card green">
            <h3>③ 成績管理</h3>
            <a href="../main/seisekitoroku.jsp">④ 成績登録</a>
            <a href="gradeView.jsp">⑤ 成績参照</a>
        </div>

        <div class="card blue">
            <h3>⑥ 科目管理</h3>
            <a href="SubjectList.action">科目管理</a>
        </div>
    </div>
</div>

<%@ include file="../tool/footer.jsp" %>
