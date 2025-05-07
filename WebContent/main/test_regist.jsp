<%@page import="dao.StudentDao"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, bean.*" %>

<jsp:include page="../tool/header.jsp" />
<jsp:include page="../main/menu.jsp" />

<style>
    .content-area {
        margin-left: 1px;
        padding: 30px;
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
</style>

<div class="content-area">
    <div class="title-area">
        成績管理
    </div>

	<!-- メッセージ表示 -->
	<c:if test="${not empty message}">
		<p style="color:red;">${message }</p>
	</c:if>


	<form action="TestRegist.action" method="post">
		入学年度：
		<select name="entYear">
			<option value="">--</option>
			<c:forEach var="year" items="${yearList }">
				<option value="${year }" <c:if test="${year == selectedYear}">selected</c:if>>${year}</option>
			</c:forEach>
		</select>

		クラス：
		<select name="classNum">
			<option value="">--</option>
			<c:forEach var="c" items="${classList}">
				<option value="${c.class_num }" <c:if test="${c.class_num == selectedClass}">selected</c:if>>${c.class_num}</option>
			</c:forEach>
		</select>

		科目：
		<select name="subjectCd">
			<option value="">--</option>
			<c:forEach var="sub" items="${subjectList }">
				<option value="${sub.cd }" <c:if test="${sub.cd ==selectedSubject }">selected</c:if>>${sub.name }</option>
			</c:forEach>
		</select>

		回数：
		<select name="count">
			<option value="">--</option>
			<c:forEach var="i" begin="1" end="10">
				<option value="${i }" <c:if test="${i == selectedCount }">selected</c:if>>${i }</option>
			</c:forEach>
		</select>

		<input type="submit" value="検索" />
	</form>

	<hr/>

	<!-- 学生と成績入力欄 -->
	<c:if test="${not empty subjectList }">
		<h3>科目：${subjectName}（第${selectedCount }回）</h3>
		<form action="TestRegistDone.action" method="post">
			<table border="1">
				<tr>
					<th>入学年度</th>
					<th>クラス</th>
					<th>学生番号</th>
					<th>氏名</th>
					<th>点数</th>
				<tr>

				<c:forEach var="student" items="${studentList }">
					<tr>
						<td>${selectedYear }</td>
						<td>${selectedClass }</td>
						<td>${student.no }</td>
						<td>${student.name }</td>
						<td>
							<input type="number" name="score_${student.no }"
							value="<c:forEach var='test' items='${testList }'> <c:if test='${test.student.no == student.no }'>${test.point}</c:if> </c:forEach>" min="0" max="100" required />
						</td>
					</tr>
				</c:forEach>
			</table>

			<!-- 検索条件をhiddenで保持 -->
			<input type="hidden" name="entYear" value="${selectedYear }" />
			<input type="hidden" name="classNum" value="${selectedClass }" />
			<input type="hidden" name="subjectCd" value="${selectedSubject }" />
			<input type="hidden" name="count" value="${selectedCount }" />

			<input type="submit" value="登録して終了" />
		</form>
	</c:if>

</div>

<jsp:include page="../tool/footer.jsp" />
