function Security($http, $cookieStore, $rootScope, $state, GENERAL_URL) {
    this.server = $http;
    this.cookieStore = $cookieStore;
    this.rootScope = $rootScope;
    this.state = $state;
    this.GENERAL_URL = GENERAL_URL;
}

Security.prototype.Login = function (username, password, callback) {

    var self = this;
    this.server.post('/login/login.json', {
        username: username,
        password: password
    })
        .success(function (response) {
            self.SetCredentials(response);
            callback(response);
        })
};


Security.prototype.SetCredentials = function (response) {

    this.rootScope.globals = {
        currentUser: {
            userId: response.userId,
            surname: response.surname,
            roles: response.roles,
            permissions: response.permissions
        }
    };
    this.rootScope.isLogin = true;
    this.server.defaults.headers.common['Authorization'] =
        'Basic ' + this.rootScope.globals.currentUser;
    this.cookieStore.put('globals', this.rootScope.globals);
    this.cookieStore.put('isLogin', true);
};

Security.prototype.ClearCredentials = function () {
    this.rootScope.isLogin = false;
    this.rootScope.globals = {};
    this.cookieStore.remove('globals');
    this.cookieStore.remove('isLogin');
    this.cookieStore.remove('sid');
    this.server.defaults.headers.common.Authorization = 'Basic ';
};

//设置用户密码
Security.prototype.passwordRest = function (user) {

    var resetURL = this.GENERAL_URL.SHIROUSER_PSWRESET;
    var initURL = this.GENERAL_URL.SHIROUSER_PSWINIT;
    var URL = user.oldPassword == undefined ? initURL : resetURL;

    var state = this.state;
    this.server({
        method: 'POST',
        url: URL,
        data: user,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        if (data.code != 0) {
            alert(data.info);
        } else if (URL == initURL) {
            state.go('userList', {}, {
                reload: true
            });
        }
        else {
            state.go('logout', {}, {
                reload: true
            });
        }
    })
}

Security.prototype.certificatePSW = function (user) {

    if (user == undefined ||
        user.oldPassword == undefined ||
        user.oldPassword == "") {
        return;
    }

    user.userId = this.rootScope.globals.currentUser.userId;
    var GENERAL_URL = this.GENERAL_URL;

    this.server({
        method: 'POST',
        url: GENERAL_URL.SHIROUSER_PSWCERT,
        data: user,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        user.pswMismatch = data.pswMismatch;

        if (user.pswMismatch == true) {
            user.oldPassword = "";
        }
    })
};


Security.prototype.validPermission = function (state) {
    var permission = state.current.permission;
    if (!this.hasPermission(permission) && this.rootScope.isLogin) {
        state.go('UnauthorizedAccess');
        return false;
    }
    return true;
};

Security.prototype.hasRole = function (r) {

    if (this.rootScope.globals.currentUser.roles === undefined) {
        return false;
    }

    var roles = this.rootScope.globals.currentUser.roles;
    if (!angular.isArray(roles)) {
        roles = [roles];
    }
    return (roles.indexOf(r) !== -1);
};

Security.prototype.hasPermission = function (p) {

    if (this.rootScope.globals.currentUser === undefined ||
        this.rootScope.globals.currentUser.permissions === undefined) {
        return false;
    }

    var permissions = this.rootScope.globals.currentUser.permissions;
    if (!angular.isArray(permissions)) {
        permissions = [permissions];
    }
    return (permissions.indexOf(p) !== -1);
};

Security.prototype.encode = function (input) {
    var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
    var output = "";
    var chr1, chr2, chr3 = "";
    var enc1, enc2, enc3, enc4 = "";
    var i = 0;

    do {
        chr1 = input.charCodeAt(i++);
        chr2 = input.charCodeAt(i++);
        chr3 = input.charCodeAt(i++);

        enc1 = chr1 >> 2;
        enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
        enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
        enc4 = chr3 & 63;

        if (isNaN(chr2)) {
            enc3 = enc4 = 64;
        } else if (isNaN(chr3)) {
            enc4 = 64;
        }

        output = output +
            keyStr.charAt(enc1) +
            keyStr.charAt(enc2) +
            keyStr.charAt(enc3) +
            keyStr.charAt(enc4);
        chr1 = chr2 = chr3 = "";
        enc1 = enc2 = enc3 = enc4 = "";
    } while (i < input.length);

    return output;
};

Security.prototype.decode = function (input) {
    var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
    var output = "";
    var chr1, chr2, chr3 = "";
    var enc1, enc2, enc3, enc4 = "";
    var i = 0;

    // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
    var base64test = /[^A-Za-z0-9\+\/\=]/g;
    if (base64test.exec(input)) {
        window.alert("There were invalid base64 characters in the input text.\n" +
            "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
            "Expect errors in decoding.");
    }
    input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

    do {
        enc1 = keyStr.indexOf(input.charAt(i++));
        enc2 = keyStr.indexOf(input.charAt(i++));
        enc3 = keyStr.indexOf(input.charAt(i++));
        enc4 = keyStr.indexOf(input.charAt(i++));

        chr1 = (enc1 << 2) | (enc2 >> 4);
        chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
        chr3 = ((enc3 & 3) << 6) | enc4;

        output = output + String.fromCharCode(chr1);

        if (enc3 != 64) {
            output = output + String.fromCharCode(chr2);
        }
        if (enc4 != 64) {
            output = output + String.fromCharCode(chr3);
        }

        chr1 = chr2 = chr3 = "";
        enc1 = enc2 = enc3 = enc4 = "";

    } while (i < input.length);

    return output;
};
