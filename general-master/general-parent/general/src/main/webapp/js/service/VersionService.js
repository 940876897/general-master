ucmedAdmin.factory("VersionService", function ($http, $state, uiUploader) {

    var versionControl = new VersionControl($http, $state, uiUploader);

    return {
        versionControl: versionControl
    };
});
