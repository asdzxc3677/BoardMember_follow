var MemberReg = { //최상위 제이슨( 중괄호에 해당되는 {} 것은 무조건 다 제이슨 처린)
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
        $('#memberForm').on('submit', function(e){
            debugger;
        });
    },

    func: {
    }
};