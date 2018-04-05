require(['component/iframeLayer','component/dataTable', 'common/util', 'common/http', 'layer1', 'jquery.validate', 'jquery.serialize'], function (layer, dataTable, util, http) {

	init();

	function init() {
		//初始化产品类别
		var str=$('#typeidhidden').val();
		if( str !=null && str !=""){
			$("#typeid").val(str);
		}
		initMaterialTable();
		initDetailTable();
		formValid();
		bind();
	}

	function updateCost(){
		http.httpRequest({
			url: '/info/product/getCost',
			data: {id : $("#productId").val()},
			type: 'post',
			success: function (data) {
				$("#cost").val(data);
			}
		});
	}

	var material_table;
	var detail_table;

	function initMaterialTable() {
		material_table = dataTable.load({
			el: '#material-table',
			ajax:{ 
				url:'/info/material/list.json',
				type:'post',
				data: function ( dto ) {
					dto.name = $("#NAME").val();
					dto.type = $("#typeSelect").val();
				}
			},
			type:'post',
			columns: [
				{data: 'name'},
				{data: 'price'},
				{data: 'description'},
				{data: null , width:'80px'}
				],
				columnDefs: [{
					targets: 3,
					render: function (data, type, row, meta) {
						return "数量：<input style=\"width:30px\" id=" + data.id +" value='1' type='text'> <button class=\"btn btn-xs btn-primary js-save-detail\">添加零部件</button>";
					}
				}
				]
		})
	}

	function initDetailTable() {
		detail_table = dataTable.load({
			el: '#detail-table',
			ajax:{ 
				url:'/info/product/detailList.json',
				type:'post',
				data: function ( dto ) {
					dto.productId = $("#productId").val();
				}
			},
			type:'post',
			columns: [
				{data: 'name'},
				{data: 'price'},
				{data: 'amount'},
				{data: null }
				],
				columnDefs: [{
					targets: 2,
					render: function (data, type, row, meta) {
						return "<input style='width:25px;' id='p"+row.id+"' value='"+ data +"'>";
					}
				},{
					targets: 3,
					render: function (data, type, row, meta) {
						return "<button class=\"btn btn-xs btn-primary js-update-amount\">修改数量</button>";
					}
				}
				]
		})
	}

	function formValid() {
		$('#productForm').validate({
			rules: {
				name: {
					required: true,
					rangelength:[1,32]
				},
				number: {
					required: true,
					rangelength:[1,32]
				},
				price: {
					required: true,
					number: true,
					rangelength:[1,32]
				},
				cpuNumber: {
					required: true,
					rangelength:[1,32]
				},
				radiatorNumber: {
					required: true,
					rangelength:[1,32]
				},
				mainboardNumber: {
					required: true,
					rangelength:[1,32]
				},
				graphicsCardNumber: {
					required: true,
					rangelength:[1,32]
				},
				memoryBankNumber: {
					required: true,
					rangelength:[1,32]
				},
				hardDriveNumber: {
					required: true,
					rangelength:[1,32]
				},
				caseNumber: {
					required: true,
					rangelength:[1,32]
				},
				powerSupplyNumber: {
					required: true,
					rangelength:[1,32]
				},
			},
			showErrors: function (errorMap, errorList) {
				for (var i in errorMap) {
					layer.tips(errorMap[i], $('#productForm input[name=' + i + ']'), {
						tips: 3,
						tipsMore: true,
						ltype: 0
					});
				}
			},
			submitHandler: function () {
				var formParam = $('#productForm').serializeObject();
				http.httpRequest({
					url: '/info/product/save',
					serializable: true,
					data: formParam,
					type: 'post',
					success: function (data) {
						$("#productId").val(data.state);
						layer.msg(data.message, {time: 1000}, function () {});
					}
				})
			}
		})
	}

	function bind() {
		util.bindEvents([{
			el: '.js-save-detail',
			event: 'click',
			handler: function () {
				var data = material_table.row($(this).closest('td')).data();
				var amount = $("#"+data.id).val();
				layer.confirm('确认要添加 ' + data.name + ' ' + amount +'件?', {icon: 2, title: '提示'}, function (index) {
					http.httpRequest({
						url: '/info/product/saveDetail',
						data: {productId : $("#productId").val() , materialId : data.id , amount : amount},
						type: 'post',
						success: function (data) {
							layer.msg(data.message, {time: 1000}, function () {});
						}
					});
				});
			}
		},{
			el: '.js-material-back',
			event: 'click',
			handler: function () {
				updateCost();
				$("#material-div").hide();
				$("#product-div").show();
			}
		},{
			el: '.js-add-material',
			event: 'click',
			handler: function () {
				if($("#productId").val()==""){
					layer.msg("请先保存产品信息!", {time: 2000}, function () {});
				}else{
					$("#product-div").hide();
					$("#material-div").show();
				}
			}
		},{
			el: '.js-show-detail',
			event: 'click',
			handler: function () {
				if($("#productId").val()==""){
					layer.msg("请先保存产品信息!", {time: 2000}, function () {});
				}else{
					detail_table.ajax.reload(null,false);
					$("#product-div").hide();
					$("#detail-div").show();
				}
			}
		},{
			el: '.js-material-search',
			event: 'click',
			handler: function () {
				material_table.ajax.reload(null,false);
			}
		},{
			el: '#cancel',
			event: 'click',
			handler: function () {
				layer.close();
			}
		},{
			el: '.js-detail-back ',
			event: 'click',
			handler: function () {
				updateCost();
				$("#product-div").show();
				$("#detail-div").hide();
			}
		},{
			el: '.js-update-amount',
			event: 'click',
			handler: function () {
				var data = detail_table.row($(this).closest('td')).data();
				var amount = $("#p"+data.id).val();
				layer.confirm('确认要修改为 ' + amount +' 件?', {icon: 2, title: '提示'}, function (index) {
					http.httpRequest({
						url: '/info/product/saveDetail',
						data: {id : data.id , amount : amount},
						type: 'post',
						success: function (data) {
							detail_table.ajax.reload();
							layer.msg(data.message, {time: 1000}, function () {});
						}
					});
				});
			}
		}])
	};
});