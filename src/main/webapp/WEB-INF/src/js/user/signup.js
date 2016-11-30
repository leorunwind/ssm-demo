/**
 * Created by saigao on 16/8/8.
 */
(function () {
    var signupVue = new Vue({
        el: '#page-top',
        data: {
            username: '',
            password: '',
            password1: '',
            mail: '',
            gender: '',
            signupTip: '',
            signupSuccess: '',
            tips: {username: '', password: '', password1: '', mail: ''},
            verify: false
        },
        methods: {
            submit: function () {
                var self = this;
                if (self.mail.length == 0) {
                    self.tips.mail = '邮箱不能为空';
                    return;
                } else {
                    self.tips.mail = '';
                }
                var a = confirm('用户使用协议');
                if (self.verify == false) {
                    return;
                }
                $.ajax({
                    type: 'post',
                    url: '/signup?action=submit',
                    data: {
                        username: self.username,
                        password: self.password,
                        mail: self.mail,
                        gender: self.gender
                    },
                    success: function (data) {
                        console.log(JSON.stringify(data));
                        if (parseInt(data) > 0) {
                            self.signupTip = '注册成功,马上登录>>';
                            self.signupSuccess = true;
                        } else {
                            self.signupTip = '注册失败,请重试';
                            self.signupSuccess = false;
                        }
                    }
                });
            },
            visitLogin: function () {
                window.location.url = '/login';
            }
        },
        watch: {
            'username': function (newVal, oldVal) {
                this.verify = checkUsername(newVal)
                if(!this.verify) {
                    this.tips.username = '用户名必须由中文、大小写英文或数字组成';
                } else {
                    this.tips.username = '';
                }
            },
            'password': function (newVal, oldVal) {
                this.verify = checkPassword(newVal);
                if(!this.verify) {
                    this.tips.password = '密码长度不能少于6位';
                } else {
                    this.tips.password = '';
                }
            },
            'password1': function (newVal, oldVal) {
                this.verify = newVal == this.password;
                if (!this.verify) {
                    this.tips.password1 = '两次输入的密码不一致';
                } else {
                    this.tips.password1 = '';
                }
            },
            'mail': function (newVal, oldVal) {
                this.verify = newVal.length > 0;
                if (!this.verify) {
                    this.tips.mail = '邮箱不能为空';
                } else {
                    this.tips.mail = '';
                }
            }
        }
    });

    function checkUsername(username) {
        for (var idx in username) {
            // !/[\u4e00-\u9fa5]/.test(username[idx]) &&
            if (!/[\u4e00-\u9fa5]/.test(username[idx]) && !/[a-zA-Z]/.test(username[idx]) &&!/[0-9]/.test(username[idx])) {
                return false;
            }
        }
        return true;
    }

    function checkPassword(password) {
        if (password.length < 6) {
            return false;
        }
        return true;
    }
})();