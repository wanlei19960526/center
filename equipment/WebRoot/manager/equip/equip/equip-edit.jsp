<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/core.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>讯牛后台</title>
	</head>
	<body>
		<div class="x-body">
			<form class="layui-form">
				<input type="hidden" id="equipId" name="equipId" value="${e.equipId}" />
				<div class="layui-form-item">
					<label for="equipName" class="layui-form-label">
						<span class="x-red">*</span>设备名
					</label>
					<div class="layui-input-inline">
						<input type="text" id="equipName" name="equipName"
							value="${e.equipName}" lay-verify="equipName" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label for="typeId" class="layui-form-label">
						<span class="x-red">*</span>设备类别
					</label>
					<div class="layui-input-inline">
						<select name="typeId" id="typeId" lay-verify="typeId" lay-search="">
							<option value="">请选择设备类别</option>
							<c:forEach var="t" items="${types}">
								<c:if test="${t.typeId == e.typeId}">
									<option selected="selected" value="${t.typeId}">${t.typeName}</option>
								</c:if>
								<c:if test="${t.typeId != e.typeId}">
									<option value="${t.typeId}">${t.typeName}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label for="equipPrice" class="layui-form-label">
						<span class="x-red">*</span>设备价格
					</label>
					<div class="layui-input-inline">
						<input type="text" id="equipPrice" value="${e.equipPrice}"
							name="equipPrice" lay-verify="equipPrice" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label for="equipProducer" class="layui-form-label">
						<span class="x-red"></span>设备厂家
					</label>
					<div class="layui-input-inline">
						<input type="text" id="equipProducer" value="${e.equipProducer}"
							name="equipProducer" lay-verify="equipProducer" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label for="remark" class="layui-form-label">
						<span class="x-red"></span>备注
					</label>
					<div class="layui-input-inline">
						<input type="text" id="remark" name="remark" lay-verify="remark"
							autocomplete="off" value="${e.remark}" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_repass" class="layui-form-label">
					</label>
					<button class="layui-btn" lay-filter="add" lay-submit="">
						提交
					</button>
				</div>
			</form>
		</div>
		<script>
	layui.use( [ 'form', 'layer' ], function() {
		$ = layui.jquery;
		var form = layui.form, layer = layui.layer;
		//自定义验证规则
			form.verify( {
				equipName : function(value) {
					if (value.length < 1) {
						return '设备名至少得写吧';
					}
				},
				typeId : function(value) {
					if (value < 1) {
						return '设备类别选一个吧';
					}
				},
				equipPrice : function(value) {
					if (value < 1) {
						return '设备价格至少得写吧';
					}
				}
			});
			//监听提交
			form.on('submit(add)', function(data) {
				var equipId = $("#equipId").val();
				if (equipId == '') {//添加
						$.ajax( {
							url : "${ctx}/equip/equip/add",
							type : "post",
							data : JSON.stringify(data.field),
							contentType : "application/json; charset=utf-8",//后台接受标准的json格式数据
							dataType : "json",
							success : function(result) {
								if (result.code == 0) {
									layer.alert(result.msg, {
										icon : 6
									}, function() {
										window.parent.location.reload();
										var index = parent.layer
												.getFrameIndex(window.name);// 获得frame索引
											parent.layer.close(index);//关闭当前frame
										});
								} else {
									layer.alert(result.msg, {
										icon : 5
									});
								}
							}
						});
					} else {//修改
						$.ajax( {
							url : "${ctx}/equip/equip/update",
							type : "post",
							data : JSON.stringify(data.field),
							contentType : "application/json; charset=utf-8",//后台接受标准的json格式数据
							dataType : "json",
							success : function(result) {
								if (result.code == 0) {
									layer.alert(result.msg, {
										icon : 6
									}, function() {
										window.parent.location.reload();
										var index = parent.layer
												.getFrameIndex(window.name);// 获得frame索引
											parent.layer.close(index);//关闭当前frame
										});
								} else {
									layer.alert(result.msg, {
										icon : 5
									});
								}
							}
						});
					}
					return false;
				});
		});
</script>
	</body>
</html>