var MemberReg = { //최상위 제이슨( 중가로에 해당되는 {} 것은 무조건 다 제이슨 처린)
    /**
     * 스크립트에서 처음 호출될 함수(초기화 함수)
     */
    init : function() {
        this.evtBind();
    },

    /**
     * 이벤트 바인딩 함수
     */
    evtBind : function() {
        $('#memberReg_saveBtn').on("click",function (){
            MemberReg.func.insertMember();
        });

        //blueimp 라이브러리(파일업로드 - 긴빠이용)
        $("#memberReg_profileImg").fileupload({
            url : '/service/file/upload?module=profile&uploadType=image',
            dataType: 'json',
            add: function(e, data){
                debugger;
                var uploadFile = data.files[0];
                var isValid = true;
                if (!(/png|jpe?g|gif/i).test(uploadFile.name)) {
                    alert('png, jpg, gif 만 가능합니다.');
                    isValid = false;
                }
                if (isValid) {
                    data.submit();
                }
            },
            done: function (e, data) {
                debugger;
                $("#profileImg").val(data.result[0].url + ":" + data.result[0].fileName);
                $(".photo").find("img").attr("src", "/service/file/fileView?fileUrl="+data.result[0].url+"&fileName="+data.result[0].fileName);
            }
        });
    },

    func: {
        insertMember: function() {
            var param = {
                'korName': $('#memberReg_korName').val(),
                'engName': $('#memberReg_engName').val(),
                'chinaName': $('#memberReg_chinaName').val(),
                'techLevel': $('#memberReg_techLevel option:selected').val(),
                'regNo1': $('#memberReg_regNo1').val(),
                'regNo2': $('#memberReg_regNo2').val(),
                'birthDate': $('#memberReg_birthDate').val(),
                'year': $('#memberReg_year').val(),
                'phoneNum': $('#memberReg_phoneNum').val(),
                "profileImg" : $("#profileImg").val()
            };
            debugger;
            $.ajax({
                type: "post",
                url: "/service/member/insertMember",
                data: param,
                success: function (res, statusText) {
                    debugger;
                    alert('회원등록이 완료되었습니다.');
                    location.href = '/member/findAll';
                },
                error: function () {
                    debugger;
                    alert('회원등록 도중 에러가 발생되었습니다.');
                }
            });
        }
    }
};