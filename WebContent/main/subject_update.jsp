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
  		text-decoration: none;
  		color: #fff;
  		font-size: 15px;
	}


</style>

<div class="content-area">
    <div class="title-area">科目情報変更</div>

		<div>
			<form action="../scoremanager.main/SubjectUpdateExecute.action" method="post" style="margin-left: 10px;">
				<p>
				科目コード<br>
				<input type="text" name="cd" value="${subject.cd }" style="width:100%; " readonly/>
			 	</p>
			 	<%String error=(String) request.getAttribute("error"); %>
			 	<% if (error != null){ %>
			 		<p class="error_massage" style="color: orange; font-size:13px;">※<%= error %></p>
			 	<%} %>
			 	<p>科目名<br>
			 	<input type="text" name="name" maxlength="20" value="${subject.name }"
			 	 style="width:100%; " required/>
			 	</p>

			 	<p><input type="submit" value="変更" class="btn"></p>
			</form>

			<a href="../scoremanager.main/SubjectList.action" style="margin-left: 10px; color: #1e90ff;">戻る</a>
		</div>

</div>


<jsp:include page="../tool/footer.jsp" />
