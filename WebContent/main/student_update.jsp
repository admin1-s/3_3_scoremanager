<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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

    .btn {
  		padding: 1px 4px;
  		border-radius: 8px;
  		background-color: #00bfff;
  		color: #fff;
  		font-size: 15px;
	}


</style>

<div class="content-area">
    <div class="title-area">学生情報変更</div>

		<div>
			<form action="../scoremanager/StudetUpdateExecute.action" method="post" style="margin-left: 10px;">
				<p>
				入学年度<br>
				<input type="text" name="ent_year" value="${student.entYear }" style="width:100%; " readonly/>
			 	</p>
			 	<p>
				学生番号<br>
				<input type="text" name="no" value="${student.no }" style="width:100%; " readonly/>
			 	</p>
			 	<p>氏名<br>
			 	<input type="text" name="name" maxlength="20" value="${student.name }"
			 	 style="width:100%; " required/>
			 	</p>
			 	<p>クラス<br>
			 	<select readonly>
  					<option name="class_num">${student.classNum }</option>
				</select>
			 	</p>
			 	<p>在学中<input type="checkbox" name="is_attend"></p>

			 	<p><input type="submit" value="変更" class="btn"></p>
			</form>

			<a href="../scoremanager/SubjectList.action" style="margin-left: 10px; color: #1e90ff;">戻る</a>
		</div>

</div>


<jsp:include page="../tool/footer.jsp" />
