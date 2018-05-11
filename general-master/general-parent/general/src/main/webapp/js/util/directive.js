/**
 * Angualr directive(自定义的标签库，类似jsp的taglib)
 * Created by chris on 2016/1/15.
 */

var compareTo = function () {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=compareaTo"
        },
        link: function (scope, element, attributes, ngModel) {

            ngModel.$validators.compareTo = function (modelValue) {
                return modelValue == scope.otherModelValue;
            };

            scope.$watch("otherModelValue", function () {
                ngModel.$validate();
            });
        }
    };
};

ucmedAdmin.directive("compareaTo", compareTo);