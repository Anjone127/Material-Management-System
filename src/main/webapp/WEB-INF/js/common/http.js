/**
 * http请求工具
 */
define(['jquery', 'component/iframeLayer'], function ($, iframeLayer) {

    // 默认值
    var defaultOpt = {
        async: true,
        dataType: 'json',
        type: 'get',
        serializable: false
    };

    var index;  //success之后销毁对应的layer层

    /**
     * 组装http请求参数
     * @param options
     * {
     *  type:get/post  请求类型
     *  data:{},  业务参数
     *  serializable:true/false,  是否需要序列化
     * @returns {void|*}
     */
    function generateHttpParam(options) {
        // 参数合并
        var opt = $.extend({}, defaultOpt, options);

        opt.beforeSend = function () {
            if (options.beforeSend) {
                options.beforeSend();
            } else {
                //index = iframeLayer.msg('发送中，请稍后', {icon: 16, time: -1, shade: [0.4, '#CCC']});
            }
        };

        // 获取请求地址
        opt.url = generateUrl(options.url);
        // 成功回调
        opt.success = function (data) {
            // 成功回调
            if (index) {
                iframeLayer.close(index);
            }
            options.success && options.success(data);
        };
        // 失败回调
        opt.error = function (jqXHR, textStatus, errorThrown) {
            // 权限异常处理
            if (unAuthError(jqXHR, textStatus, errorThrown)) {
                return;
            }

            if (options.error) {
                options.error();
            } else {
                iframeLayer.msg('网络异常，请重试');
            }
        };
        // json参数序列化
        if (opt.serializable) {
            opt.contentType = 'application/json';
            opt.data = JSON.stringify(opt.data);
        }

        //返回
        return opt;
    }

    /**
     * 组装http请求url
     * @param url 请求的url
     */
    function generateUrl(url) {
        var p = {
            // 增加时间戳，解决IE浏览器ajax请求缓存问题
            _t: new Date().getTime()
        };

        return url + '?' + $.param(p);
    }


    /**
     * http get请求
     * @param options
     */
    function httpRequest(options) {
        var ajax = $.ajax(generateHttpParam(options));
        return ajax;
    }

    /**
     * 未授权错误
     * @param jqXHR
     * @param textStatus
     * @param errorThrown
     */
    function unAuthError(jqXHR, textStatus, errorThrown) {
        // 401为未授权状态
        if (jqXHR.status === 401) {
            iframeLayer.alert("您尚未登录或登录时间过长,请重新登录!", {icon: 3, title: '登录提示'}, function () {
                top.location.href = window.__FRAMEWORK__.root_url;
            });
            return true;
        }
    }

    // 返回
    return {
        httpRequest: httpRequest,
        unAuthError: unAuthError
    };
});