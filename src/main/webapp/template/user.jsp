<%@ page language="java" pageEncoding="UTF-8"%> 
<html>
<body>
<h2>user</h2>
<p>
	这是user，有user角色就可以访问
	<form action="/logout" method="POST">
		<input name="_csrf" value="${_csrf.token}" style=" display:none;" />
		<input type="submit" value="退出登录" >
	</form>
</p>

</body>
</html>
	