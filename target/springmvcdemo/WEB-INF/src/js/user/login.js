/**
 * Created by saigao on 16/8/7.
 */
(function () {
    var loginVue = new Vue({
        el: '#page-top',
        data: {
            username: '',
            password: ''
        },
        methods: {
            submit: function() {
                var self = this;
                if (!this.username) {
                    alert('请输入账号');
                    return;
                }
                if (!this.password) {
                    alert('请输入密码');
                    return;
                }
                $.ajax({
                    type: 'post',
                    url: '/login?action=submit',
                    data: {usernameOrMail: self.username, password: self.password},
                    success: function (data) {
                        if (data.result) {
                            window.location.href = '/';
                        } else {
                            alert('用户名不存在或密码错误');
                        }
                    }
                });
            }
        }
    })
})();