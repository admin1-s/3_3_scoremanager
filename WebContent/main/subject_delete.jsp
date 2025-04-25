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
  		background-color: #dc143c;
  		color: #fff;
  		font-size: 15px;
	}



</style>

<div class="content-area">
    <div class="title-area">科目情報削除</div>

	<div style="margin-left: 10px;">
		<p style="font-size:18px;">「${subject.name} (${subject.cd}) 」を削除してもよろしいですか？</p>

		<form action="../scoremanager.main/SubjectDeleteExecute.action" method="post">
			<input type="hidden" name="cd" value="${subject.cd }"/>
			<input type="hidden" name="name" value="${subject.name }"/>
			<input type="submit" value="削除" class="btn">
		</form>

		<a href="../scoremanager.main/SubjectList.action" style="color: #1e90ff;" >戻る</a>
	</div>

</div>


<jsp:include page="../tool/footer.jsp" />
