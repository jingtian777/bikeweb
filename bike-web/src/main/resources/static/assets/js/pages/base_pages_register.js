/*
 *  Document   : base_pages_register.js
 *  Author     : pixelcave
 *  Description: Custom JS code used in Register Page
 */

var BasePagesRegister = function() {
    // Init Register Form Validation, for more examples you can check out https://github.com/jzaefferer/jquery-validation
    var initValidationRegister = function(){
        jQuery('.js-validation-register').validate({
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
                'register-username': {
                    required: true,
                    minlength: 11,
                    maxlength: 11
                },
                'register-email': {
                    required: false,
                    email: true
                },
                'register-password': {
                    required: true,
                    minlength: 6
                },
                'register-password2': {
                    required: true,
                    equalTo: '#register-password'
                }
            },
            messages: {
                'register-username': {
                    required: '请输入你的手机号码',
                    minlength: '你的手机号码必须是11位数字',
                    maxlength: '你的手机号码必须是11位数字'
                },
                'register-email': '请输入合法的电子邮件地址',
                'register-password': {
                    required: '请输入你的密码',
                    minlength: '你的密码不能少于6个字符'
                },
                'register-password2': {
                    required: '请再次输入你的密码',
                    minlength: '你的密码不能少于6个字符',
                    equalTo: '您输入的两次密码不相同'
                },
            }
        });
    };

    return {
        init: function () {
            // Init Register Form Validation
            initValidationRegister();
        }
    };
}();

// Initialize when page loads
jQuery(function(){ BasePagesRegister.init(); });