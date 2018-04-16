<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../global.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>


		<title>SubScription List</title>

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
							SubScription List
						</h2>
					</div>
					<div class="detail">
						<div class="inner03">
							<form id="pageForm" action="${ctx }/admin/main" method="post">
								<table class="tabindex" width="100%" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<th width="10%" bgcolor="#f8f8f8" scope="col">
													<div align="center">
														ID
													</div>
												</th>
												<th width="20%" bgcolor="#f8f8f8" scope="col">
													<span class="titlab">Email</span>
												</th>
												<th width="10%" bgcolor="#f8f8f8" scope="col">
													<span class="titlab">Register Time</span>
												</th>
												<th width="10%" bgcolor="#f8f8f8" scope="col">
													<span class="titlab">Is subscribed</span>
												</th>
												
												<th width="10%" bgcolor="#f8f8f8" scope="col">
													Operation
												</th> 
											</tr>
											
											<c:forEach var="u" items="${users}" varStatus="i">
												<tr>
													<td>
														<div align="center">
															${i.index+1 }
														</div>
													</td>   
													<td class="waitcol" >
														${u.email }
													</td>
													<td class="waitcol">
														<fmt:formatDate value="${u.registtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
													</td>
													
													<td class="waitcol" bgcolor="#f8f8f8">
														<c:if test="${u.subscription==0}">
															<font style="color:red">NO</font>
														</c:if>
														<c:if test="${u.subscription==1}">
															<font style="">Yes</font>
														</c:if>
													</td>                          		                         
							                  		 
							                  		<td><a class="datacol" onclick="del('${u.id}')">Delete</a></td>	
												</tr>
											</c:forEach>
											
										</table>
										<div class="fanye">
											<div id="pagediv">
												<c:if test="${page.totleNum> 0}">
													<page:page pageSize="10" totleNum="${page.totleNum}" pageNum="${page.pageNum}"></page:page>
												</c:if>
											</div>	 
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
		function del(id){
			$.ajax({
             type: "post",
             
             url: "${ctx}/delUser?id="+id,
             dataType: "json",
             success: function(data){
                    if(data.ec=='success'){
                    	alert("Delete success");
						location.reload();
                    }else{
                    	alert("Delete failure");
                    }
                    
             },
             error:function(){
             	alert("error");
             }
         });
		}
	
	</script>
</html>
