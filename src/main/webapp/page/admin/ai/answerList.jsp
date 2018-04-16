<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>


		<title>律师解答列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
		<%@ include file="head.jsp"%>

		<div class="container">
			<div class="leftbar">
			     <!--<div class="lm01"> <img class="peptx" src="images/tximg.jpg" />
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
			    </div> -->
			</div>
			<div class="mainbody">
				<div class="currmenu">
					<ul class="rig_nav">
						<li>
							<a href="${ctx }/cooperation/moneyRecord">资金流水</a>
						</li>
						<li>
							<a href="${ctx }/cooperation/answerList">律师解答</a>
						</li>
						<li>
							<a href="${ctx }/cooperation/problemList">问题列表</a>
						</li>
					</ul>
				</div>
				<div class="rig_lm03">
					<div class="title">
						<img src="images/listicon.jpg" class="icon"
							style="padding-top: 13px;">
						<h2>
							律师解答列表
						</h2>
					</div>
					<div class="detail">
						<div class="inner03">
							<form id="pageForm" action="${ctx }/cooperation/answerList" method="post">
								<table class="tabindex" width="100%" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<th width="10%" bgcolor="#f8f8f8" scope="col">
													<div align="center">
														序号
													</div>
												</th>
											 
												<th width="22%" bgcolor="#f8f8f8" scope="col">
													<span class="titlab">问题	</span>
												</th>
												<th width="22%" bgcolor="#f8f8f8" scope="col">
													<span class="titlab">解答	</span>
												</th>
												<th width="22%" bgcolor="#f8f8f8" scope="col">
													<span class="titlab">律师	</span>
												</th>
												<th width="7%" bgcolor="#f8f8f8" scope="col">
													<span class="titlab">点赞</span>
												</th>
												<th width="7%" bgcolor="#f8f8f8" scope="col">
													<span class="titlab">点差</span>
												</th>
												<th width="7%" bgcolor="#f8f8f8" scope="col">
													<span class="titlab">咨询次数</span>
												</th>
												<!-- <th width="7%" bgcolor="#f8f8f8" scope="col">
													<span class="titlab">状态</span>
												</th>-->
												<th width="9%" bgcolor="#f8f8f8" scope="col">
													操作
												</th> 
											</tr>
											
											<c:forEach var="l" items="${beans}" varStatus="i">
											<tr>
												<td>
													<div align="center">
														${i.index+1 }
													</div>
												</td>   
												<td title="${l.problem }">${l.problem }</td>	                          		                         
						                  		<td title="${l.answer}">${l.answer}</td>	                          		                         
						                  		<td title="${l.lawyername }">${l.lawyername }</td>	         
												
												<td class="waitcol" bgcolor="#f8f8f8">
													${l.laudcount }
												</td>                          		                         
						                  		<td >${l.badcount }</td>	                          		                         
						                  		<td >${l.askcount }</td>	 
						                  		<td><a class="datacol">查看</a></td>	
											</tr>
											</c:forEach>
											
										</table>
										<div class="fanye">
											<div id="pagediv">
												<c:if test="${totalSize> 0}">
													<page:page pageSize="10" totleNum="${totalSize}" pageNum="${pageNum}"></page:page>
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
</html>
