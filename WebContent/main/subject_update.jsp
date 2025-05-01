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
    	margin: 0 auto 2em;
    	padding: 2em;
    	background: none;
    	border: 1px solid #ccc;
    	position: relative;
    }

    .title-area:after{
    	background-color: #eee;
 	  	border: none;
   		content: ''; /* 擬似要素にコンテンツなし */
   		position: absolute;
  	 	top: 7px;
   		left: 7px;
   		width: 100%;
   		height: 100%;
   		z-index: -1; /* 背景として後ろに表示 */
    }

    .title-area p{
    	margin:0;
    	font-size: 20px;
        font-weight: bold;
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
    <div class="title-area">
    	<p>科目情報変更</p>
    </div>

		<div>
			<form action="../scoremanager/SubjectUpdateExecute.action" method="post" style="margin-left: 10px;">
				<p>
				科目コード<br>
				<input type="text" name="cd" value="${subject.cd }" style="margin-top:10px; margin-left:10px; width:100%; border: none;" readonly/>
			 	</p>
			 	<%String error=(String) request.getAttribute("error"); %>
			 	<% if (error != null){ %>
			 		<p class="error_massage" style="color: orange; font-size:13px;">※<%= error %></p>
			 	<%} %>
			 	<p>科目名<br>
			 	<input type="text" name="name" maxlength="20" value="${subject.name }"
			 	 style="margin-top:10px; margin-left:10px; width:100%; " required/>
			 	</p>

			 	<p><input type="submit" value="変更" class="btn"></p>
			</form>

			<a href="../scoremanager/SubjectList.action" style="margin-left: 10px; color: #1e90ff;">戻る</a>
		</div>

</div>


<jsp:include page="../tool/footer.jsp" />
