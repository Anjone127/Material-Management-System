require(['component/iframeLayer','component/dataTable','common/util','common/http','handlebars','jquery'], function (layer, dataTable, util, http, handlebars) {

	init();

	function init() {
		initMOrderTable();
		initMaterialTable();
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
	var m_order_table;
	var material_table;
	var order_detail_table;
	var tpl = $('#tpl').html();
	var template = handlebars.compile(tpl);

	function initMaterialTable() {
		material_table = dataTable.load({
			el: '#material-table',
			ajax:{ 
				url:'/info/material/list.json',
				type:'post',
				data: function ( dto ) {
					dto.supplierNumber = $("#supplierNumber").val();
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

	function initMOrderTable() {
		m_order_table = dataTable.load({
			el: '#morder-table',
			ajax:{ 
				url:'/business/morder/list.json',
				type:'post',
				data: function ( dto ) {
					dto.name = $("#name").val();
					dto.number = $("#number").val();
				}
			},
			type:'post',
			columns: [
				{data: 'number'},
				{data: 'supplierNumber'},
				{data: 'totalMoney'},
				{data: 'cancelMoney'},
				{data: 'state'},
				{data: 'startTime'},
				{data: 'finalTime'},
				{data: 'remark'},
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
							case "1": context = "待收货"; break;
							case "2": context = "退货中"; break;
							case "3": context = "已完成"; break;
							}
							return context;
						}
					},{
						targets: 8,
						render: function (data, type, row, meta) {
							var context ;
							if(data.state=="0")
								context= {
									func: [
										{
											'text': '修改备注',
											'class': 'btn btn-xs btn-primary js-edit'
										},{
											'text': '编辑订单',
											'class': 'btn btn-xs btn-primary js-show-add-detial'
										},{
											'text': '确认订单',
											'class': 'btn btn-xs btn-primary js-confirm1'
										}
										]
							};
							if(data.state=="1")
								context = {
									func: [{
										'text': '确认收货',
										'class': 'btn btn-xs btn-primary js-confirm3'
									}]};
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
				url:'/business/morder/detailList.json',
				type:'post',
				data: function ( dto ) {
					dto.orderId = $("#orderId").val();
				}
			},
			type:'post',
			columns: [
				{data: 'materialNumber'},
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
				var data = m_order_table.row($(this).closest('td')).data();
				layer.confirm('确认要删除吗?', {icon: 2, title: '提示'}, function (index) {
					http.httpRequest({
						url: '/business/morder/updateState',
						data: {id : data.id , state : "1"},
						type: 'post',
						success: function (data) {
							if (data.state == 'success') {
								m_order_table.ajax.reload(null,false);
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
				var data = m_order_table.row($(this).closest('td')).data();
				layer.confirm('确认收货?', {icon: 2, title: '提示'}, function (index) {
					http.httpRequest({
						url: '/business/morder/updateState',
						data: {id : data.id , state : "3"},
						type: 'post',
						success: function (data) {
							if (data.state == 'success') {
								m_order_table.ajax.reload(null,false);
								layer.msg(data.message, {time: 1000}, function () {});
							}else{
								layer.msg(data.message, {time: 1000}, function () {});
							}
						}
					});
				});
			}
		},{
			el: '.js-show-add-detial',
			event: 'click',
			handler: function () {
				var data = m_order_table.row($(this).closest('td')).data();
				$("#morder-div").hide();
				$("#supplierNumber").val(data.supplierNumber);
				$("#orderId").val(data.id);
				material_table.ajax.reload();
				order_detail_table.ajax.reload();
				$("#material-div").show();
				$("#detail-div").show();
			}
		},{
			el: '.js-add-detial',
			event: 'click',
			handler: function () {
				var data = material_table.row($(this).closest('td')).data();
				http.httpRequest({
					url: '/business/morder/addDetail',
					data: {orderId: $("#orderId").val() , materialId: data.id , materialNumber: data.number , amount : $("#"+data.id).val() ,price: data.price},
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
					url: '/business/morder/deleteDetail',
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
			el: '.js-add-morder',
			event: 'click',
			handler: function () {
				layer.dialog({
					title: '新增物料订单',
					area: ['628px', '300px'],
					content: '/business/morder/show',
					callback: function (data) {
						if (data.reload) {
							m_order_table.ajax.reload();
						}
					}
				})
			}
		}, {
			el: '.js-edit',
			event: 'click',
			handler: function () {
				var data = m_order_table.row($(this).closest('td')).data();
				layer.dialog({
					title: '修改备注',
					area: ['628px', '300px'],
					content: '/business/morder/show?id=' + data.id,
					callback: function (data) {
						if (data.reload) {
							m_order_table.ajax.reload();
						}
					}
				})
			}
		},{
			el: '.js-back',
			event: 'click',
			handler: function () {
				$("#material-div").hide();
				$("#detail-div").hide();
				m_order_table.ajax.reload(null,false);
				$("#morder-div").show();
			}
		}, {
			el: '.js-search',
			event: 'click',
			handler: function () {
				m_order_table.ajax.reload(null,false);
			}
		}])
	};
});