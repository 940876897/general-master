ucmedAdmin.controller('roleController', function ($scope, $stateParams, $state,
                                                  SecurityService, $http, $location,
                                                  ngDialog) {

    $scope.roleModel = SecurityService.roleModel;
    $scope.security = SecurityService.security;

    if (!$scope.security.validPermission($state)) {
        return;
    }

    if ($location.path() == '/roleList') {

        $scope.paginationConf = {
            currentPage: 1,
            itemsPerPage: SecurityService.roleModel.pageSize
        };

        $scope.roleModel.search = "";

        var reGetRoles = function () {
            $scope.startIndex = ($scope.paginationConf.currentPage - 1) * $scope.paginationConf.itemsPerPage + 1;
            $scope.roleModel.loadAllRoles($scope.paginationConf,
                $scope.roleModel.search);
        };
        $scope.$watch('paginationConf.currentPage', reGetRoles);
    }


    // 角色详细
    if ($stateParams.roleid != null) {
        $scope.role = $scope.roleModel
            .setRoleInstance($stateParams.roleid);

        if (!$scope.role && window.localStorage) {
            $scope.role = JSON.parse(localStorage[$stateParams.roleid]);
        }

        //保存打开页面时的rolename，用于rolename的重复判断检测
        $scope.role.oldRolename = $scope.role.roleName;
    }


    //删除角色信息
    $scope.deleteRole = function (role) {
        ngDialog.openConfirm({
            template: 'templates/deleteRole.html',
            className: 'ngdialog-theme-default',
            controller: ['$scope', function ($scope) {
                $scope.data = role;
            }]
        }).then(function (value) {
            SecurityService.roleModel.roleDelete(value);
        }, function (reason) {
        });
    };


    //管理成员
    $scope.manageUser = function (role) {
        ngDialog.openConfirm({
            template: 'templates/manageUser.html',
            className: 'ngdialog-theme-default',
            controller: ['$scope', function ($scope) {
                $scope.data = role;
                $scope.unselectedUser = SecurityService.roleModel.loadUnselectedUserToRole(role);
                $scope.selectedUser = SecurityService.roleModel.loadSelectedUserToRole(role);
                $scope.roleModel = SecurityService.roleModel;
            }]
        }).then(function (value) {
            SecurityService.roleModel.userManage(value);
        }, function (reason) {
        });
    };


    // 权限分配
    if ($stateParams.rid != null) {
        $scope.role = $scope.roleModel
            .setRoleInstance($stateParams.rid);
        if (!$scope.role && window.localStorage) {
            $scope.role = JSON.parse(localStorage[$stateParams.rid]);
        }

        //获取选中的权限
        $scope.roleModel.loadSelectedPermission($stateParams.rid);
        $scope.roleModel = SecurityService.roleModel;

    }


    $scope.searchKeyDown = function (ev, tag) {
        if (tag != 'enter' || ev.keyCode == 13) {
            $scope.search = $scope.roleModel.search;
            $scope.paginationConf.currentPage = 1;
            $scope.roleModel.loadAllRoles($scope.paginationConf, $scope.roleModel.search);
        }
    }


});
