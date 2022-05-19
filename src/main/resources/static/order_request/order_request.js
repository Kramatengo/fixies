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

    $scope.getWarrantyTypesForOrderPage = function () {

        $http({
            url: contextPath + '/api/v1/warranties/',
            method: 'GET',
        }).then(function (response) {
            $scope.orderPageWarrantyData = response.data;
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

        if ($scope.newOrder == null) {
            alert('Форма не заполнена');
            return;
        }


        $http({
            url: contextPath + '/api/v1/orders',
            method: 'POST',
            data: $scope.newOrder
        }).then(function (response) {
            alert('Ваш заказ успешно сформирован');
            console.log($scope.newOrder);
            document.getElementById("repairStatus").style.backgroundColor = "#FFFFFF";

            $scope.newOrder = null;
            $location.path('/');
        }, function failureCallback(response) {
            console.log($scope.newOrder);
            console.log("$scope.newOrder.status = " + $scope.newOrder.status)

            if ($scope.newOrder.model == undefined){
                alert("Модель продукта не выбрана");
                $scope.markSelectorBackgroundAsUndefined("productModel");
            }

            if ($scope.newOrder.warranty == undefined){
                alert("Тип гарантии не выбран");
                $scope.markSelectorBackgroundAsUndefined("warranty");
            }

            if ($scope.newOrder.executor == undefined){
                alert("Мастер не назначен");
                $scope.markSelectorBackgroundAsUndefined("serviceEngineerDesignated");
            }


            if ($scope.newOrder.status == undefined){
                alert("Статус не выбран");
                $scope.markSelectorBackgroundAsUndefined("repairStatus");
            }

           // $scope.newOrder = null;
            // alert(response.data.messages);
        });
    };


    $scope.getModelsForOrderPage();
    $scope.getRepairStatusesForOrderPage();
    $scope.getWarrantyTypesForOrderPage();
    $scope.getUsersForOrderPage();


});