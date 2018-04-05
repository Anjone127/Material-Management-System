require(['component/iframeLayer','component/dataTable','common/util','common/http','handlebars','jquery'], function (layer, dataTable, util, http, handlebars) {

	init();

	function init() {
		initProductTable();
		initPOrderTable();
		initOrderDetailTable();
		bind();
	}
	function getMonth(date){  
		var month = "";  
		month = date.getMonth() + 1; 
		if(month<10){  
			month = "0" + month;  
		}  
		return month;  
	}   
	function getDay(date){  
		var day = "";  
		day = date.getDate();  
		if(day<10){  
			day = "0" + day;  
		}  
		return day;  
	}
	function getHours(date){
		var hours = "";
		hours = date.getHours();
		if(hours<10){  
			hours = "0" + hours;  
		}  
		return hours;  
	}
	function getMinutes(date){
		var minute = "";
		minute = date.getMinutes();
		if(minute<10){  
			minute = "0" + minute;  
		}  
		return minute;  
	}
	function getSeconds(date){
		var second = "";
		second = date.getSeconds();
		if(second<10){  
			second = "0" + second;  
		}  
		return second;  
	}
	function getDate(longTypeDate){  
		var datetimeType = "";  
		var date = new Date();  
		date.setTime(longTypeDate);
		datetimeType = date.getFullYear()+"-"+getMonth(date)+"-"+getDay(date)+"&nbsp;"+getHours(date)+":"+getMinutes(date)+":"+getSeconds(date);//yyyy-MM-dd 00:00:00格式日期
		return datetimeType;
	} 
	var p_order_table;
	var product_table;
	var order_detail_table;
	var tpl = $('#tpl').html();
	var template = handlebars.compile(tpl);

	function initProductTable() {
		product_table = dataTable.load({
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
				{data: 'description' },
				{data: null , width:'40%'}
				],
				columnDefs: [
					{
						targets: 4,
						render: function (data, type, row, meta) {
							var context = {
									func: [
										{
											'text': '添加到订单',
											'class': 'btn btn-xs btn-primary js-add-detial'
										}
										]
							};
							return "数量：<input id=" + data.id +" type='text'> " +template(context);
						}
					}
					]
		})
	}

	function initPOrderTable() {
		p_order_table = dataTable.load({
			el: '#porder-table',
			ajax:{ 
				url:'/business/porder/list.json',
				type:'post',
				data: function ( dto ) {
					dto.number = $("#number").val();
				}
			},
			type:'post',
			columns: [
				{data: 'number'},
				{data: 'customerNumber'},
				{data: 'totalMoney'},
				{data: 'cancelMoney'},
				{data: 'state'},
				{data: 'startTime'},
				{data: 'finalTime'},
				{data: null}
				],
				columnDefs: [
					{
						targets:5,
						render :function(data){
							return getDate(data);
						}
					},{
						targets:6,
						render :function(data){
							return getDate(data);
						}
					},{
						targets: 4,
						render: function (data, type, row, meta) {
							var context = "未确认";
							switch(data){
							case "1": context = "到发货"; break;
							case "2": context = "发货中"; break;
							case "3": context = "订单完成"; break;
							case "4": context = "已撤销";break;
							}
							return context;
						}
					},{
						targets: 7,
						render: function (data) {
							var context;
							switch(data.state){
							case "0":{
								context= {
										func: [
											{'text': '撤销订单','class': 'btn btn-xs btn-danger js-cancel'},
											{'text': '编辑订单','class': 'btn btn-xs btn-primary js-show-order-detial'},
											{'text': '确认订单','class': 'btn btn-xs btn-primary js-confirm1'}]
								};
								break;
							}
							case "1":{
								context = {
										func: [{'text': '发货','class': 'btn btn-xs btn-primary js-confirm2'}]
								};
								break;
							}
							case "2":{
								context = {func: [{'text': '确认收货','class': 'btn btn-xs btn-primary js-confirm3'}]};
							}
							}
							return template(context);
						}
					}
					]
		})
	}

	function initOrderDetailTable() {
		order_detail_table = dataTable.load({
			el: '#order-detail-table',
			ajax:{ 
				url:'/business/porder/detailList.json',
				type:'post',
				data: function ( dto ) {
					dto.orderId = $("#orderId").val();
				}
			},
			type:'post',
			columns: [
				{data: 'productNumber'},
				{data: 'price'},
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
											'text': '删除',
											'class': 'btn btn-xs btn-primary js-delete-detail'
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
			el: '.js-confirm1',
			event: 'click',
			handler: function () {
				var data = p_order_table.row($(this).closest('td')).data();
				layer.confirm('确认订单?', {icon: 2, title: '提示'}, function (index) {
					http.httpRequest({
						url: '/business/porder/updateState',
						data: {id : data.id , state : "1"},
						type: 'post',
						success: function (data) {
							if (data.state == 'success') {
								p_order_table.ajax.reload(null,false);
								layer.msg(data.message, {time: 1000}, function () {});
							}else{
								layer.msg(data.message, {time: 1000}, function () {});
							}
						}
					});
				});
			}
		},{
			el: '.js-confirm2',
			event: 'click',
			handler: function () {
				var data = p_order_table.row($(this).closest('td')).data();
				layer.confirm('确认发货?', {icon: 2, title: '提示'}, function (index) {
					http.httpRequest({
						url: '/business/porder/updateState',
						data: {id : data.id , state : "2"},
						type: 'post',
						success: function (data) {
							if (data.state == 'success') {
								p_order_table.ajax.reload(null,false);
								layer.msg(data.message, {time: 1000}, function () {});
							}else{
								layer.msg(data.message, {time: 1000}, function () {});
							}
						}
					});
				});
			}
		},{
			el: '.js-confirm3',
			event: 'click',
			handler: function () {
				var data = p_order_table.row($(this).closest('td')).data();
				layer.confirm('确认收货?', {icon: 2, title: '提示'}, function (index) {
					http.httpRequest({
						url: '/business/porder/updateState',
						data: {id : data.id , state : "3"},
						type: 'post',
						success: function (data) {
							if (data.state == 'success') {
								p_order_table.ajax.reload(null,false);
								layer.msg(data.message, {time: 1000}, function () {});
							}else{
								layer.msg(data.message, {time: 1000}, function () {});
							}
						}
					});
				});
			}
		},{
			el: '.js-show-order-detial',
			event: 'click',
			handler: function () {
				var data = p_order_table.row($(this).closest('td')).data();
				$("#porder-div").hide();
				$("#orderId").val(data.id);
				order_detail_table.ajax.reload();
				$("#product-div").show();
				$("#detail-div").show();
			}
		},{
			el: '.js-add-detial',
			event: 'click',
			handler: function () {
				var data = product_table.row($(this).closest('td')).data();
				http.httpRequest({
					url: '/business/porder/addDetail',
					data: {orderId: $("#orderId").val() , productId: data.id , productNumber: data.number , amount : $("#"+data.id).val() ,price: data.price},
					type: 'post',
					success: function (data) {
						if (data.state == 'success') {
							order_detail_table.ajax.reload();
							layer.msg(data.message, {time: 1000}, function () {});
						}else{
							layer.msg(data.message, {time: 1000}, function () {});
						}
					}
				});
			}
		},{
			el: '.js-delete-detail',
			event: 'click',
			handler: function () {
				var data = order_detail_table.row($(this).closest('td')).data();
				http.httpRequest({
					url: '/business/porder/deleteDetail',
					data: {id:data.id},
					type: 'post',
					success: function (data) {
						if (data.state == 'success') {
							layer.msg(data.message, {time: 1000}, function () {});
							order_detail_table.ajax.reload();
						}else{
							layer.msg(data.message, {time: 1000}, function () {});
						}
					}
				});
			}
		}, {
			el: '.js-add-porder',
			event: 'click',
			handler: function () {
				layer.dialog({
					title: '新增产品订单',
					area: ['628px', '300px'],
					content: '/business/porder/show',
					callback: function (data) {
						if (data.reload) {
							p_order_table.ajax.reload();
						}
					}
				})
			}
		}, {
			el: '.js-cancel',
			event: 'click',
			handler: function () {
				var data = p_order_table.row($(this).closest('td')).data();
				http.httpRequest({
					url: '/business/porder/updateState',
					data: {id : data.id , state : "4"},
					type: 'post',
					success: function (data) {
						if (data.state == 'success') {
							p_order_table.ajax.reload(null,false);
							layer.msg(data.message, {time: 1000}, function () {});
						}else{
							layer.msg(data.message, {time: 1000}, function () {});
						}
					}
				});
			}
		},{
			el: '.js-back',
			event: 'click',
			handler: function () {
				$("#product-div").hide();
				$("#detail-div").hide();
				p_order_table.ajax.reload(null,false);
				$("#porder-div").show();
			}
		}, {
			el: '.js-search',
			event: 'click',
			handler: function () {
				p_order_table.ajax.reload(null,false);
			}
		}])
	};
});