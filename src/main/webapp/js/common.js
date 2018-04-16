


var commonFunc ={};

commonFunc.paging = function(curr,pages,url,layer,pageId){
	laypage({
	    cont: 'demo1'
	    ,pages: pages
	    ,groups: 5 //连续显示分页数
	    ,skip: false,
	     curr:curr,
	     jump: function(obj, first){
		    //得到了当前页，用于向服务端请求对应数据 
		    $("#"+pageId+" a").click(function(){
			  	var page = $(this).attr("data-page");
			  	location.href="url"+page;
			})
		  }
	  });
}

commonFunc.tips=function(content){
	layer.open({
		content:content,
		offset: 'rb',
		shade: 0,
		title:'提示信息',
		btn:[],
		anim:6
	});

}
commonFunc.upload = function(data, id, type, callback, index, size, url) {
	$('#' + id).live('change', function() {
		$.ajaxFileUpload( {
			url : url, //用于文件上传的服务器端请求地址
			type : 'post',
			allowType : type,
			allowSize : size,
			data : data, //此参数非常严谨，写错一个引号都不行
			secureuri : false, //一般设置为false
			fileElementId : id, //文件上传空间的id属性  <input type="file" id="file" name="file" />
			dataType : 'json', //返回值类型 一般设置为json
			success : function(data, status) //服务器成功响应处理函数
			{
				callback(data, index);
			},
			error : function(data, status, e)//服务器响应失败处理函数
			{
				alert(e);
			}
		})
	})

}

var func={
		ajax:function(url,data,callback){
			$.ajax({
				url:url,
				type: 'POST',
				data:data,
				dataType:'json',
				success:function(data){
					callback(data);
		//			if(data.ec==success_code){//成功
		//				//layer.alert(data.em);
		//				callback(data.cd);
		//			}else if(data.ec==error_code){//失败
		//				layer.alert(data.em);
		//			}else if(data.ec==not_full){//数据不全
		//				layer.alert("您输入的数据不完整");
		//			}else if(data.ec==null_code){//暂无数据
		//				layer.alert("暂无数据");
		//			}else{
		//				layer.alert(data.em+','+data.ec);
		//			}
				},
				error:function(){
					alert("网络连接错误！")
				}
			})
		},	
	
	serializeObject:function(form){ 
		var o ={}; 
		$.each(form.serializeArray(),function(index){ 
			if(o[this['name']]){ 
				o[this['name']] = o[this['name']] +","+this['value']; 
			}else{ 
				o[this['name']] = this['value']; 
			} 
		}); 
		return o; 
	}
}

var StringUtil={};

StringUtil.isEmpty=function(str){
	if(str==''||str=='undefined'||str==undefined||str==null){
		return true;
	}else {
		return false;
	}
}

var openFunc={};

openFunc.left=function(news){
	layer.open({
		content: news,
		offset: 'rb',
		shade: 0,
		title:'提示信息',
		btn:[],
		anim:6 
	});
}

openFunc.add=function(title,area,content,callback){
	layer.open({
		  type: 2,
		  title: title,
		  shadeClose: false,
		  shade: 0.3,
		  area: area,
		  content:content ,
		  btn:['保存','取消']
		  ,yes: function(index,layero ){
				callback(index,layero);
		    	
		  }
		  ,btn2: function(index){
		    layer.closeAll();
		  }
	});
}