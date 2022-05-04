angular.module('fx-front').controller('frontPageController', function ($scope, $http) {
    const contextPath = 'http://localhost:8159/asc';

    $scope.loadAllCategoriesList = function () {

        $http({
            url: contextPath + '/api/v1/categories/',
            method: 'GET'
        }).then(function (response) {
            $scope.allCategoriesPage = response.data;
            console.log($scope.allCategoriesPage);
        }, function failureCallback(response) {
            console.log(response);
        });
    }

    $scope.loadAllCategoriesList();

});