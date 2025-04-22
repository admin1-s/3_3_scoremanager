<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../tool/header.jsp" %>
<%@ include file="menu.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Student" %>

<div class="main">
<h2>学生情報登録</h2>

<form action="studentAdd" method="post"><br>

    入学年度:
    <select name="entYear" required style="width:300px; padding:5px; margin-bottom:10px;">
        <option value="">選択してください</option>
        <%
    		List<Integer> entYears = (List<Integer>) request.getAttribute("entYears");
    			if (entYears != null) {
        		for (Integer year : entYears) {
		%>
    		<option value="<%= year %>"><%= year %></option>
		<%
        		}
    		}
		%>
    </select><br>

    学生番号:
    <input type="text" name="no" required
           style="width:300px; padding:5px; margin-bottom:10px;"><br>

    氏名:
    <input type="text" name="name" required
           style="width:300px; padding:5px; margin-bottom:10px;"><br>

    クラス:
    <select name="class_num" required style="width:300px; padding:5px; margin-bottom:10px;">
        <option value="">選択してください</option>
        <%
            List<String> classList = (List<String>) request.getAttribute("classList");
            if (classList != null) {
                for (String classNum : classList) {
        %>
            <option value="<%= classNum %>"><%= classNum %></option>
        <%
                }
            }
        %>
    </select><br>

    <input type="submit" value="登録して終了"
           style="padding:5px 15px; font-size:14px; margin-top:10px;"><br>

    <a href="../scoremanager.main/studentlist3">戻る</a>

</form>
</div>

<%@ include file="../tool/footer.jsp" %>
