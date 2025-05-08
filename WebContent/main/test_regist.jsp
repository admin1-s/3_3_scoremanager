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
        width:100%;
    }

    .title-area {
    	position: relative;
  		background: #eee;
  		box-shadow: 0px 0px 0px 5px #eee;
  		border: dashed 2px white;
  		padding: 15px 30px;
		color: #000000;
		margin-bottom:30px;
    }

    .title-area:after{
    	position: absolute;
  		content: '';
  		left: -7px;
		top: -7px;
		border-width: 0 0 15px 15px;
		border-style: solid;
		border-color: #fff #fff #eee;
		box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.15);
    }

    .title-area p{
    	margin:0;
    	font-size: 20px;
        font-weight: bold;
   }

   .box{
   		padding: 20px;
   		margin-top:10px;
   		margin-bottom:30px;
   		border: solid 1px #808080;
   		border-radius:10px;
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

   .search{
   		font-size: 18px;
		display: inline-block;
		padding: 0.2em 0.5em 0.1em;
		color: #0099ff;
		border: none;
		border-radius: 5px;
		background: linear-gradient(
    		-45deg,
    		#ddeeff 25%,
    		#c6e6fb 25%,
    		#c6e6fb 50%,
    		#ddeeff 50%,
    		#ddeeff 75%,
  			#c6e6fb 75%,
   			#c6e6fb
   		);
    	background-size: 10px 10px;
		cursor: pointer;
	}

	.fin {
		font-size: 15px;
		display: inline-block;
		padding: 0.2em 0.5em 0.1em;
		color: #0099ff;
		border: none;
		border-radius: 5px;
		background: linear-gradient(
    		-45deg,
    		#ddeeff 25%,
    		#c6e6fb 25%,
    		#c6e6fb 50%,
    		#ddeeff 50%,
    		#ddeeff 75%,
  			#c6e6fb 75%,
   			#c6e6fb
   		);
    	background-size: 10px 10px;
		cursor: pointer;
	}


</style>

<div class="content-area">
    <div class="title-area" >
       <p>成績管理</p>
    </div>


	<div class="box">
		<!-- メッセージ表示 -->
		<c:if test="${not empty message}">
			<p style="color:red;">${message }</p>
		</c:if>

		<form action="TestRegist.action" method="post" style="text-align: center;">
			<div style="float: left; margin-right: 10%;">
			入学年度<br>
			<select name="entYear" style="margin-top: 5px;">
				<option value="">--</option>
				<c:forEach var="year" items="${yearList }">
					<option value="${year }" <c:if test="${year == selectedYear}">selected</c:if>>${year}</option>
				</c:forEach>
			</select>
			</div>

			<div style="float: left; margin-right: 10%">
			クラス<br>
			<select name="classNum" style="margin-top: 5px;">
				<option value="">--</option>
				<c:forEach var="c" items="${classList }">
					<option value="${c.getClassNum() }"  <c:if test="${c.getClassNum() == selectedClass}">selected</c:if>>${c.getClassNum()}</option>
				</c:forEach>
			</select>
			</div>

			<div style="float: left; margin-right: 10%">
			科目<br>
			<select name="subjectCd" style="margin-top: 5px;">
				<option value="">--</option>
				<c:forEach var="sub" items="${subjectList }">
					<option value="${sub.getCd() }" <c:if test="${sub.getCd() ==selectedSubject }">selected</c:if>>${sub.getName() }</option>
				</c:forEach>
			</select>
			</div>

			<div style="float: left; margin-right: 10%">
			回数<br>
			<select name="count" style="margin-top: 5px;">
				<option value="">--</option>
				<option value="1">1</option>
				<option value="2">2</option>
			</select>
			</div>

			<input type="submit" value="検索" class="search" />
		</form>
	</div>


	<!-- 学生と成績入力欄 -->
	<c:if test="${not empty subjectList }">
		<p>科目：${subjectName }（第${selectedCount }回）</p>
		<form action="TestRegistExecute.action" method="post">
			<table class="table">
				<tr>
					<th align=left>入学年度</th>
					<th align=left>クラス</th>
					<th align=left>学生番号</th>
					<th align=left>氏名</th>
					<th align=left>点数</th>
				<tr>

				<c:forEach var="student" items="${studentList }">
					<tr>
						<td>${selectedYear }</td>
						<td>${selectedClass }</td>
						<td>${student.getNo() }</td>
						<td>${student.getName() }</td>
						<td>
							<input type="number" name="score_${student.getNo() }"
							value="<c:forEach var='test' items='${testList }'>
										<c:if test='${test.student.no == student.no }'>${test.point}</c:if>
									</c:forEach>" min="0" max="100" required />
						</td>
					</tr>
				</c:forEach>
			</table>

			<!-- 検索条件をhiddenで保持 -->
			<input type="hidden" name="entYear" value="${selectedYear }" />
			<input type="hidden" name="classNum" value="${selectedClass }" />
			<input type="hidden" name="subjectCd" value="${selectedSubject }" />
			<input type="hidden" name="no" value="${selectedCount }" />

			<input type="submit" value="登録して終了" class="fin" style="margin-top:10px;" />
		</form>
	</c:if>

</div>

<jsp:include page="../tool/footer.jsp" />
