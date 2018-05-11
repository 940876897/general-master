//构造Role Model对象
function RoleModel($http, $state, PERMISSION, GENERAL_URL) {

    //初始化每页条数
    this.pageSize = 10;
    this.id = 5;
    this.lstRole = [];

    this.search = "";

    this.server = $http;
    this.state = $state;

    this.selected = [];
    this.selectedTags = [];
    this.roles = [];

    //角色管理成员时选中的成员列表
    this.selectedUser = [];
    //角色管理成员时待选择的成员列表
    this.unselectedUser = [];

    //权限分配静态数据
    this.permissionList = [
        {
            id: 1,
            type: 'project',
            name: '项目列表',
            permissions: [
                {pid: PERMISSION.PROJECT_VIEW, pname: '项目菜单'},
                {pid: PERMISSION.PROJECT_CREATE, pname: '添加'},
                {pid: PERMISSION.PROJECT_UPDATE, pname: '修改'},
                {pid: PERMISSION.PROJECT_SOFTWARE, pname: '查看软件'},
                {pid: PERMISSION.PROJECT_SEARCH, pname: '搜索'}]
        },
        {
            id: 2,
            type: 'project',
            name: '项目软件',
            permissions: [
                {pid: PERMISSION.SOFTWARE_VIEW, pname: '软件菜单'},
                {pid: PERMISSION.SOFTWARE_CREATE, pname: '添加'},
                {pid: PERMISSION.SOFTWARE_UPDATE, pname: '修改'},
                {pid: PERMISSION.SOFTWARE_VERSION, pname: '查看版本'},
                {pid: PERMISSION.SOFTWARE_SEARCH, pname: '搜索'}]
        },
        {
            id: 3,
            type: 'project',
            name: '软件版本',
            permissions: [
                {pid: PERMISSION.VERSION_CREATE, pname: '添加'},
                {pid: PERMISSION.VERSION_UPDATE, pname: '修改'},
                {pid: PERMISSION.VERSION_UNSHELVE, pname: '下架'}]
        },
        {
            id: 4,
            type: 'system',
            name: '用户管理',
            permissions: [
                {pid: PERMISSION.USER_VIEW, pname: '用户菜单'},
                {pid: PERMISSION.USER_CREATE, pname: '添加'},
                {pid: PERMISSION.USER_UPDATE, pname: '修改'},
                {pid: PERMISSION.USER_DELETE, pname: '删除'},
                {pid: PERMISSION.USER_RESETPSW, pname: '密码重置'},
                {pid: PERMISSION.USER_SEARCH, pname: '搜索'}]
        },
        {
            id: 5,
            type: 'system',
            name: '角色管理',
            permissions: [
                {pid: PERMISSION.ROLE_VIEW, pname: '角色菜单'},
                {pid: PERMISSION.ROLE_CREATE, pname: '添加'},
                {pid: PERMISSION.ROLE_UPDATE, pname: '修改'},
                {pid: PERMISSION.ROLE_DELETE, pname: '删除'},
                {pid: PERMISSION.ROLE_PERMISSION, pname: '权限分配'},
                {pid: PERMISSION.ROLE_USER, pname: '管理成员'},
                {pid: PERMISSION.ROLE_SEARCH, pname: '搜索'}]
        }
    ];

    //选中的权限列表
    this.pselected = [];
    //权限分配中全选框被选中的列表
    this.permissionListSelected = [];

    this.GENERAL_URL = GENERAL_URL;
}


//获取全部角色列表
RoleModel.prototype.loadAllRoles = function (paginationConf, search) {

    var searchURL = this.GENERAL_URL.SHIROROLE_SEARCH + '?searchValue=' + search + '&pageSize=' + this.pageSize +
        '&currentPageNo=' + (paginationConf.currentPage == 0 ? 1 : paginationConf.currentPage);
    var listURL = this.GENERAL_URL.SHIROROLE_ROLELIST_PAGE + '?pageSize=' + this.pageSize +
        '&currentPageNo=' + (paginationConf.currentPage == 0 ? 1 : paginationConf.currentPage);

    var URL = (search || search != "") ? searchURL : listURL;

    var pData = [];
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
        self.lstRole = pData;
    })

};

//添加角色信息
RoleModel.prototype.addRole = function (isValid, role) {

    var self = this;
    var GENERAL_URL = this.GENERAL_URL;

    if (isValid) {
        this.server({
            method: 'POST',
            url: GENERAL_URL.SHIROROLE_ADD,
            data: role,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            if (data.code != 0) {
                alert(data.info);
            } else {
                window.location.href = '#/roleList';
            }
        })
    }
};

//验证角色名是否存在
RoleModel.prototype.ifExistRoleName = function (role, control) {

    if (role == undefined ||
        role.roleName == "" ||
        role.oldRolename == role.roleName) {
        return;
    }

    var searchURL = this.GENERAL_URL.SHIROROLE_ROLENAME_EXISTS + '?rolename=' + role.roleName;
    var self = this;
    this.server({
        method: 'GET',
        url: searchURL,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        role.exists = data.exists;

        if (role.exists == true) {
            role.inputrolename = role.roleName;
            role.roleName = "";
        }
    })

};


//根据roleid从Admin.roleList中获取角色详细记录
RoleModel.prototype.setRoleInstance = function (roleid) {

    for (var i = 0; i < this.lstRole.length; i++) {
        if (this.lstRole[i].roleId == roleid) {
            if (window.localStorage) {
                localStorage[roleid] = JSON.stringify(this.lstRole[i]);
            }
            return this.lstRole[i];
        }
    }
    return null;
};


//修改角色信息
RoleModel.prototype.editRole = function (isValid, role) {

    var self = this;
    var GENERAL_URL = this.GENERAL_URL;

    if (isValid) {
        this.server({
            method: 'PUT',
            url: GENERAL_URL.SHIROROLE_UPDATE,
            data: role,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            if (data.code) {
                alert(data.info);
            } else {
                localStorage.removeItem(role.id); //清除值
                window.location.href = '#/roleList';
            }
        })
    }

    for (var i = 0; i < this.lstRole.length; i++) {
        var str = this.lstRole[i];
        var id = str.id;
        if (role.id == str.id) {
            str.roleName = role.roleName;
            str.description = role.description;
            this.lstRole[i] = str;
            break;
        }

    }
    window.location.href = '#/roleList';
};


// 删除角色信息
RoleModel.prototype.roleDelete = function (role) {

    var state = this.state;
    var GENERAL_URL = this.GENERAL_URL;

    this.server({
        method: 'POST',
        url: GENERAL_URL.SHIROROLE_DELETE,
        data: role,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        if (data.code != 0) {
            alert(data.info);
        } else {
            state.go('roleList', {}, {
                reload: true
            });
        }
    })
};


//管理成员begin
// 保存角色已经选中的成员列表到服务器
RoleModel.prototype.userManage = function (role) {

    var state = this.state;
    role.userList = this.selectedUser;
    var GENERAL_URL = this.GENERAL_URL;

    this.server({
        method: 'POST',
        url: GENERAL_URL.SHIROROLE_USERMANAGE,
        data: role,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        if (data.code != 0) {
            alert(data.info);
        } else {
            state.go('roleList', {}, {
                reload: true
            });
        }
    })
};


//获取角色待选用户列表
RoleModel.prototype.loadUnselectedUserToRole = function (role) {

    var searchURL = this.GENERAL_URL.SHIROROLE_UNSELECTEDUSER + '?roleid=' + role.roleId;
    var pData = [];
    var GENERAL_URL = this.GENERAL_URL;

    this.server({
        method: 'GET',
        url: searchURL
    }).success(function (data, status, headers, config) {
        if (data != "" && data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                pData.push(data[i]);
            }
        }
    });
    return this.unselectedUser = pData;

};

//获取角色选中的用户列表
RoleModel.prototype.loadSelectedUserToRole = function (role) {

    var searchURL = this.GENERAL_URL.SHIROROLE_SELECTEDUSER + '?roleid=' + role.roleId;
    var pData = [];

    this.server({
        method: 'GET',
        url: searchURL
    }).success(function (data, status, headers, config) {
        if (data != "" && data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                pData.push(data[i]);
            }
        }
    });
    return this.selectedUser = pData;

};

//角色选中用户时选择框的左右移动
RoleModel.prototype.selectUserToRole = function ($event, user) {
    var action = $event.target.nodeName;

    if (action == 'DIV') {
        for (var i = 0; i < this.unselectedUser.length; i++) {
            var temp = this.unselectedUser[i];
            if (temp.userId == user.userId) {
                this.unselectedUser.splice(i, 1);
            }

        }

        this.selectedUser.push(user);
    }

    if (action == 'H1') {
        for (var i = 0; i < this.selectedUser.length; i++) {
            var temp = this.selectedUser[i];
            if (temp.userId == user.userId) {
                this.selectedUser.splice(i, 1);
            }

        }

        this.unselectedUser.push(user);
    }

    if (user == 'add') {
        for (var i = 0; i < this.unselectedUser.length; i++) {
            var temp = this.unselectedUser[i];

            this.selectedUser.push(temp);
        }
        this.unselectedUser.splice(0, this.unselectedUser.length);
    }

    if (user == 'remove') {
        for (var i = 0; i < this.selectedUser.length; i++) {
            var temp = this.selectedUser[i];
            this.unselectedUser.push(temp);
        }
        this.selectedUser.splice(0, this.selectedUser.length);
    }
};
//管理成员end


//权限分配begin

//获取角色已经拥有的权限列表
RoleModel.prototype.loadSelectedPermission = function (roleid) {

    var searchURL = this.GENERAL_URL.SHIROROLE_SELECTEDPERMISSION + '?roleid=' + roleid;
    var pData = [];
    this.pselected = [];
    this.permissionListSelected = [];
    var self = this;

    this.server({
        method: 'GET',
        url: searchURL
    }).success(function (data, status, headers, config) {
        if (data != "" && data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                pData.push(data[i].permission);
            }
            self.pselected = pData;
            //生成权限全选的列表
            self.makePermissionListSelected();

        }
    });

};


//角色的权限保存
RoleModel.prototype.roleAddPermission = function (role) {

    var state = this.state;
    role.permissionList = this.pselected;
    var GENERAL_URL = this.GENERAL_URL;

    this.server({
        method: 'POST',
        url: GENERAL_URL.SHIROROLE_UPDATEPERMISSION,
        data: role,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        if (data.code != 0) {
            alert(data.info);
        } else {
            state.go('roleList', {}, {
                reload: true
            });
        }
    })
};

RoleModel.prototype.updateSelected = function (tag, action, id, name) {
    if ('permission' == tag) {
        if (action == 'add' && this.pselected.indexOf(id) == -1) {
            this.pselected.push(id);
        }
        if (action == 'remove' && this.pselected.indexOf(id) != -1) {
            var idx = this.pselected.indexOf(id);
            this.pselected.splice(idx, 1);
        }
    }

    if ('checkall' == tag) {
        if (action == 'add' && this.permissionListSelected.indexOf(id) == -1) {
            this.permissionListSelected.push(id);
        }
        if (action == 'remove' && this.permissionListSelected.indexOf(id) != -1) {
            var idx = this.permissionListSelected.indexOf(id);
            this.permissionListSelected.splice(idx, 1);
        }
    }
};

RoleModel.prototype.updateSelection = function ($event, id, tag) {
    if ('permission' == tag) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');
        this.updateSelected(tag, action, id, checkbox.name);
        this.makePermissionListSelected();
    }

    if ('checkall' == tag) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');
        this.updateSelected(tag, action, id, checkbox.name);

        if ('add' == action) {
            for (var i = 0; i < this.permissionList.length; i++) {
                var pl = this.permissionList[i];
                if (pl.id == id) {
                    var plist = pl.permissions;
                    for (var j = 0; j < plist.length; j++) {
                        var temp = plist[j];
                        if (!this.contains(this.pselected, temp.pid)) {
                            this.pselected.push(temp.pid);

                        }
                    }
                }
            }
        }

        if ('remove' == action) {
            for (var i = 0; i < this.permissionList.length; i++) {
                var pl = this.permissionList[i];
                if (pl.id == id) {
                    var plist = pl.permissions;
                    for (var j = 0; j < plist.length; j++) {
                        var temp = plist[j];
                        if (this.contains(this.pselected, temp.pid)) {
                            var idx = this.pselected.indexOf(temp.pid);
                            this.pselected.splice(idx, 1);

                        }
                    }
                }
            }
        }

    }
};

RoleModel.prototype.isSelected = function (id, tag) {
    if ('permission' == tag) {
        return this.pselected.indexOf(id) >= 0;
    }

    if ('checkall' == tag) {
        return this.permissionListSelected.indexOf(id) >= 0;
    }

};

//权限全选列表的生成
RoleModel.prototype.makePermissionListSelected = function () {
    this.permissionListSelected = [];
    for (var i = 0; i < this.permissionList.length; i++) {
        var p = this.permissionList[i];
        var boo = true;
        var pList = p.permissions;
        for (var j = 0; j < pList.length; j++) {
            var temp = pList[j];
            if (!this.contains(this.pselected, temp.pid)) {
                boo = false;
                break;
            }
        }
        if (boo) {
            this.permissionListSelected.push(p.id);
        }
    }

    return this.permissionListSelected;
};

//权限分配end


//其它方法begin
//判断数组是否包含某个值
RoleModel.prototype.contains = function (a, obj) {
    for (var i = 0; i < a.length; i++) {
        if (a[i] === obj) {
            return true;
        }
    }
    return false;
};
//其它方法end
