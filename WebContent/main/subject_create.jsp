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

    .btn {
  		padding: 5px 10px; /* 内側の余白設定 */
  		border-radius: 12px; /* 要素の角を丸くする */
  		background-color: #00bfff; /* 背景色指定 */
  		text-decoration: none; /* aタグの下線を非表示に */
  		color: #fff; /* 文字色 */
  		font-size: 15px; /* フォントサイズ */
	}


</style>

<div class="content-area">
    <div class="title-area">科目情報登録</div>

		<div>
			<form action="SubjectCreateExecute.action" method="post" style="margin-left: 10px;">
				<p>
				科目コード<br>
				<input type="text" name="cd" maxlength="3" placeholder="科目コードを入力してください"
			 	style="width:100%; " required>
			 	</p>
			 	<p>科目名<br>
			 	<input type="text" name="name" maxlength="20" placeholder="科目名を入力してください"
			 	 style="width:100%; " required>
			 	</p>

			 	<p><input type="submit" value="登録" class="btn"></p>
			</form>

			<a href="../scoremanager.main/SubjectList.action" style="margin-left: 10px;">戻る</a>
		</div>

</div>


<jsp:include page="../tool/footer.jsp" />
