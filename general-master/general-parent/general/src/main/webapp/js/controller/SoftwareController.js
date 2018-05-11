ucmedAdmin.controller('softwareController', function ($scope, $stateParams, $state,
                                                      SecurityService, VersionService,
                                                      $http, $location) {

    $scope.versionControl = VersionService.versionControl;
    $scope.security = SecurityService.security;

    if (!$scope.security.validPermission($state)) {
        return;
    }


    //左边菜单栏直接进来的软件列表
    if ($location.path() == '/hospitalSoftwareList') {

        $scope.softwarePaginationConf = {
            currentPage: 1,
            itemsPerPage: VersionService.versionControl.pageSize
        };

        $scope.versionControl.softwareSearch = "";

        var reGetSoftware = function () {
            $scope.startindex = ($scope.softwarePaginationConf.currentPage - 1) * $scope.softwarePaginationConf.itemsPerPage + 1;
            $scope.versionControl.seachSoftwareList($scope.softwarePaginationConf,
                $scope.versionControl.softwareSearch);
        };
        $scope.$watch('softwarePaginationConf.currentPage', reGetSoftware);

    }


    // 软件列表
    if ($stateParams.sPid != null) {
        $scope.paginationConf = {
            currentPage: 1,
            itemsPerPage: VersionService.versionControl.pageSize
        };
        var reGetSoftwares = function () {
            $scope.startIndex = ($scope.paginationConf.currentPage - 1)
                * $scope.paginationConf.itemsPerPage + 1;
            $scope.versionControl.loadSoftwareList($stateParams.sPid, $scope);
        };
        $scope.$watch('paginationConf.currentPage', reGetSoftwares);
    }

    // 添加软件
    if ($stateParams.sfPid != null) {
        $scope.softwareP = {};
        $scope.softwareP.vcProjectId = $stateParams.sfPid;
    }
    // 修改软件
    if ($stateParams.sid != null) {
        $scope.software = $scope.versionControl
            .setSoftwareInstance($stateParams.sid);


        if (!$scope.software && window.localStorage) {
            $scope.software = JSON.parse(localStorage[$stateParams.sid]);
        }

        //保存打开页面时的commonName，用于commonName的重复判断检测
        $scope.software.oldCommonName = $scope.software.commonName;
    }


    //添加软件（菜单栏）
    if ($location.path() == '/hospitalSoftwareList/softWaresAdd') {

        //加载医院和项目信息,以供添加软件时选中哪家医院和项目
        $scope.versionControl.loadHospitalAndProjectList();
        $scope.versionControl = VersionService.versionControl;
    }


    // 修改软件（菜单栏）
    if ($stateParams.softwareId != null) {

        $scope.hSoftware = $scope.versionControl
            .setHospitalSoftwareInstance($stateParams.softwareId);


        if (!$scope.hSoftware && window.localStorage) {
            $scope.hSoftware = JSON.parse(localStorage[$stateParams.softwareId]);
        }

        //保存打开页面时的commonName，用于commonName的重复判断检测
        $scope.hSoftware.oldCommonName = $scope.hSoftware.commonName;

        //医院列表
        $scope.hlist = $scope.hSoftware.hlist;

        //获取该软件的医院的所有项目列表
        for (var i = 0; i < $scope.hlist.length; i++) {
            var h = $scope.hlist[i];
            if (h.hospitalName == $scope.hSoftware.hospitalName) {
                VersionService.versionControl.projectList = h.projectList;
            }
        }
    }


    $scope.searchKeyDown = function (ev, tag) {

        if (tag != 'enter' || ev.keyCode == 13) {
            $scope.softwareSearch = $scope.versionControl.softwareSearch;
            $scope.softwarePaginationConf.currentPage = 1;
            $scope.versionControl.seachSoftwareList($scope.softwarePaginationConf, $scope.versionControl.softwareSearch);
        }
    }


});