<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.duty.entity.User"%>
<%@page import="com.duty.util.UserConst"%>
<%@ include file="global.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>SUBSCRIPTION</title>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">

		<link rel="stylesheet" type="text/css" href="css/index.css">
		<% User u = (User)request.getSession().getAttribute(UserConst.USER_SFJ_KEY) ;
			String type = "";
			if(u!=null){
				type=u.getSubscription();
			}
		%>
		<c:set value="<%=type %>" var="type"></c:set>
	</head>

	<body>
		<div class="w-1200">
			 <p><a class="btnexit" onclick="logout();">logout</a></p>
			<img class="indextext" src="images/index_text.png">
			<div class="divcenter">
				<c:if test="${type==1}"> <p class="txt" style="font-weight:bold">Your subscription status: subscribed!</p></c:if>
				<c:if test="${type==0}"> <p class="txt" style="font-weight:bold">Your subscription status: unsubscribed!</p></c:if>
				  
	            <p>
	                <a class="left"  onclick="subscript(1)">SUBSCRIBE</a>
	                <a class="right"  onclick="subscript(0)">UNSUBSCRIBE</a>
	              </p>
			</div>
		</div>
	</body>
</html>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function(){
    var height=$("body").height();
    var wheight=$(".w-1200").height();
    var margintop=(height-wheight)/2;
    $(".w-1200").css("padding-top",margintop+"px");
})

var status = 0;
function subscript(type){
	if(status==1){
			return ;
	}
	var url = '';
	var tip = '';
	if(type==0){
		url = "${ctx}/unsubscript";
		tip = "Unsubscribed";
	}else if(type==1){
		url = "${ctx}/subscript";
		tip = 'Subscribed!';
	}
	status=1;
		$.ajax({
             type: "post",
             url: url,
             dataType: "json",
             success: function(data){
                    if(data.ec=='success'){
                    	mytip(tip);
                    	location.reload();
                    }else if(data.ec=='nologin'){
                    	location.href='${ctx}/login';
                    }else if(data.ec=='subscripted'){
                    	//if(type==0){
                    		
                    	//}else if(type==1){
                    		mytip('You have already subscribed!');
                    	//}
                    	
                    }else if(data.ec=='unsubscripted'){
                    	mytip('You have not yet subscribed!');	
                    }else{
                    	mytip('Option has failed');
                    }
                    status=0;
             },
             error:function(){
             	mytip("error");status=0;
             }
         });
	}
	
	function logout(){
		location.href="${ctx}/logout";
	}
	function mytip(msg){
		alert(msg);
	}
</script>
