ucmedAdmin.config(function ($stateProvider, $urlRouterProvider, $httpProvider, PERMISSION) {
    $stateProvider

        .state('login', {
            url: '/login',
            templateUrl: 'pages/login.html',
            controller: 'LoginController'
        })

        .state('logout', {
            url: '/logout',
            templateUrl: 'pages/logout.html',
            controller: 'LoginController'
        })

        .state('projects', {
            url: '/projects',
            templateUrl: 'pages/projectList.html',
            controller: 'projectController',
            permission: PERMISSION.PROJECT_VIEW,
            ncyBreadcrumb: {
                label: '项目列表'
            }
        })

        .state('projects.projectsAdd', {
            url: '/projectsAdd',
            views: {
                "@": {
                    templateUrl: 'pages/addProject.html',
                    controller: 'projectController'
                }
            },
            permission: PERMISSION.PROJECT_CREATE,
            ncyBreadcrumb: {
                label: '添加项目'
            }
        })

        .state('projects.projectsEdit', {
            url: '/:pid/projectsEdit',
            views: {
                "@": {
                    templateUrl: 'pages/editProject.html',
                    controller: 'projectController'
                }
            },
            permission: PERMISSION.PROJECT_UPDATE,
            ncyBreadcrumb: {
                label: '修改项目'
            }
        })

        .state('projects.softWaresList', {
            url: '/softWares/:sPid',
            views: {
                "@": {
                    templateUrl: 'pages/softwareList.html',
                    controller: 'softwareController'
                }
            },
            permission: PERMISSION.PROJECT_SOFTWARE,
            ncyBreadcrumb: {
                label: '项目软件'
            }
        })

        .state('projects.softWaresList.softWaresAdd', {
            url: '/softWaresAdd',
            views: {
                "@": {
                    templateUrl: 'pages/addSoftware.html',
                    controller: 'softwareController'
                }
            },
            permission: PERMISSION.SOFTWARE_CREATE,
            ncyBreadcrumb: {
                label: '添加软件'
            }
        })

        .state('projects.softWaresList.softWaresEdit', {
            url: '/:sid/softWaresEdit',
            views: {
                "@": {
                    templateUrl: 'pages/editSoftware.html',
                    controller: 'softwareController'
                }
            },
            permission: PERMISSION.SOFTWARE_UPDATE,
            ncyBreadcrumb: {
                label: '修改软件'
            }
        })

        .state('projects.softWaresList.versionsList', {
            url: '/versions/:vSid',
            views: {
                "@": {
                    templateUrl: 'pages/versionList.html',
                    controller: 'versionController',
                }
            },
            permission: PERMISSION.SOFTWARE_VERSION,
            ncyBreadcrumb: {
                label: '软件版本',
            }
        })
        .state('hospitalSoftwareList.versionsList', {
            url: '/versions/:vSid',
            views: {
                "@": {
                    templateUrl: 'pages/versionList.html',
                    controller: 'versionController',
                }
            },
            permission: PERMISSION.SOFTWARE_VERSION,
            ncyBreadcrumb: {
                label: '软件版本',
            }
        })

        .state('projects.softWaresList.versionsList.versionsAdd', {
            url: '/versionsAdd',
            views: {
                "@": {
                    templateUrl: 'pages/addVersion.html',
                    controller: 'versionController'
                }
            },
            permission: PERMISSION.VERSION_CREATE,
            ncyBreadcrumb: {
                label: '添加版本'
            }
        })

        .state('projects.softWaresList.versionsList.versionsEdit', {
            url: '/:vid/versionsEdit',
            views: {
                "@": {
                    templateUrl: 'pages/editVersion.html',
                    controller: 'versionController'
                }
            },
            permission: PERMISSION.VERSION_UPDATE,
            ncyBreadcrumb: {
                label: '修改版本'
            }
        })

        .state('userList', {
            url: '/userList',
            templateUrl: 'pages/userList.html',
            controller: 'userController',
            permission: PERMISSION.USER_VIEW,
            ncyBreadcrumb: {
                label: '用户管理'
            }
        })

        .state('userList.userAdd', {
            url: '/userAdd',
            views: {
                "@": {
                    templateUrl: 'pages/addUser.html',
                    controller: 'userController'
                }
            },
            permission: PERMISSION.USER_CREATE,
            ncyBreadcrumb: {
                label: '添加用户'
            }
        })

        .state('userList.userEdit', {
            url: '/:userid/userEdit',
            views: {
                "@": {
                    templateUrl: 'pages/editUser.html',
                    controller: 'userController'
                }
            },
            permission: PERMISSION.USER_UPDATE,
            ncyBreadcrumb: {
                label: '修改用户'
            }
        })

        .state('roleList', {
            url: '/roleList',
            templateUrl: 'pages/roleList.html',
            controller: 'roleController',
            permission: PERMISSION.ROLE_VIEW,
            ncyBreadcrumb: {
                label: '角色管理'
            }
        })

        .state('roleList.roleAdd', {
            url: '/roleAdd',
            views: {
                "@": {
                    templateUrl: 'pages/addRole.html',
                    controller: 'roleController'
                }
            },
            permission: PERMISSION.ROLE_CREATE,
            ncyBreadcrumb: {
                label: '添加角色'
            }
        })

        .state('roleList.roleEdit', {
            url: '/:roleid/roleEdit',
            views: {
                "@": {
                    templateUrl: 'pages/editRole.html',
                    controller: 'roleController'
                }
            },
            permission: PERMISSION.ROLE_UPDATE,
            ncyBreadcrumb: {
                label: '修改角色'
            }
        })

        .state('roleList.permissionAssignment', {
            url: '/:rid/permissionAssignment',
            views: {
                "@": {
                    templateUrl: 'pages/permissionAssignment.html',
                    controller: 'roleController'
                }
            },
            permission: PERMISSION.ROLE_PERMISSION,
            ncyBreadcrumb: {
                label: '权限分配'
            }
        })

        .state('UnauthorizedAccess', {
            url: '/UnauthorizedAccess',
            views: {
                "@": {
                    templateUrl: 'pages/noPermission.html',
                    controller: 'baseController'
                }
            },
            ncyBreadcrumb: {
                label: '权限不足'
            }
        })
        .state('hospitalSoftwareList', {
            url: '/hospitalSoftwareList',
            views: {
                "@": {
                    templateUrl: 'pages/hospitalSoftwareList.html',
                    controller: 'softwareController'
                }
            },
            permission: PERMISSION.SOFTWARE_VIEW,
            ncyBreadcrumb: {
                label: '软件列表'
            }
        })
        .state('hospitalSoftwareList.softWaresAdd', {
            url: '/softWaresAdd',
            views: {
                "@": {
                    templateUrl: 'pages/addHospitalSoftware.html',
                    controller: 'softwareController'
                }
            },
            permission: PERMISSION.SOFTWARE_CREATE,
            ncyBreadcrumb: {
                label: '添加软件'
            }
        })
        .state('hospitalSoftwareList.softWaresEdit', {
            url: '/:softwareId/softWaresEdit',
            views: {
                "@": {
                    templateUrl: 'pages/editHospitalSoftware.html',
                    controller: 'softwareController'
                }
            },
            permission: PERMISSION.SOFTWARE_UPDATE,
            ncyBreadcrumb: {
                label: '修改软件'
            }
        })
        .state('hospitalSoftwareList.versionsList.versionsAdd', {
            url: '/versionsAdd',
            views: {
                "@": {
                    templateUrl: 'pages/addVersion.html',
                    controller: 'versionController'
                }
            },
            permission: PERMISSION.VERSION_CREATE,
            ncyBreadcrumb: {
                label: '添加版本'
            }
        })
        .state('hospitalSoftwareList.versionsList.versionsEdit', {
            url: '/:vid/versionsEdit',
            views: {
                "@": {
                    templateUrl: 'pages/editVersion.html',
                    controller: 'versionController'
                }
            },
            permission: PERMISSION.VERSION_UPDATE,
            ncyBreadcrumb: {
                label: '修改版本'
            }
        });

    $urlRouterProvider.otherwise('/login');

    //拦截器拦截器，用于拦截所有请求
    var interceptor = function ($q) {
        return {
            'response': function (resp) {
                return resp;
            },
            'responseError': function (rejection) {
                //  错误处理

                switch (rejection.status) {
                    case 401:
                        alert("登录状态过期，请重新登录。");
                        window.location.href = '#/login';
                        break;
                    case 500:
                        alert("服务器错误，请查看服务器日志或通知管理员。");
                        break;
                }
                return $q.reject(rejection);
            }
        };
    };
    // 将拦截器和 $http的request/response链整合在一起
    $httpProvider.interceptors.push(interceptor);
})
    .run(['$rootScope', '$location', '$cookieStore', '$http', 'PERMISSION', 'ngDialog', 'SecurityService',
        function ($rootScope, $location, $cookieStore, $http, PERMISSION, ngDialog, SecurityService) {

            // keep user logged in after page refresh
            $rootScope.PERMISSION = PERMISSION;
            $rootScope.isLogin = $cookieStore.get('isLogin') || false;
            $rootScope.globals = $cookieStore.get('globals') || {};
            $rootScope.security = SecurityService.security;

            if ($rootScope.globals.currentUser) {
                $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser;
            }

            $rootScope.$on('$locationChangeStart', function (event, next) {

                if (!$rootScope.globals.currentUser) {

                    if (next.templateUrl == "pages/login.html") {
                    } else {
                        $location.path("/login");
                    }
                }
            });

            $rootScope.changepsw = function () {

                ngDialog.openConfirm({
                    template: 'templates/resetPassword.html',
                    className: 'ngdialog-theme-default',
                    controller: ['$scope', function ($scope) {
                    }]
                }).then(function (user) {
                    user.userId = $rootScope.globals.currentUser.userId;
                    //user.oldPassword = "000";//用户界面不需要输入旧密码，此处默认值用来判断服务端初始化密码还是重新设置密码
                    SecurityService.security.passwordRest(user);
                }, function (reason) {
                });
            };

        }
    ]);
