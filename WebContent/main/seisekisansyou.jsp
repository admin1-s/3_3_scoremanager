<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="../tool/header.jsp" />
<jsp:include page="../main/menu.jsp" />


<style>
    .content-area {
        margin-left: 1px;
        padding: 30px;
    }

    .title-area {
        background-color: #eeeeee;
        padding: 15px 30px;
        font-size: 20px;
        font-weight: bold;
        border-bottom: 1px solid #ccc;
    }

    .form-container {
        background-color: #fff;
        margin-top: 10px;
        padding: 20px 30px;
        display: flex;
        flex-wrap: wrap;
        align-items: center;
        gap: 20px;
        border-bottom: 1px solid #ccc;
    }

    .form-group {
        display: flex;
        flex-direction: column;
    }

    .form-group label {
        margin-bottom: 5px;
        font-weight: bold;
        font-size: 14px;
    }

    .form-group select {
        padding: 5px 10px;
        width: 160px;
        font-size: 14px;
    }

    .btn-search {
        background-color: #666;
        color: white;
        border: none;
        padding: 10px 20px;
        font-size: 14px;
        cursor: pointer;
        height: 40px;
        align-self: flex-end;
    }

    .btn-search:hover {
        background-color: #444;
    }
    .undertext{
    	color:aqua;
    }
</style>

<div class="content-area">
    <div class="title-area">
        成績参照
    </div>

    <form action="SubjectSearch.action" method="post">
        <div class="form-container">
        <div class="form-group">
        <p>科目情報</p>
        </div>

            <div class="form-group">
                <label>入学年度</label>
                <select name="entYear">
                    <option>----</option>
                    <!-- JSTLで動的に生成 -->
                </select>
            </div>

            <div class="form-group">
                <label>クラス</label>
                <select name="classId">
                    <option>----</option>
                </select>
            </div>

            <div class="form-group">
                <label>科目</label>
                <select name="subjectId">
                    <option>----</option>
                </select>
            </div>



            <div class="form-group">
                <button type="submit" class="btn-search">検索</button>
            </div>
        </div>
    </form>






<form action="StudentSearch.action" method="post">

        <div class="form-container">
        <div class="form-group">
        <p>学生情報</p>
        </div>

            <div class="form-group">
                <label>学生番号</label>
                <input type="text" name="aaa" required>
            </div>

            <div class="form-group">
                <button type="submit" class="btn-search">検索</button>
            </div>
			</div>

<div class="undertext">
<p>科目情報を選択又は学生情報を入力し検索ボタンをクリックしてください</p>
</div>
        </form>
</div>

<jsp:include page="../tool/footer.jsp" />
