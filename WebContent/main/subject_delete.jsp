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
    	position: relative;
  		background: #eee;
  		box-shadow: 0px 0px 0px 5px #eee;
  		border: dashed 2px white;
  		padding: 15px 30px;
		color: #000000;
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

	.btn{
	font-size: 15px;
	display: inline-block;
	padding: 0.2em 0.5em 0.1em;
	color: #ff0000;
	border: none;
	border-radius: 5px;
	background: linear-gradient(
    	-45deg,
    	#ffe4e1 25%,
    	#ffd6d8 25%,
    	#ffd6d8 50%,
    	#ffe4e1 50%,
    	#ffe4e1 75%,
  		#ffd6d8 75%,
   		#ffd6d8
   	);
    background-size: 10px 10px;
	cursor: pointer;

}



</style>

<div class="content-area">
    <div class="title-area">
    	<p>科目情報削除</p>
    </div>

	<div style="margin-left: 10px;">
		<p style="font-size:18px;">「${subject.name} (${subject.cd}) 」を削除してもよろしいですか？</p>

		<form action="../scoremanager/SubjectDeleteExecute.action" method="post">
			<input type="hidden" name="cd" value="${subject.cd }"/>
			<input type="hidden" name="name" value="${subject.name }"/>
			<input type="submit" value="削除" class="btn">
		</form>

		<a href="../scoremanager/SubjectList.action" style="color: #1e90ff;" >戻る</a>
	</div>

</div>


<jsp:include page="../tool/footer.jsp" />
