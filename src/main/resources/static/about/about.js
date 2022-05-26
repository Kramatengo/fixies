angular.module('fx-front').controller('aboutController', function ($scope, $http, $routeParams, $location, $localStorage) {
    const contextPath = 'http://localhost:8159/asc';
    let orderStatusParams;

    $scope.displayPromoPage = function () {
        $http({
            url: contextPath + '/api/v1/about',
            method: 'GET'
        }).then(function (response) {
            $scope.aboutPage = response.data;
            console.log($scope.aboutPage);
        }, function failureCallback(response) {
            console.log(response);
        });
    };

});
