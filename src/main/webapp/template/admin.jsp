<%@ page language="java" pageEncoding="UTF-8"%> 
<html>
<body>
	<h2>admin</h2>
	<p>
		这是admin，只有admin角色才可以访问
	</p>
	<form action="/logout" method="POST">
		<input name="_csrf" value="${_csrf.token}" style=" display:none;" />
		<input type="submit" value="退出登录" >
	</form>
</body>
</html>
	