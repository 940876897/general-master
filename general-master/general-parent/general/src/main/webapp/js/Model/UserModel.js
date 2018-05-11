//构造User Model对象
function UserModel($http, $state, GENERAL_URL) {

    //初始化每页条数
    this.pageSize = 10;
    this.id = 4;
    this.lstUser = [];

    this.search = "";

    this.server = $http;
    this.state = $state;

    //添加用户时存放选中的角色id
    this.addUserSelectedRoleIds = [];
    //添加用户时存放选中的角色名称
    this.addUserSelectedRoleNames = [];

    //编辑用户时存放选中的角色id
    this.editUserSelectedRoleIds = [];
    //编辑用户时存放选中的角色名称
    this.editUserSelectedRoleNames = [];
    //编辑用户时将角色赋值给roleNames，防止在修改角色时双向绑定导致角色跟着变
    this.roleNames = [];

    //存放全部的角色列表
    this.roles = [];

    this.rolesToUser = [];

    this.GENERAL_URL = GENERAL_URL;
}

//角色复选框begin
UserModel.prototype.updateSelected = function (action, id, name, tag) {

    if (tag == 'adduser') {
        if (action == 'add' && this.addUserSelectedRoleIds.indexOf(id) == -1) {
            this.addUserSelectedRoleIds.push(id);
            //  this.addUserSelectedRoleNames.push(name);
        }
        if (action == 'remove' && this.addUserSelectedRoleIds.indexOf(id) != -1) {
            var idx = this.addUserSelectedRoleIds.indexOf(id);
            this.addUserSelectedRoleIds.splice(idx, 1);
            // this.addUserSelectedRoleNames.splice(idx, 1);
        }
    }

    if (tag == 'edituser') {
        if (action == 'add' && this.editUserSelectedRoleIds.indexOf(id) == -1) {
            this.editUserSelectedRoleIds.push(id);
            //this.editUserSelectedRoleNames.push(name);
        }
        if (action == 'remove' && this.editUserSelectedRoleIds.indexOf(id) != -1) {
            var idx = this.editUserSelectedRoleIds.indexOf(id);
            this.editUserSelectedRoleIds.splice(idx, 1);
            // this.editUserSelectedRoleNames.splice(idx, 1);
        }
    }
};

UserModel.prototype.updateSelection = function ($event, id, tag) {
    if (tag == 'adduser') {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');
        this.updateSelected(action, id, checkbox.name, tag);
    }

    if (tag == 'edituser') {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');
        this.updateSelected(action, id, checkbox.name, tag);
    }
};

UserModel.prototype.isSelected = function (id, tag) {

    if (tag == 'adduser') {
        return this.addUserSelectedRoleIds.indexOf(id) >= 0;
    }

    if (tag == 'edituser') {
        return this.editUserSelectedRoleIds.indexOf(id) >= 0;
    }
};

//角色复选框end


//获取用户列表带搜索
UserModel.prototype.loadAllUsers = function (paginationConf, search) {

    var searchURL = this.GENERAL_URL.SHIROUSER_SEARCH + '?searchValue=' + search + '&pageSize=' + this.pageSize +
        '&currentPageNo=' + (paginationConf.currentPage == 0 ? 1 : paginationConf.currentPage);
    var listURL = this.GENERAL_URL.SHIROUSER_LIST + '?pageSize=' + this.pageSize +
        '&currentPageNo=' + (paginationConf.currentPage == 0 ? 1 : paginationConf.currentPage);

    var URL = (search || search != "") ? searchURL : listURL;

    var pData = new Array();
    var self = this;
    this.server({
        method: 'GET',
        url: URL
    }).success(function (data, status, headers, config) {
        if (data != "" && data.list.length > 0) {
            for (var i = 0; i < data.list.length; i++) {
                pData.push(data.list[i]);
            }
            paginationConf.totalItems = data.totalCount;
        }
        self.lstUser = pData;
    })
};

//获取用户的角色列表
UserModel.prototype.loadRoleToUserId = function (userid) {

    var searchURL = this.GENERAL_URL.SHIROROLE_ROLELIST_USERID + '?userid=' + userid;
    var self = this;
    var rolesToUserData = new Array();
    var roleNamesData = new Array();
    this.server({
        method: 'GET',
        url: searchURL
    }).success(function (data, status, headers, config) {

        if (data != "" && data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                rolesToUserData.push(data[i].roleId);
                roleNamesData.push(data[i].roleName);
            }
        }
        self.editUserSelectedRoleIds = rolesToUserData;
        self.editUserSelectedRoleNames = roleNamesData;
        self.roleNames = roleNamesData;//将角色赋值给roleNames，防止在修改角色时双向绑定导致角色跟着变
    });
};


//获取角色列表
UserModel.prototype.loadAllRoles = function () {


    var searchURL = this.GENERAL_URL.SHIROROLE_LIST;
    var pData = new Array();
    this.addUserSelectedRoleIds = [];
    this.roles = [];
    var self = this;
    this.server({
        method: 'GET',
        url: searchURL
    }).success(function (data, status, headers, config) {
        if (data != "" && data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                pData.push(data[i]);
            }
        }
        self.roles = pData;
    })
    return this.roles;
};

//添加用户信息
UserModel.prototype.addUser = function (isValid, user) {

    user.roles = this.addUserSelectedRoleIds;
    var GENERAL_URL = this.GENERAL_URL;

    if (isValid) {
        this.server({
            method: 'POST',
            url: GENERAL_URL.SHIROUSER_ADD,
            data: user,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            if (data.code != 0) {
                alert(data.info);
            } else {
                window.location.href = '#/userList';
            }
        })
    }
};

//验证用户名是否存在
UserModel.prototype.ifExistUserName = function (user, control) {

    if (user == undefined ||
        user.username == "" ||
        user.oldUsername == user.username) {
        return;
    }

    var searchURL = this.GENERAL_URL.SHIROUSER_USERNAME_EXISTS + '?username=' + user.username;

    this.server({
        method: 'GET',
        url: searchURL,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        user.exists = data.exists;

        if (user.exists == true) {
            user.inputusername = user.username;
            user.username = "";
        }
    })
};

//根据userid从Admin.userList中获取用户详细记录
UserModel.prototype.setUserInstance = function (userid) {

    for (var i = 0; i < this.lstUser.length; i++) {
        if (this.lstUser[i].userId == userid) {
            if (window.localStorage) {
                localStorage[userid] = JSON.stringify(this.lstUser[i]);
            }
            return this.lstUser[i];
        }
    }
    return null;
};

//修改用户信息
UserModel.prototype.editUser = function (isValid, user) {

    user.roles = this.editUserSelectedRoleIds;
    var GENERAL_URL = this.GENERAL_URL;

    if (isValid) {
        this.server({
            method: 'POST',
            url: GENERAL_URL.SHIROUSER_EDIT,
            data: user,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            if (data.code != 0) {
                alert(data.info);
            } else {
                window.location.href = '#/userList';
            }
        })
    }
};


// 删除用户信息
UserModel.prototype.userDelete = function (user) {

    var state = this.state;
    var GENERAL_URL = this.GENERAL_URL;

    this.server({
        method: 'POST',
        url: GENERAL_URL.SHIROUSER_DELETE,
        data: user,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        if (data.code != 0) {
            alert(data.info);
        } else {
            state.go('userList', {}, {
                reload: true
            });
        }
    })
};

