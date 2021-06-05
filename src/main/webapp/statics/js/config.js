	//login page images round robin
	var currentindex = 1;
	
	$(function(){
		$("#flash").css("background-image","url("+$("#flash1").attr("bgUrl")+")");//设置banner背景颜色名称 这里的flash就是div的ID
	});		

	
	function changeflash(i) {	
		currentindex=i;
		for (j=1;j<=2;j++){
			if (j==i){
				$("#flash"+j).fadeIn("normal");
				$("#flash"+j).css("display","block");
				$("#f"+j).removeClass();
				$("#f"+j).attr("class","dq");
				//alert("#f"+j+$("#f"+j).attr("class"))
				$("#flash").css("background-image","url("+$("#flash"+j).attr("bgUrl")+")");
			}else{
				$("#flash"+j).css("display","none");
				$("#f"+j).removeClass();
				$("#f"+j).attr("class","no");
			}
		}
	}
	function startAm(){
		timerID = setInterval("timer_tick()",2000);
	}
	function stopAm(){
		clearInterval(timerID);
	}
	function timer_tick() {
		currentindex = currentindex >=2 ? 1 : currentindex + 1;
		changeflash(currentindex);
	}
	$(document).ready(function(){
		$(".flash_bar div").mouseover(function(){
			stopAm();
		}).mouseout(function(){
			startAm();
		});
		startAm();
	});
	
	// input
	$(function(){
		$(":input.user").focus(function(){
			$(this).addClass("userhover");                          
		}).blur(function(){
			$(this).removeClass("userhover")
		});
		$(":input.pwd").focus(function(){
			$(this).addClass("pwdhover");                          
		}).blur(function(){
			$(this).removeClass("pwdhover")
		});

		$(".user").focus(function(){
			var username = $(this).val();
			if(username == ''){
			   $(this).val('');
			}
		 });
		 $(".user").blur(function(){
			var username = $(this).val();
			if(username == ''){
			   $(this).val('');
			}
		 });

		 $(".pwd").focus(function(){
			var password = $(this).val();
			if(password == ''){
			   $(this).val('');
			}
		 });
		 $(".pwd").blur(function(){
			var password = $(this).val();
			if(password == ''){
			   $(this).val('');
			}
		 });
	
	});

	/**
	 * User login verification
	 */

	function checkLogin() {
		var userName = $("#userName").val(); // Username
		var userPwd = $("#userPwd").val(); //password

		if (isEmpty(userName)) {
			$("#msg").html("Username can't be empty");
			return;
		}
		if (isEmpty(userPwd)) {
			$("#msg").html("Password can't be empty");
			return;
		}

		$("#loginForm").submit();
	}
