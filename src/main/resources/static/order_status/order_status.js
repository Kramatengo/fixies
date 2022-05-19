angular.module('fx-front').controller('orderStatusController', function ($scope, $http, $routeParams, $location, $localStorage) {
    const contextPath = 'http://localhost:8159/asc';
 let orderStatusParams;

    $scope.displayOrderStatus = function () {
        $http({
            url: contextPath + '/api/v1/orders/order_status/' + $routeParams.orderId,
            method: 'GET'
        }).then(function (response) {
            $scope.orderStatusPage = response.data;
            orderStatusParams = $scope.orderStatusPage;
            console.log($scope.orderStatusPage);
        }, function failureCallback(response) {
            console.log(response);
        });
    };

    $scope.isWarrantyRepair = function () {
        if (orderStatusParams.warranty.id == 1) {
            return true;
        }
        return false;
    }

    $scope.displayOrderStatus();

});