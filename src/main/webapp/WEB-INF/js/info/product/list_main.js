require(['component/iframeLayer','component/dataTable','common/util','common/http','handlebars','jquery'], function (layer, dataTable, util, http, handlebars) {

	init();

	function init() {
		initDataTable();
		bind();
	}

	var table;

	function initDataTable() {
		var tpl = $('#tpl').html();
		var template = handlebars.compile(tpl);
		table = dataTable.load({
			el: '#product-table',
			ajax:{ 
				url:'/info/product/list.json',
				type:'post',
				data: function ( dto ) {
					dto.name = $("#name").val();
					dto.number = $("#number").val();
					dto.typeid = $("#typeSelect").val();
				}
			},
			type:'post',
			columns: [
				{data: 'number'},
				{data: 'name'},
				{data: 'price'},
				{data: 'typeid'},
				{data: 'description' },
				{data: null }
				],
				columnDefs: [
					{
						targets: 3,
						render: function (data) {
							if(data=="")
								return "未分类";
							return $("#"+data).text();
						}
					},{
						targets: 5,
						render: function (data, type, row, meta) {
							var context = {
									func: [
										{
											'text': '编辑',
											'class': 'btn btn-xs btn-primary js-edit'
										},
										{
											'text': '删除',
											'class': 'btn btn-xs btn-danger js-delete'
										}
										]
							};
							return template(context);
						}
					}
					]
		})
	}

	function bind() {
		util.bindEvents([{
			el: '.js-add',
			event: 'click',
			handler: function () {
				layer.dialog({
					title: '新增产品类别',
					area: ['620px', '530px'],
					content: '/info/product/show',
					callback: function (data) {
						table.ajax.reload();
					}
				})
			}
		}, {
			el: '.js-edit',
			event: 'click',
			handler: function () {
				var data = table.row($(this).closest('td')).data();
				layer.dialog({
					title: '编辑产品',
					area: ['620px', '530px'],
					content: '/info/product/show?id=' + data.id,
					callback: function (data) {
						table.ajax.reload();
					}
				})
			}
		}, {
			el: '.js-delete',
			event: 'click',
			handler: function () {
				var data = table.row($(this).closest('td')).data();
				layer.confirm('确定要删除吗?', {icon: 2, title: '提示'}, function (index) {
					http.httpRequest({
						url: '/info/product/delete',
						data: {id: data.id},
						type: 'post',
						success: function (data) {
							if (data.state == 'success') {
								layer.msg(data.message, {time: 1000}, function () {
									table.ajax.reload(null,false);
								});
							}else{
								layer.msg(data.message, {time: 1000}, function () {
								});
							}
						}
					});
				});
			}
		}, {
			el: '.js-search',
			event: 'click',
			handler: function () {
				table.ajax.reload(null,false);
			}
		}])
	};
});