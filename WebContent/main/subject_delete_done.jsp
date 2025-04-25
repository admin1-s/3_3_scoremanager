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

	.h3 {
		text-align: center;
		font-size: 15px;
	}

	.done {
		background-color: #3cb371;
		margin-bottom: 100px;
	}


</style>

<div class="content-area">
    <div class="title-area">科目情報削除</div>

		<div class="done">
			<h3 class="h3">削除が完了しました</h3>
		</div>

		<div>
			<a href="../scoremanager.main/SubjectList.action" style="margin-left: 10px;">科目一覧</a>
		</div>

</div>


<jsp:include page="../tool/footer.jsp" />
