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


	.table{
	width: 100%;
    border-spacing: 0;
}

	.table th,.table td{
    border-bottom: 1px solid #000;
    padding: 20px;
}

	.create {
	padding-top: 2%;
	padding-left: 90%;
}

	.create-btn {
	color: #1e90ff;
}

	.btn-1{
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



	.btn-2{
	font-size: 18px;
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
        <p>科目管理</p>
    </div>

    <!-- 新規登録 -->
      <div class="create" style="padding-left:85%; ">
        <a href="../scoremanager/SubjectCreate.action" class="create-btn" >新規登録</a>
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
                    	<form action="../scoremanager/SubjectUpdate.action" method="post" style="display:inline ; margin-right:10%;">
                      		<input type="hidden" name="cd" value="${subject.cd }"/>
                      		<input type="submit" class="btn-1" value="変更"/>
                      	</form>
                      	<form action="../scoremanager/SubjectDelete.action" method="post" style="display:inline">
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
