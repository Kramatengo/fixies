


angular.module('fx-front').controller('orderRequestController', function ($scope, $http, $routeParams, $location, $localStorage) {
    const contextPath = 'http://localhost:8159/asc';

    $scope.getModelsForOrderPage = function () {

        $http({
            url: contextPath + '/api/v1/models/',
            method: 'GET',
        }).then(function (response) {
            $scope.orderPageModelsData = response.data;
            console.log($scope.orderPageModelsData);
        });
    }

    $scope.getRepairStatusesForOrderPage = function () {

        $http({
            url: contextPath + '/api/v1/statuses/',
            method: 'GET',
        }).then(function (response) {
            $scope.orderPageStatusData = response.data;
        });
    }

    $scope.getUsersForOrderPage = function () {

        $http({
            url: contextPath + '/api/v1/users/',
            method: 'GET',
        }).then(function (response) {
            $scope.orderPageServiceEngineerData = response.data;
        });
    }

    $scope.createOrder = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'POST',
            data: $scope.newOrder
        }).then(function (response) {
            alert('Ваш заказ успешно сформирован');
            console.log($scope.newOrder);
            $scope.newOrder = null;
            $location.path('/');
        }, function failureCallback (response) {
            console.log($scope.newOrder);
            $scope.newOrder = null;
            alert(response.data.messages);
        });
    };


    $scope.getModelsForOrderPage();
    $scope.getRepairStatusesForOrderPage();
    $scope.getUsersForOrderPage();


});