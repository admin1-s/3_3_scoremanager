<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/main/base.jsp">
  <c:param name="title">得点管理システム-学生一覧</c:param>
  <c:param name="content">
<section class="me-4">
      <h2  class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>

      <!-- 新規登録 -->
      <div class="px-4 mb-3 text-end">
        <a href="../scoremanager.main/studentInfo" class="btn btn-outline-primary">新規登録</a>
      </div>

 <div class="px-4">
        	<table class="table table-bordered table-hover table-striped align-middle">
              <thead class="table-light">
                <tr>
                  <th>科目コード</th>
                  <th>科目名</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
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
              </tbody>
            </table>
    </div>
</section>


<jsp:include page="../tool/footer.jsp" />

</c:param>
</c:import>
