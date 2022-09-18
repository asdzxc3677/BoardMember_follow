<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/resources/js/jquery.js"></script>
    <script type="text/javascript" src="/resources/js/jquery-ui-1.11.0.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.fileupload.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.fileupload-ui.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.fileupload-process.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.fileupload-image.js"></script>
    <script type="text/javascript" src="/resources/js/memberReg.js"></script>
    <link rel="stylesheet" href="../../../resources/css/bootstrap.min.css">
    <script type="text/javascript">
        $(document).ready(function() { // $(document).ready 문서가 완전히 로딩이 된후에 실행되는것
            MemberReg.init();
        });
    </script>
</head>
<body>
<div class="container">
    <h2 class="display-4 fw-normal">사원등록 페이지</h2>
    <div class="py-5 text-center">
        <form action="/member/insertMember" method="post" id="memberForm" enctype="multipart/form-data">
            <input class="form-control mb-2" name="korName" type="text" placeholder="한글이름"><br>
            <input class="form-control mb-2" name="engName" type="text" placeholder="영문이름"><br>
            <input class="form-control mb-2" name="chinaName" type="text" placeholder="한문이름"><br>
            <select name="techLevel">
                <option value="A">초급</option>
                <option value="B">중급</option>
                <option value="C">고급</option>
            </select>
            <input class="form-control mb-2" name="regNo1" type="text" placeholder="주민등록번호 앞자리">
            <input class="form-control mb-2" name="regNo2"type="text" placeholder="주민등록번호 뒷자리"><br>
            <input class="form-control mb-2" name="birthDate" type="text" placeholder="생년월일"><br>
            <input class="form-control mb-2" name="year" type="text" placeholder="연차"><br>
            <input class="form-control mb-2" name="phoneNum" type="text" placeholder="연락처"><br>
            첨부파일:<input type="file" name="profileImg">
            <div class="photo"><img src="" alt=""></div>
            <input class="btn btn-primary" type="submit" id="memberReg_saveBtn" value="저장">
            <br>
        </form>
    </div>
</div>
</body>
</html>
