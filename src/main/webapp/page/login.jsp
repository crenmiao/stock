<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="global.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Login</title>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">

		<link rel="stylesheet" type="text/css" href="css/login.css">

	</head>

	<body>
		<div class="w-1200">
          <img class="logintext" src="images/login_text.png">
          <div class="divlogin">
            <p><input type="text" id="email" name="email" placeholder="Enter your email"></p>
            <p><input type="password" id="pwd" name="pwd" placeholder="Enter your password"></p>
            <p style="margin-top:30px;"><a class="btn" onclick="login();">login</a></p>
            <p><a class="btn"  onclick="register();">register</a></p>
          </div>
      	</div>
	</body>
</html>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/plug-in/layer/layer/layer.js"></script>
<script  type="text/javascript">
	var status = 0;
	function login(){
		if(status==1){
			return ;
		}
		var email = $("#email").val();
		var pwd = $("#pwd").val();
		
		var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
	　　
	　　if(email == ""){ //输入不能为空
	　　　　mytip("Please enter the email!");
	　　　　return false;
	　　}else if(!reg.test(email)){ //正则验证不通过，格式不对
	　　　　mytip("Please enter the correct email!");
	　　　　return false;
	　　}
	
		if(pwd == ""){ //输入不能为空
	　　　　mytip("Please enter the password!");
	　　　　return false;
	　　}
		status=1;
		$.ajax({
             type: "post",
             url: "${ctx}/loginSubmit",
             data: {email:email, pwd:pwd},
             dataType: "json",
             success: function(data){
                    if(data.ec=='noregister'){
                    	mytip('You have not registered, please register first!');
                    }else if(data.ec=='success'){
                    	if(data.em=='1'){
                    		location.href='${ctx}/index';
                    	}else if(data.em=='2'){
                    		location.href='${ctx}/admin/main';
                    	}
                    	
                    }else{
                    	mytip('Login failure');
                    }
                    status=0;
             },
             error:function(){
             	mytip("error");status=0;
             }
         });
		
	}
	
	function register(){
		if(status==1){
			return ;
		}
		var email = $("#email").val();
		var pwd = $("#pwd").val();
		
		var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
	　　
	　　if(email == ""){ //输入不能为空
	　　　　mytip("Please enter the email!");
	　　　　return false;
	　　}else if(!reg.test(email)){ //正则验证不通过，格式不对
	　　　　mytip("Please enter the correct email!");
	　　　　return false;
	　　}
	
		if(pwd == ""){ //输入不能为空
	　　　　mytip("Please enter the password!");
	　　　　return false;
	　　}
		$.ajax({
             type: "post",
             url: "${ctx}/register",
             data: {email:email, pwd:pwd},
             dataType: "json",
             success: function(data){
                    if(data.ec=='registered'){
                    	mytip('Your email has been registered, please log in directly!');
                    }else if(data.ec=='success'){
                    	location.href='${ctx}/index';
                    }else{
                    	mytip('Register has failed');
                    }
                    status=0;
             },
             error:function(){
             	mytip("error");status=0;
             }
         });
	}
	
	function mytip(msg){
		alert(msg);
	}
</script>
