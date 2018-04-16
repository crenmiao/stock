<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

	<script>
		//$(".currmenu li").click(function(){
			//$(".rig_nav li").removeClass("rig_seleli");
			//$(this).addClass("rig_seleli");
		//})
		
		var menu = "${menutab}";
		var menunum = menu*1-1;
		if(menu!=null&&menu!=''){
			$($(".rig_nav li")[menunum]).attr("class","rig_seleli");
		}else{
			$($("#rig_nav li")[0]).attr("class","rig_seleli");
		}
		
	</script>