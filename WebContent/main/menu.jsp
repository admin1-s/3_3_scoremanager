<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--メニュー画面を表示するクラス--%>
<div class="menu">
    <nav>
    <a href="../main/index.jsp">メニュー</a>
    <a href="<%= request.getContextPath() %>/main?action=StudentListAction">学生管理</a>
    <a href="#">成績管理</a>
    <a href="../main/seisekitoroku.jsp">　成績登録</a>
    <a href="#">　成績参照</a>
    <a href="../scoremanager.main/SubjectList.action">科目管理</a>
	</nav>
</div>