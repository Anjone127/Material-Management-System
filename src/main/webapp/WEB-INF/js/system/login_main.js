require(['common/util', 'layer1', 'common/http', 'jquery.validate', 'jquery','jquery.serialize'], function (util, layer, http) {

    //执行初始化函数
    init();
    
    /**
     * 初始化集合
     */
    function init() {
        formValid();
        bind();
    }

    /**
     * 登录表单验证
     */
    function formValid() {
        $('#login-form').validate({
            rules: {
            	userId: {
                    required: true,
                    rangelength: [4, 20]
                },
                userPassword: {
                    required: true,
                    rangelength: [6, 20]
                }
            },
            messages: {
            	username: {
                    required: '登录账号不能为空',
                    rangelength: '登录账号长度必须是{0}到{1}之间'
                },
                password: {
                    required: '登录密码不能为空',
                    rangelength: '登录密码长度必须是{0}到{1}之间'
                }
            }
        });
    }

    /**
     * 事件绑定
     */
    function bind() {
        util.bindEvents([{
            el: '.js-checkcode',
            event: 'click',
            handler: function () {
                $(this).attr('src', '/captcha?t=' + Math.random());
            }
        }])
    }

})