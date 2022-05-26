angular.module('fx-front').controller('orderListController', function ($scope, $http) {
    const contextPath = 'http://localhost:8159/asc';

    $scope.loadAllOrderList = function () {

        $http({
            url: contextPath + '/api/v1/orders' + '/all_order_list',
            method: 'GET'
        }).then(function (response) {
            $scope.allOrderList = response.data;
            console.log($scope.allOrderList);
        }, function failureCallback(response) {
            console.log(response);
        });
    }

    $scope.loadAllOrderList();

});