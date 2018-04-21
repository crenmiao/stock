<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.duty.util.PropertyUtil"%>
<%@ include file="../global.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>


		<title>System Configuration</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		
	</head>

	<body>
		<%@ include file="head.jsp"%>

		<div class="container">
			<!-- <div class="leftbar">
			    <div class="lm01"> <img class="peptx" src="images/tximg.jpg" />
			      <div class="pepdet">
			        <p class="pepname">李小雅</p>
			        <p>李小雅</p>
			        <p>江苏话务一部三组</p>
			      </div>
			      <div class="clear"></div>
			    </div>
			    <div class="lm02">
			      <div class="title"><img class="icon" src="images/dataicon.jpg" />
			        <h2>日历</h2>
			      </div>
			      <div class="detail"> <img class="" src="images/kj_01.jpg" /> </div>
			    </div>
			    <div class="lm03">
			      <div class="title"><img style="padding-right:5px;" class="icon" src="images/weaicon.jpg" />
			        <h2>天气</h2>
			      </div>
			      <div class="detail"> <img class="" src="images/kj_02.jpg" /> </div>
			    </div>
			</div> -->
			<div class="mainbody">
				<div class="currmenu">
					<ul class="rig_nav">
						<li>
							<a href="${ctx }/admin/main">SubScription List</a>
						</li>
						<li>
							
							<a href="${ctx }/admin/config">System Configuration</a>
						</li>
					</ul>
				</div>
				<div class="rig_lm03">
					<div class="title">
						<img src="images/listicon.jpg" class="icon"
							style="padding-top: 13px;">
						<h2>
							System Configuration
						</h2>
					</div>
					<div class="detail">
						<div class="inner03">
							<form id="pageForm" action="${ctx }/admin/main" method="post">
								<table class="tabindex" width="100%" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<td>
													Email sending start time：
												</td>
												<td>
													<input type="text" style="height:30px;" name="sendtime" id="sendtime" class="Wdate" value="<%=PropertyUtil.getProperty("sendtime") %>" onFocus="WdatePicker({dateFmt:'HH:mm:ss'})"/>
												</td>
											</tr>
											<tr>
												<td></td>
												<td> 
													<div onclick="save();"style="width:100px;height:30px;line-height:28px;background:#4093D7;color:white;border-radius:5px;text-align:center;cursor:pointer;">Update time</div>
												</td>
											</tr>
											
											<tr>
												<td>
													Email title：
												</td>
												<td>
													<input type="text" style="height:30px;width:500px;" name="title" id="title"  value="<%=PropertyUtil.getProperty("sendtitle") %>" />
												</td>
											</tr>
											<tr>
												<td>
													Email Template(Content)：
												</td>
												<td>
													<textarea style="height:300px;width:500px;" id="content"><%=PropertyUtil.getProperty("sentContent") %></textarea>
												</td>
											</tr>
											<tr>
												<td></td>
												<td> 
													<div onclick="updateTem();"style="width:200px;height:30px;line-height:28px;background:#4093D7;color:white;border-radius:5px;text-align:center;cursor:pointer;">Update Email Template</div>
												</td>
											</tr>
											
											
										</table>
										<div class="fanye">
												 
										</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<%@ include file="foot.jsp"%>
	<script type="text/javascript">
		function save(id){
			$.ajax({
             type: "post",
             url: "${ctx}/admin/configSave",
             data:{sendtime:$("#sendtime").val()},
             dataType: "json",
             success: function(data){
                    if(data.ec=='success'){
                    	alert("success");
						location.reload();
                    }else if(data.ec=='nologin'){
                    	
						location = "/admin";
                    }else{
                    	alert("failure");
                    }
                    
             },
             error:function(){
             	alert("error");
             }
         });
		}
	
	
	
	function updateTem(id){
			$.ajax({
             type: "post",
             url: "${ctx}/admin/updateTem",
             data:{title:$("#title").val(),content:$("#content").val()},
             dataType: "json",
             success: function(data){
                    if(data.ec=='success'){
                    	alert("success");
						location.reload();
                    }else if(data.ec=='nologin'){
                    	
						location = "/admin";
                    }else{
                    	alert("failure");
                    }
                    
             },
             error:function(){
             	alert("error");
             }
         });
		}
	</script>
</html>
