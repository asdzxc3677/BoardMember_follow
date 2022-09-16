<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<div id="tent" class="tent"></div>
<div class="sideMenu_lyr">
	<div class="sideMenu">
		<div class="div_account">
			<div class="user">
				<span class="photo">
					<c:choose>
						<c:when test="${not empty sessionInfo.profileImg}"><img src="/service/file/fileView?fileUrl=${sessionInfo.profileImg}" alt=""></c:when>
						<c:otherwise><img src="/resources/img/blank_person.png" alt=""></c:otherwise>
					</c:choose>
				</span>
				<span class="name"><strong>${sessionInfo.nickname}</strong></span>
				<span class="type">
					<c:choose>
						<c:when test="${not empty sessionInfo}">
							<c:choose>
								<c:when test="${sessionInfo.memberType eq 'C'}">코칭회원</c:when>
								<c:otherwise>일반회원</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							로그인해주세요.
						</c:otherwise>
					</c:choose>
				</span>
			</div>
			<div class="optn">
				<c:if test="${not empty sessionInfo}">
					<button type="button" class="logout" onclick="CommonUtil.func.logout();"><span></span></button>
				</c:if>
			</div>
		</div>
		<div id="menu_area"></div>
	</div>
	<!-- <button type="button" class="aside_lyr_close"><span>닫기</span></button> -->
	<a href="javascript:void(0);" class="aside_lyr_close">닫기</a>	
</div>
<script type="text/javascript">
	// 사이드 메뉴 오픈시 활성화 (모바일에서 전체 스크롤을 Disable시켜 스크롤시 사이드 메뉴가 깨지는 현상을 방지 #50134)
	jQuery('#blind').bind("touchmove",function(event){
		if(jQuery('.aside_lyr').css('display') == 'block'){
			event.preventDefault();
		}
	});
</script>