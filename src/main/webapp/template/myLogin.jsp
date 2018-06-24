<%@ page language="java" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>后台登录</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="../css/style.css" tppabs="../css/style.css" />
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="../js/jquery.js"></script>
<script src="../js/verificationNumbers.js" tppabs="../js/verificationNumbers.js"></script>
<script src="../js/Particleground.js" tppabs="../js/Particleground.js"></script>
<script>
$(document).ready(function() {
  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  });
  
  var errorMessage = "${error_message}";
  if(errorMessage !== null && errorMessage !== "" && errorMessage !== undefined){
	  $("#errorTip").text(errorMessage);
	  $("#errorTip").css("display", "block");
  } else {
	  $("#errorTip").css("display", "none");
  }
});
</script>
</head>
<body>
	<form action="/login" method="POST">
		<dl class="admin_login">
		 <dt>
		  <strong>权限管理系统</strong>
		  <em>Management System</em>
		 </dt>
		  <dd>
			<div id="errorTip" style="color: red; position: relative; left: 90px; display: none;">
			</div>
 		 </dd>
		 <dd class="user_icon">
		  <input type="text" placeholder="账号" class="login_txtbx" name="username"/>
		 </dd>
		 <dd class="pwd_icon">
		  <input type="password" placeholder="密码" class="login_txtbx" name="password" />
		 </dd>
		 <dd>
		  <input type="submit" value="立即登陆" class="submit_btn" />
		 </dd>
		</dl>
		<input name="_csrf" value="${_csrf.token}" style=" display:none;" />
	</form>
</body>
</html>
