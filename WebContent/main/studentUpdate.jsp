<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
  		color: #fff;
  		font-size: 15px;
	}


</style>

<div class="content-area">
    <div class="title-area">学生情報変更</div>

    <form action="StudentUpdateExecute.action" method="post">
    		<p>入学年度<br>
    		<input type="text" id="entYear" name="entYear" value="${student.entYear}" style="margin-top:10px; margin-left:10px; width:100%; border: none;" readonly />
			</p>

            <p>学生番号<br>
            <input type="text" id="studentNo" name="studentNo" value="${student.no}" style="margin-top:10px; margin-left:10px; width:100%; border: none;" readonly />
			</p>

            <p>名前<br>
            <input type="text" id="studentName" name="studentName" value="${student.name}" style="margin-top:10px; margin-left:10px; width:100%; " required />
			</p>

            <p>クラス
            <select name="classNum" style="margin-top:10px; margin-left:10px; width:100%;" required>
                <c:forEach var="classItem" items="${classList}">
                    <option value="${classItem}" ${classItem == student.classNum ? 'selected' : ''}>${classItem}</option>
                </c:forEach>
            </select>
            </p>

			<p>
            <label for="isAttend">在学中</label>
            <input type="checkbox" id="isAttend" name="isAttend" ${student.attend ? 'checked' : ''} />
			</p>

            <p><input type="submit" value="更新" class="btn"/></p>
    </form>

    <a href="../scoremanager/StudentList.action">学生一覧へ戻る</a>
</div>

<jsp:include page="../tool/footer.jsp" />
