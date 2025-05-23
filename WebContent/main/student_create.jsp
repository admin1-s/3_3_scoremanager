<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../tool/header.jsp" %>
<%@ include file="menu.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Student" %>

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

    form label {
        display: block;
        margin-top: 5px;
    }

    select, input[type="text"] {
        width: 100%;
        padding: 8px;
        font-size: 14px;
        margin-top: 5px;
        margin-bottom: 10px;
        box-sizing: border-box;
    }

    input[type="submit"] {
        background-color: #444;
        color: white;
        border: none;
        font-size: 16px;
        padding: 10px 20px;
        cursor: pointer;
        border-radius: 4px;
    }

    input[type="submit"]:hover {
        background-color: #4169e1;
    }
</style>

<div class="content-area">
	<div class="title-area">
    	<p>学生情報登録</p>
    </div>

    <form action="../scoremanager/StudentCreateExecute.action" method="post">

        <label for="entYear" style="margin-top:30px; ">入学年度:</label>
		<select name="entYear" id="entYear" required>
    		<option value="">--------</option>
    			<% for (int year = 2026; year >= 2016; year--) { %>
        	<option value="<%= year %>"><%= year %></option>
    			<% } %>
		</select>

        <label for="no">学生番号:</label>
        <input type="text" name="no" id="no" placeholder="学生番号を入力してください" required>

        <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <p style="color: orange; font-size:13px;"><%= error %></p>
    <%
        }
    %>

        <label for="name">氏名:</label>
        <input type="text" name="name" id="name" placeholder="氏名を入力してください" required>

        <label for="class_num">クラス:</label>
        <select name="class_num" id="class_num" required>
            <option value="">--------</option>
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
        </select>

        <input type="submit" value="登録して終了" style="margin-top:20px; "><br>

        <a href="../scoremanager/StudentList.action" >戻る</a>

    </form>
</div>

<%@ include file="../tool/footer.jsp" %>
