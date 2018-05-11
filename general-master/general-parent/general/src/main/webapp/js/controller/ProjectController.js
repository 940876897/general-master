ucmedAdmin.controller('projectController', function ($scope, $stateParams, $state,
                                                     SecurityService, VersionService,
                                                     $http, $location) {

    $scope.versionControl = VersionService.versionControl;
    $scope.security = SecurityService.security;

    if (!$scope.security.validPermission($state)) {
        return;
    }

    if ($location.path() == '/projects') {

        $scope.paginationConf = {
            currentPage: 1,
            itemsPerPage: VersionService.versionControl.pageSize
        };

        $scope.versionControl.search = "";

        var reGetProjects = function () {
            $scope.startIndex = ($scope.paginationConf.currentPage - 1) * $scope.paginationConf.itemsPerPage + 1;
            $scope.versionControl.loadAllProjects($scope.paginationConf,
                $scope.versionControl.search);
        };
        $scope.$watch('paginationConf.currentPage', reGetProjects);
    }

    // 项目详细
    if ($stateParams.pid != null) {
        $scope.project = $scope.versionControl
            .setProjectInstance($stateParams.pid);
        if (!$scope.project && window.localStorage) {
            $scope.project = JSON.parse(localStorage[$stateParams.pid]);
        }
    }

    $scope.searchKeyDown = function (ev) {
        //只响应回车时间
        if (ev.keyCode == 13) {
            $scope.search = $scope.versionControl.search;
            $scope.paginationConf.currentPage = 1;
            $scope.versionControl.loadAllProjects($scope.paginationConf, $scope.versionControl.search)
        }
    }

});