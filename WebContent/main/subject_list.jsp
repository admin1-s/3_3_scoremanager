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

	.table{
	width: 100%;
    border-spacing: 0;
}

	.table th,.table td{
    border-bottom: 1px solid #000;
    padding: 10px;
}

	.create {
	padding-top: 2%;
	padding-left: 90%;
}

	.create-btn {
	color: #1e90ff;
}

	.btn-1{
	padding: 1px 4px;
	margin-right: 10%;
  	border-radius: 8px;
  	background-color: #00bfff;
  	color: #fff;
  	font-size: 15px;
}

	.btn-2{
	padding: 1px 4px;
  	border-radius: 8px;
  	background-color: #dc143c;
  	color: #fff;
  	font-size: 15px;

}



</style>

<div class="content-area">
    <div class="title-area">
        科目管理
    </div>

    <!-- 新規登録 -->
      <div class="create" style="padding-left:90%; ">
        <a href="../scoremanager.main/SubjectCreate.action" class="create-btn" >新規登録</a>
      </div>

      <div class="px-4">
        	<table class="table" >
                <tr>
                  <th align=left>科目コード</th>
                  <th align=left>科目名</th>
                  <th></th>
                </tr>
                <c:forEach var="subject" items="${subjectList}">
                  <tr>
                    <td>${subject.cd}</td>
                    <td>${subject.name}</td>
                    <td class="text-center">
                    	<form action="../scoremanager.main/SubjectUpdate.action" method="post" style="display:inline ; margin-right:10px;">
                      		<input type="hidden" name="cd" value="${subject.cd }"/>
                      		<input type="submit" class="btn-1" value="変更"/>
                      	</form>
                      	<form action="../scoremanager.main/SubjectDelete.action" method="post" style="display:inline">
                      		<input type="hidden" name="cd" value="${subject.cd }"/>
                      		<input type="submit" class="btn-2" value="削除"/>
                      	</form>
                    </td>
                  </tr>
                </c:forEach>
            </table>
       </div>
</div>

<jsp:include page="../tool/footer.jsp" />
