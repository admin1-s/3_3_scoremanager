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


</style>

<div class="content-area">
    <div class="title-area">
        科目管理
    </div>

    <!-- 新規登録 -->
      <div class="px-4 mb-3 text-end" style="padding-left:90%; ">
        <a href="../scoremanager.main/SubjectCreate.action" class="btn" >新規登録</a>
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
                      <a href="">変更</a>
                      <a href="">削除</a>
                    </td>
                  </tr>
                </c:forEach>
            </table>
       </div>
</div>

<jsp:include page="../tool/footer.jsp" />
