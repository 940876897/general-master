//构造VersionControl对象
function VersionControl($http, $state, uiUploader) {

    //初始化每页条数
    this.pageSize = 10;

    this.lstProject = [];
    this.lstSoftware = [];
    this.lstVersion = [];

    this.search = "";
    this.softwareSearch = "";


    this.server = $http;
    this.state = $state;
    this.uploader = uiUploader;

    this.softwareList = [];

    //添加软件时医院的列表
    this.hospitalList = [];

    //添加软件时项目的列表
    this.projectList = [];
}

VersionControl.prototype.addProject = function (isValid, project) {

    var self = this;
    if (isValid) {
        this.server({
            method: 'POST',
            url: '/vcProject/add.json',
            data: project,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            if (data.code != 0) {
                alert(data.info);
            } else {
                window.location.href = '#/projects';
            }
        })
    }
}

VersionControl.prototype.updateProject = function (isValid, project) {

    var self = this;
    if (isValid) {
        this.server({
            method: 'POST',
            url: '/vcProject/update.json',
            data: project,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            if (data.code) {
                alert(data.info);
            } else {
                localStorage.removeItem(project.vcProjectId); //清除值
                window.location.href = '#/projects';
            }
        })
    }
    ;
}

VersionControl.prototype.addSoftware = function (isValid, software) {

    if (software && software.projectId && software.projectId.vcProjectId) {
        software.vcProjectId = software.projectId.vcProjectId;
    } else {
        software.vcProjectId = this.currentProjectId;
    }


    var state = this.state;
    if (isValid) {
        this.server({
            method: 'POST',
            url: '/vcProjectSoftware/add.json',
            data: software,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            if (data.code != 0) {
                alert(data.info);
            } else {
                state.go('^');
            }
        })
    }
        this.currentProjectId = null;

};


VersionControl.prototype.updateSoftware = function (isValid, software) {

    //序列化json时间
    software.createdon = new Date(software.createdon.time);
    software.modifiedon = new Date(software.modifiedon.time);

    if (software.latestVersion) {
        software.latestVersion.modifiedon = new Date(software.latestVersion.modifiedon.time);
    }

    var state = this.state;
    if (isValid) {
        this.server({
            method: 'POST',
            url: '/vcProjectSoftware/update.json',
            data: software,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            if (data.code != 0) {
                alert(data.info);
            } else {
                localStorage.removeItem(software.vcProjectSoftwareId); //清除值
                state.go('^');
            }
        })
    }
};


VersionControl.prototype.updateVersion = function (isValid, version) {

    //序列化json时间
    version.createdon = new Date(version.createdon.time);
    version.modifiedon = new Date(version.modifiedon.time);
    version.releaseTime = new Date(version.releaseTime.time);

    var state = this.state;
    if (isValid) {
        this.server({
            method: 'POST',
            url: '/vcSoftwareVersion/update.json',
            data: version,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            if (data.code != 0) {
                alert(data.info);
            }
            else {
                localStorage.removeItem(version.vcSoftwareVersionId); //清除值
                state.go('^');
            }
        })
    }
};

VersionControl.prototype.addVersion = function (isValid, version) {

    version.vcProjectSoftwareId = this.currentSoftwareId;
    var state = this.state;
    if (isValid) {
        this.server({
            method: 'POST',
            url: '/vcSoftwareVersion/add.json',
            data: version,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            if (data.code != 0) {
                alert(data.info);
            }
            else {
                this.currentSoftwareId = null;
                state.go('^');
            }
        })
    }

};

// 下架版本
VersionControl.prototype.versionsOffShelves = function (version, $scope) {
    //序列化json时间
    version.createdon = new Date(version.createdon.time);
    version.modifiedon = new Date(version.modifiedon.time);
    version.releaseTime = new Date(version.releaseTime.time);
    var self = this;
    var state = this.state;
    this.server({
        method: 'POST',
        url: '/vcSoftwareVersion/offShelves.json',
        data: version,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {

        if (data.code != 0) {
            alert(data.info);
        } else {
            state.go(state.current.name, {}, {
                reload: true
            });
        }
    })
};


//获取项目列表
VersionControl.prototype.loadAllProjects = function (paginationConf, search) {

    var searchURL = '/vcProject/list.json?pageSize=' + this.pageSize + '&currentPageNo=' + (paginationConf.currentPage == 0 ? 1 : paginationConf.currentPage);
    if (search || search != "") {
        searchURL += '&searchValue=' + search;
    }
    var pData = new Array();
    var self = this;
    this.server({
        method: 'GET',
        url: searchURL
    }).success(function (data, status, headers, config) {
        if (data != "" && data.list.length > 0) {
            for (var i = 0; i < data.list.length; i++) {
                pData.push(data.list[i]);
            }
            paginationConf.totalItems = data.totalCount;
        }
        self.lstProject = pData;
    })
}





//根据软件的项目Id获取软件列表
VersionControl.prototype.loadSoftwareList = function (sPid, $scope) {

    var sData = new Array();
    var self = this;

    this.server({
        method: 'GET',
        url: '/vcProjectSoftware/projectSoftwareList.json?vcProjectId=' + sPid + '&currentPageNo=' + ($scope.paginationConf.currentPage == 0 ? 1 : $scope.paginationConf.currentPage) + '&pageSize=' + this.pageSize
    }).success(function (data, status, headers, config) {
        for (var i = 0; i < data.softWareList.list.length; i++) {
            sData.push(data.softWareList.list[i]);
        }
        $scope.paginationConf.totalItems = data.softWareList.totalCount;
        $scope.softWareTitle = data.hospital_name + '(' + data.project_name + ')';
    })
    this.lstSoftware = sData;
    this.currentProjectId = sPid;
}

//根据版本的软件ID获取版本列表
VersionControl.prototype.loadVersionList = function (vSid, $scope) {

    var vData = new Array();
    var self = this;

    this.server({
        method: 'GET',
        url: '/vcSoftwareVersion/list.json?softwareId=' + vSid + '&currentPageNo=' + ($scope.paginationConf.currentPage == 0 ? 1 : $scope.paginationConf.currentPage) + '&pageSize=' + this.pageSize
    }).success(function (data, status, headers, config) {

        for (var i = 0; i < data.softWareVersionList.list.length; i++) {
            vData.push(data.softWareVersionList.list[i]);
        }
        $scope.paginationConf.totalItems = data.softWareVersionList.totalCount;

        var type = "";
        switch (data.software_type) {
            case "1":
                type = 'Android';
                break;
            case "2":
                type = 'iOS';
                break;
            case "3":
                type = 'WinStore';
                break;
            case "4":
                type = 'WinPhone';
                break;
            default:
                type = 'default Type';
                break;
        }
        $scope.versionSubTitle = data.software_name + ' ' + type + '(' + data.common_name + ')';
        $scope.versionTitle = data.hospital_name + '(' + data.project_name + ')';
        self.versionSubTitleForAdd = data.software_name + ' ' + type + '(' + data.common_name + ')';
        self.commonName = data.common_name;
        self.maxAppVersion = ':最新版本号(' + data.maxAppVersion + ')';
        self.maxZipVersion = ':最新版本号(' + data.maxZipVersion + ')';
    })
    this.lstVersion = vData;
    this.currentSoftwareId = vSid;
}

//根据projectId从Admin.projects中获取project详细记录
VersionControl.prototype.setProjectInstance = function (pid) {

    for (var i = 0; i < this.lstProject.length; i++) {
        if (this.lstProject[i].vcProjectId == pid) {
            if (window.localStorage) {
                localStorage[pid] = JSON.stringify(this.lstProject[i]);
            }
            return this.lstProject[i];
        }
    }
    return null;
}

VersionControl.prototype.setSoftwareInstance = function (sid) {

    for (var i = 0; i < this.lstSoftware.length; i++) {
        if (this.lstSoftware[i].vcProjectSoftwareId == sid) {
            if (window.localStorage) {
                localStorage[sid] = JSON.stringify(this.lstSoftware[i]);
            }
            return this.lstSoftware[i];
        }
    }
    return null;
}

VersionControl.prototype.setVersionInstance = function (vid) {

    for (var i = 0; i < this.lstVersion.length; i++) {
        if (this.lstVersion[i].vcSoftwareVersionId == vid) {
            if (window.localStorage) {
                localStorage[vid] = JSON.stringify(this.lstVersion[i]);
            }
            return this.lstVersion[i];
        }
    }
    return null;
}

// 文件上传：删除所有已添加到上传列表的文件
VersionControl.prototype.btn_clean = function () {
    this.uploader.removeAll();
}

VersionControl.prototype.file_type = function (file, type) {
    return (file.uploadType == type);
}

// 文件上传：上传文件到服务器
VersionControl.prototype.btn_upload = function (ngDialog,$scope, filePath, fileType, commonName, versionNumber) {
    var scope=$scope;
    var self = this;
    var info='您确认要上传该文件吗？';
    var boo=false;
    if (fileType == 'app' && !versionNumber) {
        info='请输入应用版本号';
        boo=true;
    }
    if (fileType == 'app' && '无' == commonName) {
        info='通用名为空时不能上传文件';
        boo=true;
    }


ngDialog.openConfirm({
        template: 'templates/uploadInfo.html',
        className: 'ngdialog-theme-plain',
        controller: ['$scope', function ($scope) {
            var data={"upShow":true,"info":info,"filePath":filePath,"fileType":fileType,"commonName":commonName,"versionNumber":versionNumber};
            $scope.data = data;
            $scope.upload = function(value){

                if(boo){
                    this.closeThisDialog('button');
                    return;
                }

                $scope.data.info="正在上传....";
                $scope.data.upShow=false;


                self.uploader.startUpload({
                    url: '/upload/uploadTest.json?filePath=' + value.filePath + '&fileType=' + value.fileType + '&commonName=' + value.commonName + '&versionNumber=' + value.versionNumber,
                    concurrency: 3,
                    onProgress: function (file) {
                        $scope.$apply();
                    },
                    onCompleted: function (file, response) {
                        var res = eval('(' + response + ')');
                        if (eval('(' + response + ')').isSuccess == 'Y') {
                            $scope.data.info=info=eval('(' + response + ')').desc;
                            if (file.uploadType == 'zip') {
                                scope.version.zipDownloadUrl = res.url;
                            } else {
                                scope.version.appDownloadUrl = res.url;
                            }
                            $scope.$apply();
                        } else {
                            $scope.data.info=eval('(' + response + ')').desc;
                        }
                    },
                    onCompletedAll: function () {
                    }
                });


            };
        }]
    }).then(function (value) {

    }, function (reason) {
    });

}






VersionControl.prototype.addToUiUploader = function (e, fileType, $scope) {
    var files = e.target.files;
    files[0].uploadType = fileType;
    this.uploader.addFiles(files);
    $scope.files = this.uploader.getFiles();
    $scope.$apply();
}








/*******(菜单栏)************/

//获取软件列表(菜单栏)和搜索软件列表
VersionControl.prototype.seachSoftwareList = function (paginationConf, search) {

    var searchURL = '/vcProjectSoftware/seachSoftware.json?searchValue=' + search + '&pageSize=' + this.pageSize +
        '&currentPageNo=' + (paginationConf.currentPage == 0 ? 1 : paginationConf.currentPage);
    var listURL = '/vcProjectSoftware/hospitalSoftwareList.json?pageSize=' + this.pageSize +
        '&currentPageNo=' + (paginationConf.currentPage == 0 ? 1 : paginationConf.currentPage);

    var URL = (search || search != "") ? searchURL : listURL;

    var pData = new Array();
    this.softwareList=pData;
    var self = this;
    this.server({
        method: 'GET',
        url: searchURL
    }).success(function (data, status, headers, config) {

        if (data != "" && data.softWareList.list.length > 0) {
            for (var i = 0; i < data.softWareList.list.length; i++) {
                pData.push(data.softWareList.list[i]);
            }
            paginationConf.totalItems = data.softWareList.totalCount;
        }
        self.softwareList = pData;
    })

    //加载医院信息(包含每家医院的项目列表),编辑软件的时候需要医院列表和项目列表提前加载才可以默认选中软件所属的医院和项目
    this.loadHospitalProjectList();
}


//加载医院列表（包含项目列表）,以供编辑软件
VersionControl.prototype.loadHospitalProjectList = function () {
    this.hospitalList = [];
    var URL = '/vcProject/hospitalProjectList.json';
    var hData = new Array();
    var self = this;
    this.server({
        method: 'GET',
        url: URL
    }).success(function (data, status, headers, config) {

        if (data != "" && data.hlist.length > 0) {
            for (var i = 0; i < data.hlist.length; i++) {
                hData.push(data.hlist[i]);
            }
        }
        self.hospitalList = hData;
    })
}



//加载医院列表和项目列表,以供添加软件时选中哪家医院和项目
VersionControl.prototype.loadHospitalAndProjectList = function () {
    this.hospitalList = [];
    this.projectList = [];
    var URL = '/vcProject/hospitalAndProjectList.json';
    var hData = new Array();
    var pData = new Array();
    var self = this;
    this.server({
        method: 'GET',
        url: URL
    }).success(function (data, status, headers, config) {

        if (data != "" && data.hlist.length > 0) {
            for (var i = 0; i < data.hlist.length; i++) {
                hData.push(data.hlist[i]);
            }
        }
        if (data != "" && data.plist.length > 0) {
            for (var i = 0; i < data.plist.length; i++) {
                pData.push(data.plist[i]);
            }
        }
        self.hospitalList = hData;
        self.projectList = pData;
    })
}

//添加软件时改变医院级联改变项目列表
VersionControl.prototype.selectHospitalChang = function (h) {
    if (h.hospitalId && h.hospitalId.hospitalName) {
        this.projectList = [];
        var URL = '/vcProject/projectListByHospitalName.json?hospitalName=' + h.hospitalId.hospitalName;
        var pData = new Array();
        var self = this;
        this.server({
            method: 'GET',
            url: URL
        }).success(function (data, status, headers, config) {
            self.projectList = [];
            if (data != "" && data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    pData.push(data[i]);
                }
            }
            self.projectList = pData;
        })
    }

    h.projectId = "";

}



//编辑软件时获取该软件的信息,软件信息中包含了医院和医院的项目列表
VersionControl.prototype.setHospitalSoftwareInstance = function (softwateId) {

    for (var i = 0; i < this.softwareList.length; i++) {
        if (this.softwareList[i].vcProjectSoftwareId == softwateId) {
            if (window.localStorage) {
                this.softwareList[i].hlist=this.hospitalList;
                localStorage[softwateId] = JSON.stringify(this.softwareList[i]);
            }
            return this.softwareList[i];
        }
    }
    return null;
}


//编辑软件时，选中医院获取不同医院的项目列表
VersionControl.prototype.selectHospitalChangEdit = function (h) {
    if (h.hospitalName) {
        this.projectList = [];
        var URL = '/vcProject/projectListByHospitalName.json?hospitalName=' + h.hospitalName;
        var pData = new Array();
        var self = this;
        this.server({
            method: 'GET',
            url: URL
        }).success(function (data, status, headers, config) {
            self.projectList = [];
            if (data != "" && data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    pData.push(data[i]);
                }
            }
            self.projectList = pData;
        })
    }

    h.projectName = "";

}

//编辑软件时，选择项目时设置项目对应的ID
VersionControl.prototype.changProjectIdEdit = function (p) {
    this.projectList;
    for(var i=0;i<this.projectList.length;i++){
        var project=this.projectList[i];
        if(p.projectName==project.projectName){
            p.vcProjectId=project.vcProjectId;
        }

    }
}





//验证通用名是否存在
VersionControl.prototype.ifExistCommonName = function (software, control) {

    if (software == undefined ||software.commonName ==undefined||
        software.commonName == "" ||
        software.oldCommonName == software.commonName) {
        return;
    }

    var searchURL = '/vcProjectSoftware/commonNameIfExist.json?commonName=' + software.commonName;

    this.server({
        method: 'GET',
        url: searchURL,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        software.exists = data.exists;

        if (software.exists == true) {
            software.inputcommonName = software.commonName;
            software.commonName = "";
        }
    })
}










