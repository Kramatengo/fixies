


angular.module('fx-front').controller('orderRequestController', function ($scope, $http, $routeParams, $location, $localStorage) {
    const contextPath = 'http://localhost:8159/asc';

    $scope.getModelsForOrderPage = function () {

        $http({
            url: contextPath + '/api/v1/models/' + 'all_models_for_order_page',
            method: 'GET',
        }).then(function (response) {

            console.log(response);
            $scope.orderPageModelsData = response.data;
            console.log("response.data = " + response.data);

        });
    }

    $scope.getRepairStatusesForOrderPage = function () {

        $http({
            url: contextPath + '/api/v1/status/' + 'all_statuses_for_order_page',
            method: 'GET',
        }).then(function (response) {

            console.log(response);
            $scope.orderPageStatusData = response.data;
        });
    }

    $scope.getUsersForOrderPage = function () {

        $http({
            url: contextPath + '/api/v1/user/' + 'all_users_for_order_page',
            method: 'GET',
        }).then(function (response) {

            console.log(response);
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