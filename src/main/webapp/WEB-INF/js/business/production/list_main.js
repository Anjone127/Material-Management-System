require(['component/iframeLayer','component/dataTable','common/util','common/http','handlebars','jquery'], function (layer, dataTable, util, http, handlebars) {

	init();

	function init() {
		initDataTable();
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
	var table;

	function initDataTable() {
		var tpl = $('#tpl').html();
		var template = handlebars.compile(tpl);
		table = dataTable.load({
			el: '#production-table',
			ajax:{ 
				url:'/business/production/list.json',
				type:'post',
				data: function ( dto ) {
					dto.productNumber = $("#productNumber").val();
					dto.sId = $("#sId").val()+"";
				}
			},
			type:'post',
			columns: [
				{data: 'id'},
				{data: 'productNumber'},
				{data: 'amount'},
				{data: null},
				{data: 'date'},
				{data: 'finishTime'},
				{data: null}
				],
				columnDefs: [
					{
						targets:4,
						render :function(data){
							return getDate(data);
						}
					},{
						targets: 3,
						render: function (data) {
							var str="未完成";
	                    	if(data.state=="1")
	                    		str="已完成";
							if(data.state=="3")
								str="已撤销";
							return str;
						}

					},{
						targets: 6,
						render: function (row) {
							context = {};
							if(row.state=="0")
								context = {
									func: [{
										'text': '生产完成',
										'class': 'btn btn-xs btn-primary js-confirm'
									},{
										'text': '撤销',
										'class': 'btn btn-xs btn-danger js-cancle'
									}]};
							return template(context);
						}
					}
					]
		})
	}

	function bind() {
		util.bindEvents([{
			el: '.js-confirm',
			event: 'click',
			handler: function () {
				var data = table.row($(this).closest('td')).data();
				layer.confirm('生产完成?', {icon: 2, title: '提示'}, function (index) {
					http.httpRequest({
						url: '/business/production/updateState',
						data: {id:data.id, state : "1"},
						type: 'post',
						success: function (data) {
							if (data.state == 'success') {
								layer.msg(data.message, {time: 1000}, function () {});
								table.ajax.reload();
							}else{
								layer.msg(data.message, {time: 1000}, function () {});
							}
						}
					});
				});
			}
		},{
			el: '.js-cancle',
			event: 'click',
			handler: function () {
				var data = table.row($(this).closest('td')).data();
				layer.confirm('确定撤销计划吗?', {icon: 2, title: '提示'}, function (index) {
					http.httpRequest({
						url: '/business/production/updateState',
						data: {id:data.id, state : "3"},
						type: 'post',
						success: function (data) {
							if (data.state == 'success') {
								layer.msg(data.message, {time: 1000}, function () {});
								table.ajax.reload();
							}else{
								layer.msg(data.message, {time: 1000}, function () {});
							}
						}
					});
				});
			}
		},{
			el: '.js-search',
			event: 'click',
			handler: function () {
				table.ajax.reload(null,false);
			}
		},{
			el: '.js-add',
			event: 'click',
			handler: function () {
				layer.dialog({
					title: '新增生产计划',
					area: ['628px', '220px'],
					content: '/business/production/show',
					callback: function (data) {
						if (data.reload) {
							table.ajax.reload();
						}
					}
				}) 
			}
		}
		])
	};
});