<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
	<h1>用户登录</h1>
	<!-- 数据私密、太长要使用post传输给服务器 -->
	<form action="/user/handle_login" method="post">
		<p>请输入用户名:</p>
		<p>
			<input name="username" />
		</p>
		<p>请输入密码:</p>
		<p>
			<input name="password" />
		</p>
		<p>
			<input type="submit" value="登录" />
		</p>
	</form>

</body>
</html>