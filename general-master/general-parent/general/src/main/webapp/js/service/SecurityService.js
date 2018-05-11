ucmedAdmin.factory('SecurityService', function ($http, $cookieStore, $rootScope, $state,
                                                PERMISSION, GENERAL_URL) {

    var security = new Security($http, $cookieStore, $rootScope, $state, GENERAL_URL);
    var userModel = new UserModel($http, $state, GENERAL_URL);
    var roleModel = new RoleModel($http, $state, PERMISSION, GENERAL_URL);

    return {
        security: security,
        userModel: userModel,
        roleModel: roleModel
    };
});