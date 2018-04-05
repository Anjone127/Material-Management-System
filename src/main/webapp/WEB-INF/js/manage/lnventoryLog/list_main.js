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
	function initDataTable() {
		var tpl = $('#tpl').html();
		var template = handlebars.compile(tpl);
		table = dataTable.load({
			el: '#lnventoryLog-table',
			ajax:{ 
				url:'/manage/lnventoryLog/list.json',
				type:'post',
				data: function ( dto ) {
					dto.name = $("#name").val();
					dto.number = $("#number").val();
				}
			},
			type:'post',
			columns: [
				{data: 'date'},
				{data: 'type'},
				{data: 'inventoryName'},
				{data: 'amount'}
				],
				columnDefs: [
					{
						targets:0,
						render :function(data){
							return getDate(data);
						}
					},{
						targets: 1,
						render: function (data) {
							var str="原材料入库";
							if(data=="1")
								str="生产消耗";
							if(data=="2")
								str="库存盘点";
							if(data=="3")
								str="产品入库";
							if(data=="4")
								str="产品销售";
							return str;
						}

					}
					]
		})
	}

	function bind() {
		util.bindEvents([{
			el: '.js-search',
			event: 'click',
			handler: function () {
				table.ajax.reload(null,false);
			}
		}])
	};
});