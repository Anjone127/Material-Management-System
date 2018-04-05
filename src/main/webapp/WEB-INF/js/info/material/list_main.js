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
            el: '#material-table',
            ajax:{ 
				url:'/info/material/list.json',
				type:'post',
				data: function ( dto ) {
					dto.name = $("#name").val();
					dto.number = $("#number").val();
					dto.supplierNumber = $("#supplierNumber").val();
					dto.type = $("#typeSelect").val();
				}
			},
            type:'post',
            columns: [
                {data: 'number'},
                {data: 'supplierNumber'},
                {data: 'name'},
                {data: 'price'},
                {data: 'type'},
                {data: 'description' , width:'30%'},
                {data: null}
            ],
            columnDefs: [
            	{
            		targets: 4,
            		render: function (data) {
            			str = "处理器";
            			switch(data){
            				case "1":str="散热器";break;
            				case "2":str="主板";break;
            				case "3":str="显卡";break;
            				case "4":str="内存";break;
            				case "5":str="硬盘";break;
            				case "6":str="机箱";break;
            				case "7":str="电源";break;
            			}
            			return str;
            		}
            	},{
            		targets: 6,
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
                    title: '新增原材料信息',
                    area: ['628px', '450px'],
                    content: '/info/material/show',
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
                    title: '编辑原材料信息',
                    area: ['628px', '450px'],
                    content: '/info/material/show?id=' + data.id,
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
                        url: '/info/material/delete',
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