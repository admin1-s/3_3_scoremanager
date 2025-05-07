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
    <div class="title-area">
    	<p>成績登録完了</p>
    </div>

		<div class="done">
			<h3 class="h3">成績を登録しました</h3>
		</div>

		<div>
			<a href="../scoremanager/TestRegist.action">戻る</a>
			<a href="" >成績参照</a>
		</div>

</div>


<jsp:include page="../tool/footer.jsp" />
