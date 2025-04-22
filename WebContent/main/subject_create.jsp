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
</section>




<jsp:include page="../tool/footer.jsp" />

</c:param>
</c:import>
