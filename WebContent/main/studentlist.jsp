<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/main/base.jsp">
  <c:param name="title">得点管理システム-学生一覧</c:param>
  <c:param name="content">
    <section class="me-4">
      <h2 class="h3 mb-3 fw-bold bg-secondary bg-opacity-10 py-2 px-4">学生管理</h2>

      <!-- 検索フォーム -->
      <form method="get" class="border-bottom pb-3 mb-3 px-4">
        <div class="row align-items-end g-3">

          <!-- 入学年度 -->
          <div class="col-md-3">
            <label for="student-f1-select" class="form-label">入学年度</label>
            <select class="form-select" name="f1">
              <option value="0">----------</option>
              <c:forEach var="year" items="${ent_year_set}">
                <option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
              </c:forEach>
            </select>
          </div>

          <!-- クラス -->
          <div class="col-md-3">
            <label for="student-f2-select" class="form-label">クラス</label>
            <select class="form-select" name="f2">
              <option value="0">----------</option>
              <c:forEach var="num" items="${class_num_set}">
					<%--現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
					<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
				</c:forEach>
            </select>
          </div>

          <!-- 在学中チェック -->
          <div class="col-md-2">
            <div class="form-check mt-4">
              <input class="form-check-input" type="checkbox" id="student-f3-check" name="f3" value="t"
                <c:if test="${!empty f3}">checked</c:if> />
              <label class="form-check-label" for="student-f3-check">在学中</label>
            </div>
          </div>

          <!-- 絞込みボタン -->
          <div class="col-md-2">
            <button type="submit" class="btn btn-primary w-100">絞込み</button>
          </div>
        </div>
        <div class="mt-2 text-warning">${errors}</div>
      </form>

      <!-- 新規登録 -->
      <div class="px-4 mb-3 text-end">
        <a href="../scoremanager.main/studentInfo" class="btn btn-outline-primary">新規登録</a>
      </div>

      <!-- 検索結果表示 -->
      <div class="px-4">
          <c:if test="${students != null && students.size() > 0}">
            <div class="mb-2">検索結果：${students.size()}件</div>
            <table class="table table-bordered table-hover table-striped align-middle">
              <thead class="table-light">
                <tr>
                  <th>入学年度</th>
                  <th>学生番号</th>
                  <th>氏名</th>
                  <th>クラス</th>
                  <th class="text-center">在学中</th>
                  <th class="text-center">変更</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="student" items="${students}">
                  <tr>
                    <td>${student.entYear}</td>
                    <td>${student.no}</td>
                    <td>${student.name}</td>
                    <td>${student.classNum}</td>
                    <td class="text-center">
                      <c:choose>
                        <c:when test="${student.isAttend()}">○</c:when>
                        <c:otherwise>×</c:otherwise>
                      </c:choose>
                    </td>
                    <td class="text-center">
                      <a href="http://localhost:8080/management_score/scoremanager/StudentUpdate.action?no=${student.no}">変更</a>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </c:if>
          <c:if test="${empty students && empty errors}">
            <div class="text-danger">学生情報が存在しませんでした</div>
          </c:if>
      </div>
    </section>
  </c:param>
</c:import>
