angular.module('fx-front').controller('promotionController', function ($rootScope, $scope, $http, $routeParams, $location, $localStorage) {
    const contextPath = 'http://localhost:8159/asc';
    let orderStatusParams;

    $scope.displayPromoPage = function () {
        $http({
            url: contextPath + '/api/v1/promotion/' + 'promo_page/' + $routeParams.categoryId,
            method: 'GET'
        }).then(function (response) {
            $scope.promotionPage = response.data;
            // orderStatusParams = $scope.orderStatusPage;
            console.log($scope.promotionPage);
        }, function failureCallback(response) {
            console.log(response);
        });
    };

    $scope.getModelsForPromoPage = function () {


        $http({
            url: contextPath + '/api/v1/models/',
            method: 'GET',
        }).then(function (response) {
            $scope.promoPageModelsData = response.data;
            console.log($scope.promoPageModelsData);
        });
    }

    $scope.getModelsForPromoPage();
    $scope.displayPromoPage();

});