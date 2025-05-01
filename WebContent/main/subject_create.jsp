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
    <div class="title-area">
    	<p>科目情報登録</p>
    </div>

		<div>
			<form action="../scoremanager/SubjectCreateExecute.action" method="post" style="margin-left: 10px;">
				<p>
				科目コード<br>
				<input type="text" name="cd" maxlength="3" placeholder="科目コードを入力してください"
			 	style="margin-top:10px; margin-left:10px; width:100%; " required>
			 	</p>
				<%String error=(String) request.getAttribute("error"); %>
			 	<% if (error != null){ %>
			 		<p class="error_massage" style="color: orange; font-size:13px;">※<%= error %></p>
			 	<%} %>
			 	<p>科目名<br>
			 	<input type="text" name="name" maxlength="20" placeholder="科目名を入力してください"
			 	 style="margin-top:10px; margin-left:10px; width:100%; " required>
			 	</p>

			 	<p><input type="submit" value="登録" class="btn"></p>
			</form>

			<a href="../scoremanager/SubjectList.action" style="margin-left: 10px; color: #1e90ff;">戻る</a>
		</div>

</div>


<jsp:include page="../tool/footer.jsp" />
