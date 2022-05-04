angular.module('fx-front').controller('orderStatusController', function ($scope, $http, $routeParams, $location, $localStorage) {
    const contextPath = 'http://localhost:8159/asc';


    $scope.displayOrderStatus = function () {
        $http({
            url: contextPath + '/api/v1/orders/status',
            method: 'GET'
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