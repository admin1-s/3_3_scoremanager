<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, bean.*" %>
<jsp:include page="../tool/header.jsp" />
<jsp:include page="../main/menu.jsp" />


<style>
    .content-area {
        margin-left: 1px;
        padding: 30px;
        width:100%;
    }

    .title-area {
        background-color: #eeeeee;
        padding: 15px 30px;
        font-size: 20px;
        font-weight: bold;
        border-bottom: 1px solid #ccc;
    }

    .form-container {
        background-color: #fff;
        margin-top: 10px;
        padding: 20px 30px;
        display: flex;
        flex-wrap: wrap;
        align-items: center;
        gap: 20px;
        border-bottom: 1px solid #ccc;
    }

    .form-group {
        display: flex;
        flex-direction: column;
    }

    .form-group label {
        margin-bottom: 5px;
        font-weight: bold;
        font-size: 14px;
    }

    .form-group select {
        padding: 5px 10px;
        width: 160px;
        font-size: 14px;
    }

    .table{
	width: 100%;
    border-spacing: 0;
    margin-bottom: 10px;
}

	.table th,.table td{
    border-bottom: 1px solid #000;
    padding: 20px;
}

    .btn-search {
        background-color: #666;
        color: white;
        border: none;
        padding: 10px 20px;
        font-size: 14px;
        cursor: pointer;
        height: 40px;
        align-self: flex-end;
    }

    .btn-search:hover {
        background-color: #444;
    }
    .undertext{
    	color:aqua;
    }
</style>

<div class="content-area">
    <div class="title-area">
        成績参照
    </div>

	<!-- 科目情報検索 -->
    <form action="SubjectSearch.action" method="post">
        <div class="form-container">
        <div class="form-group">
        <p>科目情報</p>
        </div>

            <div style="float: left; margin-right: 10%;">
			入学年度<br>
			<select name="f1" style="margin-top: 5px;">
				<option value="">--------</option>
				<c:forEach var="year" items="${yearList }">
					<option value="${year }" <c:if test="${year == selectedYear}">selected</c:if>>${year}</option>
				</c:forEach>
			</select>
			</div>

            <div style="float: left; margin-right: 10%">
			クラス<br>
			<select name="f2" style="margin-top: 5px;">
				<option value="">--------</option>
				<c:forEach var="c" items="${classList }">
					<option value="${c.getClassNum() }"  <c:if test="${c.getClassNum() == selectedClass}">selected</c:if>>${c.getClassNum()}</option>
				</c:forEach>
			</select>
			</div>

            <div style="float: left; margin-right: 10%">
			科目<br>
			<select name="f3" style="margin-top: 5px;">
				<option value="">--------</option>
				<c:forEach var="sub" items="${subjectList }">
					<option value="${sub.getCd() }" <c:if test="${sub.getCd() ==selectedSubject }">selected</c:if>>${sub.getName() }</option>
				</c:forEach>
			</select>
			</div>


            <div class="form-group">
                <button type="submit" class="btn-search">検索</button>
            </div>
        </div>
    </form>






<form action="TestListStudentExecute.action" method="post">

        <div class="form-container">
        <div class="form-group">
        <p>学生情報</p>
        </div>

            <div class="form-group">
                <label>学生番号</label>
                <input type="text" name="f4" placeholder="学生番号を入力してください" max=10 required>
            </div>

            <div class="form-group">
                <button type="submit" class="btn-search">検索</button>
            </div>
			</div>

		<div class="undertext">
			<p>科目情報を選択又は学生情報を入力し検索ボタンをクリックしてください</p>
		</div>
</form>

<c:if test="${not empty students }">
	<p>氏名：${student.getName()}（ ${f4} ）</p>

	<table class="table">
				<tr>
					<th align=left>入学年度</th>
					<th align=left>クラス</th>
					<th align=left>学生番号</th>
					<th align=left>氏名</th>
					<th align=left>1回</th>
					<th align=left>2回</th>
				<tr>

				<c:forEach var="test" items="${tests }">
					<tr>
						<td>${student.getEntYear()}</td>
						<td>${test.getClassNum()}</td>
						<td>${student.getNo() }</td>
						<td>${student.getName() }</td>
						<td>
						</td>
						<td>
						</td>
					</tr>
				</c:forEach>
			</table>
</c:if>

<c:if test="${not empty tests }">
	<p>氏名：${student.getName()}（ ${f4} ）</p>

	<table class="table">
				<tr>
					<th align=left>科目名</th>
					<th align=left>科目コード</th>
					<th align=left>回数</th>
					<th align=left>点数</th>
				<tr>

				<c:forEach var="test" items="${tests }">
					<tr>
						<td></td>
						<td>${test.getSubject().getCd()}</td>
						<td>${test.getNo() }</td>
						<td>${test.getPoint() }</td>
					</tr>
				</c:forEach>
			</table>
</c:if>

</div>

<jsp:include page="../tool/footer.jsp" />
