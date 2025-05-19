<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
		margin-bottom:30px;
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

   .box{
   		padding: 20px;
   		margin-top:10px;
   		margin-bottom:30px;
   		border: solid 1px #808080;
   		border-radius:10px;
   	}


   .table{
	width: 100%;
    border-spacing: 0;
    margin-bottom: 10px;
}

	.table th,.table td{
    border-bottom: 1px solid #000;
    padding: 20px;
}

   .search{
   		font-size: 18px;
		display: inline-block;
		padding: 0.2em 0.5em 0.1em;
		color: #0099ff;
		border: none;
		border-radius: 5px;
		background: linear-gradient(
    		-45deg,
    		#ddeeff 25%,
    		#c6e6fb 25%,
    		#c6e6fb 50%,
    		#ddeeff 50%,
    		#ddeeff 75%,
  			#c6e6fb 75%,
   			#c6e6fb
   		);
    	background-size: 10px 10px;
		cursor: pointer;
	}

	.fin {
		font-size: 15px;
		display: inline-block;
		padding: 0.2em 0.5em 0.1em;
		color: #0099ff;
		border: none;
		border-radius: 5px;
		background: linear-gradient(
    		-45deg,
    		#ddeeff 25%,
    		#c6e6fb 25%,
    		#c6e6fb 50%,
    		#ddeeff 50%,
    		#ddeeff 75%,
  			#c6e6fb 75%,
   			#c6e6fb
   		);
    	background-size: 10px 10px;
		cursor: pointer;
	}


</style>
<p>エラーが発生しました。</p>


<jsp:include page="../tool/footer.jsp" />
