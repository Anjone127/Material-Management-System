require(['component/iframeLayer', 'common/util', 'common/http', 'layer1', 'jquery.validate', 'jquery.serialize'], function (layer, util, http) {

	init();

	function init() {
    	var str=$('#userRoleId1').val();
		if( str !=null && str !=""){
			$("#userRoleId").val(str);
		}
		formValid();
		bind();
	}

	function formValid() {
		$('#userForm').validate({
			rules: {
				userId: {
					required: true,
					rangelength:[6,255]
				},
				userPassword: {
					rangelength:[6,18]
				},            
				userName: {
					required: true,
					rangelength:[1,255]
				}
			},
			showErrors: function (errorMap, errorList) {
				for (var i in errorMap) {
					layer.tips(errorMap[i], $('#userForm input[name=' + i + ']'), {
						tips: 3,
						tipsMore: true,
						ltype: 0
					});
				}
			},
			submitHandler: function () {
				var formParam = $('#userForm').serializeObject();
				http.httpRequest({
					url: '/system/user/save',
					serializable: true,
					data: formParam,
					type: 'post',
					success: function (data) {
						if(data=="fail"||data.state=="error"){
							layer.msg(data.message, {time: 1000}, function () {
							});
						}else{
							layer.msg(data.message, {time: 1000}, function () {
								layer.close({reload: true});
							});
						}
					}
				})
			}
		})
	}

	function bind() {
		util.bindEvents([{
			el: '#cancel',
			event: 'click',
			handler: function () {
				layer.close();
			}
		}])
	}

})