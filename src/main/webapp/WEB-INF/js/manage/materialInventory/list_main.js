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
            el: '#materialInventory-table',
            ajax:{ 
				url:'/manage/materialInventory/list.json',
				type:'post',
				data: function ( dto ) {
					dto.name = $("#name").val();
					dto.number = $("#number").val();
				}
			},
            type:'post',
            columns: [
                {data: 'number'},
                {data: 'name'},
                {data: 'amount'},
                {data: null}
            ],
            columnDefs: [
                {
                    targets: 3,
                    render: function (data, type, row, meta) {
                        var context = {
                            func: [
                                {
                                    'text': '盘点',
                                    'class': 'btn btn-xs btn-primary js-check'
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
            el: '.js-check',
            event: 'click',
            handler: function () {
                var data = table.row($(this).closest('td')).data();
                layer.dialog({
                    title: '产品库存盘点',
                    area: ['628px', '300px'],
                    content: '/manage/materialInventory/show?id=' + data.id,
                    callback: function (data) {
                        if (data.reload) {
                            table.ajax.reload();
                        }
                    }
                })
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