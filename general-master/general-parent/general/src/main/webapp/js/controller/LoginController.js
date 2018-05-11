ucmedAdmin.controller('LoginController',
    function ($scope, $rootScope, $location, SecurityService, PERMISSION) {

        $scope.security = SecurityService.security;

        if ($rootScope.isLogin) {
            $scope.security.ClearCredentials();
        }

        $scope.login = function () {
            $scope.dataLoading = true;
            $scope.security.Login($scope.username, $scope.password, function (response) {
                if (response.success) {
                    if ($scope.security.hasPermission(PERMISSION.PROJECT_VIEW)) {
                        $location.path('projects');
                    }
                    else if ($scope.security.hasPermission(PERMISSION.SOFTWARE_VIEW)) {
                        $location.path('hospitalSoftwareList');
                    }
                    else if ($scope.security.hasPermission(PERMISSION.USER_VIEW)) {
                        $location.path('userList');
                    }
                    else if ($scope.security.hasPermission(PERMISSION.ROLE_VIEW)) {
                        $location.path('roleList');
                    }
                    else {
                        $location.path('UnauthorizedAccess');
                    }

                } else {
                    $scope.security.ClearCredentials();
                    $scope.error = response.message;
                }
                $scope.dataLoading = false;
            });
        };
    });