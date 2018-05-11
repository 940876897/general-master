ucmedAdmin.controller('userController', function ($scope, $stateParams, $state,
                                                  SecurityService, $http, $location,
                                                  ngDialog) {

    $scope.userModel = SecurityService.userModel;
    $scope.security = SecurityService.security;

    if (!$scope.security.validPermission($state)) {
        return;
    }

    if ($location.path() == '/userList') {

        $scope.paginationConf = {
            currentPage: 1,
            itemsPerPage: SecurityService.userModel.pageSize
        };

        $scope.userModel.search = "";

        var reGetUsers = function () {
            $scope.startIndex = ($scope.paginationConf.currentPage - 1) * $scope.paginationConf.itemsPerPage + 1;
            $scope.userModel.loadAllUsers($scope.paginationConf,
                $scope.userModel.search);
        };
        $scope.$watch('paginationConf.currentPage', reGetUsers);
    }

    // 修改时的用户详细
    if ($stateParams.userid != null) {
        $scope.user = $scope.userModel
            .setUserInstance($stateParams.userid);


        if (!$scope.user && window.localStorage) {
            $scope.user = JSON.parse(localStorage[$stateParams.userid]);
        }
        //保存打开页面时的username，用于username的重复判断检测
        $scope.user.oldUsername = $scope.user.username;

        //获取该用户具有的角色
        $scope.userModel.loadRoleToUserId($stateParams.userid);

        //获取全部角色列表
        $scope.userModel.loadAllRoles();
        $scope.view = true;
        $scope.toggle = function () {
            $scope.view = !$scope.view;
        };
    }


    //添加用户信息时获取的所有角色
    if ($location.path() == '/userList/userAdd') {
        $scope.userModel.loadAllRoles();
        $scope.view = true;
        $scope.toggle = function () {
            $scope.view = !$scope.view;
        };
    }


    //删除用户信息
    $scope.deleteUser = function (user) {
        ngDialog.openConfirm({
            template: 'templates/deleteUser.html',
            className: 'ngdialog-theme-default',
            controller: ['$scope', function ($scope) {
                $scope.data = user;
            }]
        }).then(function (value) {
            SecurityService.userModel.userDelete(value, $scope);
        }, function (reason) {
        });
    };


    //重置密码
    $scope.restPassword = function (user) {
        ngDialog.openConfirm({
            template: 'templates/initPassword.html',
            className: 'ngdialog-theme-default',
            controller: ['$scope', function ($scope) {
                $scope.data = user;
            }]
        }).then(function (value) {
            $scope.security.passwordRest(value, $scope);
        }, function (reason) {
        });
    };


    $scope.searchKeyDown = function (ev, tag) {
        if (tag != 'enter' || ev.keyCode == 13) {
            $scope.search = $scope.userModel.search;
            $scope.paginationConf.currentPage = 1;
            $scope.userModel.loadAllUsers($scope.paginationConf, $scope.userModel.search);
        }
    }

});
