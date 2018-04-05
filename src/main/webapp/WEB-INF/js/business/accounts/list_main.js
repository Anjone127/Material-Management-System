require(['component/iframeLayer','component/dataTable','common/util','common/http','handlebars','jquery'], function (layer, dataTable, util, http, handlebars) {

    init();

    function init() {
        initDataTable();
        bind();
    }

    var table;
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
            el: '#production-table',
            ajax:{ 
				url:'/business/accounts/list.json',
				type:'post',
				data: function ( dto ) {
					dto.stDate = $("#stDate").val();
					dto.endDate = $("#endDate").val();
					dto.peopleNumber = $("#peopleNumber").val();
					dto.type = $("#typeSelect").val();
				}
			},
            type:'post',
            columns: [
                {data: 'date'},
                {data: 'type'},
                {data: 'peopleNumber'},
                {data: 'peopleName'},
                {data: 'orderNumber'},
                {data: 'amount'},
                {data: 'state'},
                {data: 'remark'},
                {data: null},
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
                    	var str="应收";
                    	if(data=="1")
                    		str="应付";
                    	return str;
                    }
                },{
                	targets: 6,
                    render: function (data) {
                    	var str="未确认";
                    	if(data=="1")
                    		str="已确认";
                    	if(data=="2")
                    		str="生产完成";
                    	return str;
                    }
                },{
                	targets: 8,
                    render: function (data) {
                    	if(data.state=="0")
						var context = {
								func: [
									{
										'text': '账款完成',
										'class': 'btn btn-xs btn-primary js-confirm'
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
			el: '.js-search',
			event: 'click',
			handler: function () {
				table.ajax.reload(null,false);
			}
		},{
			el: '.js-excel',
			event: 'click',
			handler: function () {
				location.href='/business/accounts/excel?stDate=' + $("#stDate").val() + '&endDate=' +$("#endDate").val()
					+ '&peopleNumber=' + $("#peopleNumber").val() +"&type=" +$("#typeSelect").val();
			}
		},{
			el: '.js-confirm',
			event: 'click',
			handler: function () {
				var data = table.row($(this).closest('td')).data();
				layer.confirm('账款已经完成?', {icon: 2, title: '提示'}, function (index) {
					http.httpRequest({
						url: '/business/accounts/updateState',
						data: {id : data.id , state : "1"},
						type: 'post',
						success: function (data) {
							if (data.state == 'success') {
								table.ajax.reload(null,false);
								layer.msg(data.message, {time: 1000}, function () {});
							}else{
								layer.msg(data.message, {time: 1000}, function () {});
							}
						}
					});
				});
			}
		}])
    };
});