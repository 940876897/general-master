ucmedAdmin.constant('PERMISSION', {
    PROJECT_VIEW: 'P:1',
    PROJECT_CREATE: 'P:2',
    PROJECT_UPDATE: 'P:4',
    PROJECT_SEARCH: 'P:16',
    PROJECT_SOFTWARE: 'P:32',
    SOFTWARE_VIEW: 'S:1',
    SOFTWARE_CREATE: 'S:2',
    SOFTWARE_UPDATE: 'S:4',
    SOFTWARE_SEARCH: 'S:16',
    SOFTWARE_VERSION: 'S:32',
    VERSION_CREATE: 'V:2',
    VERSION_UPDATE: 'V:4',
    VERSION_UNSHELVE: 'V:32',
    USER_VIEW: 'U:1',
    USER_CREATE: 'U:2',
    USER_UPDATE: 'U:4',
    USER_DELETE: 'U:8',
    USER_SEARCH: 'U:16',
    USER_RESETPSW: 'U:32',
    ROLE_VIEW: 'R:1',
    ROLE_CREATE: 'R:2',
    ROLE_UPDATE: 'R:4',
    ROLE_DELETE: 'R:8',
    ROLE_SEARCH: 'R:16',
    ROLE_PERMISSION: 'R:32',
    ROLE_USER: 'R:64'
});


ucmedAdmin.constant('GENERAL_URL', {
    SHIROROLE_ADD:'/auth/shiroRole/addRole.json',
    SHIROROLE_UPDATE:'/auth/shiroRole/updateRole.json',
    SHIROROLE_DELETE:'/auth/shiroRole/deleteRole.json',
    SHIROROLE_SEARCH:'/auth/shiroRole/roleSearch.json',
    SHIROROLE_ROLELIST_PAGE:'/auth/shiroRole/roleList.json',
    SHIROROLE_LIST:'/auth/shiroRole/list.json',
    SHIROROLE_ROLELIST_USERID:'/auth/shiroRole/roleListToUserId.json',
    SHIROROLE_UNSELECTEDUSER:'/auth/shiroRole/unselectedUser.json',
    SHIROROLE_SELECTEDUSER:'/auth/shiroRole/selectedUser.json',
    SHIROROLE_USERMANAGE:'/auth/shiroRole/userManage.json',
    SHIROROLE_SELECTEDPERMISSION:'/auth/shiroRole/selectedPermission.json',
    SHIROROLE_UPDATEPERMISSION:'/auth/shiroRole/roleUpdatePermission.json',
    SHIROROLE_ROLENAME_EXISTS:'/auth/shiroRole/roleNameIfExist.json',
    SHIROUSER_ADD:'/auth/shiroUser/addUser.json',
    SHIROUSER_EDIT:'/auth/shiroUser/editUser.json',
    SHIROUSER_DELETE:'/auth/shiroUser/deleteUser.json',
    SHIROUSER_SEARCH:'/auth/shiroUser/userSearch.json',
    SHIROUSER_LIST:'/auth/shiroUser/userList.json',
    SHIROUSER_PSWINIT:'/auth/shiroUser/passwordInit.json',
    SHIROUSER_PSWRESET:'/auth/shiroUser/passwordReset.json',
    SHIROUSER_USERNAME_EXISTS:'/auth/shiroUser/userNameIfExist.json',
    SHIROUSER_PSWCERT:'/auth/shiroUser/passwordCertificate.json'
});
