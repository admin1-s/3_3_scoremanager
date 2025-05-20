<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty message}">
    <p style="color: red;">${message}</p>
</c:if>

<c:if test="${not empty tsList }">
	<p>科目：${sub.getName()}</p>
	<table class="table">
		<tr>
			<th align=left>入学年度</th>
			<th align=left>クラス</th>
			<th align=left>学生番号</th>
			<th align=left>氏名</th>
			<th align=left>1回</th>
			<th align=left>2回</th>
		</tr>

		<c:forEach var="ts" items="${tsList }">
			<tr>
				<td>${entYear}</td>
				<td>${classNum}</td>
				<td>${ts.getStudentNo()}</td>
				<td>${ts.getStudentName()}</td>
				<td>
					<c:choose>
						<c:when test="${ts.getPoint(1) != null}">
							${ts.getPoint(1)}
						</c:when>
						<c:otherwise>ー</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${ts.getPoint(2) != null}">
							${ts.getPoint(2)}
						</c:when>
						<c:otherwise>ー</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<c:if test="${not empty mes}">
		<p>氏名：${student.getName()}（ ${f4} ）</p>
    	<p style="color: red;">${mes}</p>
	</c:if>

<c:if test="${not empty tstList }">
	<p>氏名：${student.getName()}（ ${f4} ）</p>
	<table class="table">
				<tr>
					<th align=left>科目名</th>
					<th align=left>科目コード</th>
					<th align=left>回数</th>
					<th align=left>点数</th>
				<tr>

				<c:forEach var="ts" items="${tstList }">
					<tr>
						<td>${ts.getSubjectName() }</td>
						<td>${ts.getSubjectCd()}</td>
						<td>${ts.getNum() }</td>
						<td>${ts.getPoint() }</td>
					</tr>
				</c:forEach>
			</table>
</c:if>


