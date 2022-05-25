angular.module('fx-front').controller('orderEditController', function ($scope, $http, $routeParams, $location, $localStorage) {
    const contextPath = 'http://localhost:8159/asc';

    $scope.prepareOrderForUpdate = function () {
        $http.get(contextPath + '/api/v1/orders/order_edit/' + $routeParams.orderId)
            .then(function successCallback (response) {
                $scope.updated_order = response.data;
            }, function failureCallback (response) {
                console.log(response);
                alert(response.data.messages);
                $location.path('/');
            });
    }

    $scope.updateOrder = function () {
        $http.put(contextPath + '/api/v1/orders/order_edit', $scope.updated_order)
            .then(function successCallback (response) {
                $scope.updated_order = null;
                alert('Заказ успешно обновлен');
                $location.path('/');
            }, function failureCallback (response) {
                alert(response.data.messages);
            });
    }

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








    $scope.getModelsForOrderPage();
    $scope.getRepairStatusesForOrderPage();
    $scope.getUsersForOrderPage();
    $scope.prepareOrderForUpdate();

});