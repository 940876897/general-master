ucmedAdmin.controller('versionController', function ($scope, $stateParams, $state,
                                                     VersionService, SecurityService,
                                                     $http, $location, ngDialog) {

    $scope.versionControl = VersionService.versionControl;
    $scope.security = SecurityService.security;

    if (!$scope.security.validPermission($state)) {
        return;
    }

    // 版本列表
    if ($stateParams.vSid != null) {
        $scope.paginationConf = {
            currentPage: 1,
            itemsPerPage: VersionService.versionControl.pageSize
        };
        var reGetVersions = function () {
            $scope.startIndex = ($scope.paginationConf.currentPage - 1)
                * $scope.paginationConf.itemsPerPage + 1;
            $scope.versionControl.loadVersionList($stateParams.vSid, $scope);
        };
        $scope.$watch('paginationConf.currentPage', reGetVersions);
    }

    // 版本修改或添加
    var path = $location.path();
    path = path.substring(path.lastIndexOf("/"));
    if ($stateParams.vid != null || path == "/versionsAdd") {
        $scope.today = new Date();
        if ($stateParams.vid != null) {
            $scope.version = $scope.versionControl
                .setVersionInstance($stateParams.vid);
            if (!$scope.version && window.localStorage) {
                $scope.version = JSON.parse(localStorage[$stateParams.vid]);
            }
        } else {
            $scope.version = {};
            var fileUrl = $location.$$protocol + "://" + $location.$$host + ":"
                + $location.$$port + "/";
            $scope.version.appDownloadUrl = fileUrl;
            $scope.version.zipDownloadUrl = fileUrl;
        }

        $scope.files = [];
        var element = document.getElementById('zip_file');
        element.addEventListener('change', function (e) {
            $scope.versionControl.addToUiUploader(e, 'zip', $scope)
        });

        var af = document.getElementById('apk_file');
        af.addEventListener('change', function (e) {
            $scope.versionControl.addToUiUploader(e, 'apk', $scope)
        });

        // 文件上传，传递$scope给model。
        $scope.uploadFile = function (filePath, fileType, commonName, versionNumber) {
            $scope.versionControl.btn_upload(ngDialog,$scope, filePath, fileType, commonName, versionNumber);
        }
    }

    // 下架版本
    $scope.versionsOff = function (version) {
        ngDialog.openConfirm({
            template: 'templates/offShelvesVersion.html',
            className: 'ngdialog-theme-default',
            controller: ['$scope', function ($scope) {
                $scope.data = version;
            }]
        }).then(function (value) {
            VersionService.versionControl.versionsOffShelves(value, $scope);
        }, function (reason) {
        });
    };

});