angular.module('fx-front').controller('orderRequestController', function ($rootScope, $scope, $http, $routeParams, $location, $localStorage) {
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

        if ($scope.newOrder.warranty == null && $rootScope.isUserIsUser()) {
            $scope.newOrder.warranty = $scope.orderPageWarrantyData[1];
        }

        if ($scope.newOrder.executor == null && $rootScope.isUserIsUser()) {
            $scope.newOrder.executor = $scope.orderPageServiceEngineerData[1];
        }

        if ($scope.newOrder.status == null && $rootScope.isUserIsUser())  {
            $scope.newOrder.status = $scope.orderPageStatusData[0];
        }

        if ($scope.newOrder.applicantName == undefined){
            alert("Имя клиента не указано");
            $scope.markSelectorBackgroundAsUndefined("clientName");
            return;
        }

        if ($scope.newOrder.applicantPhone == undefined){
            alert("Телефон клиента не указан");
            $scope.markSelectorBackgroundAsUndefined("clientPhone");
            return;
        }

        if ($scope.newOrder.applicantEmail == undefined){
            alert("Email клиента не указан");
            $scope.markSelectorBackgroundAsUndefined("clientEmail");
            return;
        }

        if ($scope.newOrder.model == undefined){
            alert("Модель продукта не выбрана");
            $scope.markSelectorBackgroundAsUndefined("productModel");
            return;
        }

        if ($scope.newOrder.warranty == undefined){
            alert("Тип гарантии не выбран");
            $scope.markSelectorBackgroundAsUndefined("warranty");
            return;
        }

        if ($scope.newOrder.executor == undefined){
            alert("Мастер не назначен");
            $scope.markSelectorBackgroundAsUndefined("serviceEngineerDesignated");
            return;
        }


        if ($scope.newOrder.status == undefined){
            alert("Статус не выбран");
            $scope.markSelectorBackgroundAsUndefined("repairStatus");
            return;
        }
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'POST',
            data: $scope.newOrder
        }).then(function (response) {
            alert('Ваш заказ успешно сформирован');
            document.getElementById("repairStatus").style.backgroundColor = "#FFFFFF";

            $scope.newOrder = null;
            $location.path('/');
        }, function failureCallback(response) {
            console.log($scope.newOrder);
            console.log("$scope.newOrder.status = " + $scope.newOrder.status)
        });
    };


    $scope.getModelsForOrderPage();
    $scope.getRepairStatusesForOrderPage();
    $scope.getWarrantyTypesForOrderPage();
    $scope.getUsersForOrderPage();


});