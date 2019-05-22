/*
 *  Document   : base_pages_login.js
 *  Author     : pixelcave
 *  Description: Custom JS code used in Login Page
 */

var BasePagesLogin = function() {
    // Init Login Form Validation, for more examples you can check out https://github.com/jzaefferer/jquery-validation
    var initValidationLogin = function(){
        jQuery('.js-validation-login').validate({
            errorClass: 'help-block text-right animated fadeInDown',
            errorElement: 'div',
            errorPlacement: function(error, e) {
                jQuery(e).parents('.form-group > div').append(error);
            },
            highlight: function(e) {
                jQuery(e).closest('.form-group').removeClass('has-error').addClass('has-error');
                jQuery(e).closest('.help-block').remove();
            },
            success: function(e) {
                jQuery(e).closest('.form-group').removeClass('has-error');
                jQuery(e).closest('.help-block').remove();
            },
            rules: {
                'login-username': {
                    required: true,
                    minlength: 11,
                    maxlength: 11
                },
                'login-password': {
                    required: true,
                    minlength: 6
                }
            },
            messages: {
                'login-username': {
                    required: '请输入你的手机号码',
                    minlength: '你的手机号码必须是11位数字',
                    maxlength: '你的手机号码必须是11位数字'
                },
                'login-password': {
                    required: '请输入你的密码',
                    minlength: '你的密码不能少于6个字符'
                }
            }
        });
    };

    return {
        init: function () {
            // Init Login Form Validation
            initValidationLogin();
        }
    };
}();

// Initialize when page loads
jQuery(function(){ BasePagesLogin.init(); });