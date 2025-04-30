<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../tool/header.jsp" />
<jsp:include page="../main/menu.jsp" />

<div class="main">
    <h2>学生情報更新</h2>

    <form action="StudentUpdateExecute.action" method="post">
    	<div>
    		<label for="entYear">入学年度</label><br>
    		<input type="text" id="entYear" name="entYear" value="${student.entYear}" readonly />
		</div>
        <div>
            <label for="studentNo">学生番号</label><br>
            <input type="text" id="studentNo" name="studentNo" value="${student.no}" readonly />
        </div>

        <div>
            <label for="studentName">名前</label><br>
            <input type="text" id="studentName" name="studentName" value="${student.name}" required />
        </div>

        <div>
            <label for="classNum">クラス</label>
            <select name="classNum" style="width:100%;" required>
                <c:forEach var="classItem" items="${classList}">
                    <option value="${classItem}" ${classItem == student.classNum ? 'selected' : ''}>${classItem}</option>
                </c:forEach>
            </select>
        </div>

        <div>
            <label for="isAttend">在学中</label>
            <input type="checkbox" id="isAttend" name="isAttend" ${student.attend ? 'checked' : ''} />
        </div>

        <div>
            <input type="submit" value="更新" />
        </div>
    </form>

    <a href="studentList.jsp">学生一覧へ戻る</a>
</div>

<jsp:include page="../tool/footer.jsp" />
