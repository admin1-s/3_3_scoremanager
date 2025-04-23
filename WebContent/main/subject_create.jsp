<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/main/base.jsp">
  <c:param name="title">得点管理システム-学生一覧</c:param>
  <c:param name="content">
	<section class="me-4"  style="margin-left: 10px;">
    	<h2  class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>

		<div>
			<form action="subject_create" method="post" style="margin-left: 10px;">
				<p>
				科目コード<br>
				<input type="text" name="cd" maxlength="3" placeholder="科目コードを入力してください"
			 	style="width:100%; " required>
			 	</p>
			 	<p>科目名<br>
			 	<input type="text" name="name" maxlength="20" placeholder="科目名を入力してください"
			 	 style="width:100%; " required>
			 	</p>

			 	<p><input type="submit" value="登録"
           		 style="padding:5px 15px; font-size:14px; margin-top:10px;"></p>
			</form>

			<a href="../scoremanager.main/SubjectList.action" style="margin-left: 10px;">戻る</a>
		</div>



    </section>


    <jsp:include page="../tool/footer.jsp" />

  </c:param>
</c:import>