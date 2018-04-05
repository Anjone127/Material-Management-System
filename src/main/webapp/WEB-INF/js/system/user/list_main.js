require(['component/iframeLayer','component/dataTable','common/util','common/http','handlebars','jquery'], function (layer, dataTable, util, http, handlebars) {

	init();
	/**
	 * 初始化函数集合
	 */
	function init() {
		initDataTable();
		bind();
	}


	var table;

	/**
	 * 初始化dataTable
	 */
	function initDataTable() {
		var tpl = $('#tpl').html();
		var template = handlebars.compile(tpl);
		table = dataTable.load({
			el: '#user-table',
			ajax:{ 
				url:'/system/user/list.json',
				type:'post',
				data: function ( dto ) {
					dto.userId = $("#userId").val();
					dto.userName = $("#userName").val();
					dto.userRoleId = $("#typeSelect").val();
				}
			},
			columns: [
				{data: 'userId'},
				{data: 'userName'},
				{data: 'userRoleId'},
				{data: null}
				],
				columnDefs: [
					{
						targets: 2,
						render: function (data, type, row, meta) {
							if(row.userRoleId==null) return "";
							return $("#"+row.userRoleId).text();
						}
					},{
						targets: 3,
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
					title: '新增用户',
					area: ['628px', '300px'],
					content: '/system/user/show',
					callback: function (data) {
						if (data.reload) {
							table.ajax.reload();
						}
					}
				})
			}
		}, {
			el: '.js-edit',
			event: 'click',
			handler: function () {
				var data = table.row($(this).closest('td')).data();
				layer.dialog({
					title: '编辑用户',
					area: ['628px', '300px'],
					content: '/system/user/show?id=' + data.id,
					callback: function (data) {
						if (data.reload) {
							table.ajax.reload();
						}
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
						url: '/system/user/delete',
						data: {id: data.id},
						type: 'post',
						success: function (data) {
							if (data.state == 'success') {
								layer.msg(data.message, {time: 1000}, function () {
									table.ajax.reload();
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


