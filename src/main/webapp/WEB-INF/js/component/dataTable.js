define(['jquery', 'jquery.dataTables', 'dataTables.bootstrap'], function () {

    return {
        /**
         * DataTable的默认配置
         * @param options
         * @returns {jQuery table}
         */
        load: function (options) {
            var defaultOptions = {
                //默认第一列不显示索引
                showIndex: false,
                //默认启用服务器分页
                serverSide: true,
                //默认不进行排序
                ordering: false,
                //分页类型
                pagingType: 'full_numbers',
                //各个组件的位置配置
                dom: '<"wapper"t<"bottom"<"pull-left page-length"l><"pull-left"i><"pull-right"p>>>'
            }
            var opt = $.extend({}, defaultOptions, options);
            var table = $(options.el).DataTable(opt);

            //是否显示索引
            if (opt.showIndex) {
                var i = 0;
                //加索引
                table.on('draw.dt', function () {
                    table.column(0, {
                        search: 'applied',
                        order: 'applied'
                    }).nodes().each(function (cell, i) {
                        //i 从0开始，所以这里先加1
                        i = i + 1;
                        //服务器模式下获取分页信息，使用 DT 提供的 API 直接获取分页信息
                        var page = table.page.info();
                        //当前第几页，从0开始
                        var pageno = page.page;
                        //每页数据
                        var length = page.length;
                        //行号等于 页数*每页数据长度+行号
                        var columnIndex = (i + pageno * length);
                        cell.innerHTML = columnIndex;
                    });
                })
            }

            return table;
        }

    }
})