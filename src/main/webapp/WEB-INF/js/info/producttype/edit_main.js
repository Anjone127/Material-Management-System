require(['component/iframeLayer', 'common/util', 'common/http', 'layer1', 'jquery.validate', 'jquery.serialize'], function (layer, util, http) {


	init();

	function init() {
		formValid();
		bind();
	}

	function formValid() {
		$('#productTypeForm').validate({
			rules: {
				name: {
					required: true,
					rangelength:[1,32]
				},
				number: {
					required: true,
					rangelength:[1,32]
				},
				description: {
					rangelength:[0,100]
				}
			},
			showErrors: function (errorMap, errorList) {
				for (var i in errorMap) {
					layer.tips(errorMap[i], $('#productTypeForm input[name=' + i + ']'), {
						tips: 3,
						tipsMore: true,
						ltype: 0
					});
				}
			},
			submitHandler: function () {
				var formParam = $('#productTypeForm').serializeObject();
				http.httpRequest({
					url: '/info/producttype/save',
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