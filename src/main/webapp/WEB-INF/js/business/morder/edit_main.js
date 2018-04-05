require(['component/iframeLayer', 'common/util', 'common/http', 'layer1', 'jquery.validate', 'jquery.serialize'], function (layer, util, http) {

	init();

	function init() {
		formValid();
		bind();
	}

	function formValid() {
		$('#morderForm').validate({
			rules: {
				supplierNumber: {
					required: true,
					rangelength:[1,32]
				},
				address: {
					required: true,
					rangelength:[1,200]
				},
			},
			showErrors: function (errorMap, errorList) {
				for (var i in errorMap) {
					layer.tips(errorMap[i], $('#morderForm input[name=' + i + ']'), {
						tips: 3,
						tipsMore: true,
						ltype: 0
					});
				}
			},
			submitHandler: function () {
				var formParam = $('#morderForm').serializeObject();
				http.httpRequest({
					url: '/business/morder/save',
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