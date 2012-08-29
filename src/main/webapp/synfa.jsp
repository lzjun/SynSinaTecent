<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.23.custom.min.js"></script>
<style type="text/css">
.left {
	float: left;
	display: inline;
	width: 32%;
}

.center {
	float: left;
	display: inline;
	width: 33%;
}

.right {
	float: right;
	display: inline;
	width: 35%;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {

		$("#sendTarget").change(function() {
			var value = $("#sendTarget").val();
			alert(value);
			
			$.post("TecentWeibo?operator=send", {
				vender : "tecent",
				weiboContent:$("#weiboContent").val()
			}, function(data) {

			});
			
		});

		$("#linkTecent").click(function() {

			alert("腾讯微博");

			$.post("Login", {
				vender : "tecent"
			}, function(data) {
				alert(data);
				location.href=data;
			});

		});

		$("#linkSina").click(function() {

			alert("新浪微博");
			$.post("Login", {
				vender : "sina"
			}, function(data) {
				alert(data);
				location.href=data;
			});

		});

	});
</script>
</head>

<body>





	<div style="width: 100%; height: 100%">
		<div class="left">
			<button id="linkSina" style="text-align: left">连接新浪微博</button>
		</div>
		<div class="center">
			<!--<form action="TecentWeibo?operator=send" method="post"> -->
			<textarea style="width: 100%" rows="5" id="weiboContent"></textarea>
			<br /> <select id="sendTarget" style="text-align: right">
				<option value="all">--发送到--</option>
				<option value="all">所有</option>
				<option value="sina">新浪</option>
				<option value="tecent">腾讯</option>
			</select>

		</div>





		<div class="right">
			<div id="linkTecent" style="text-align: right;">
				<button>连接腾讯微博</button>
			</div>

		</div>
	</div>








</body>



</html>