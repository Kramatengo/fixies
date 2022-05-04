angular.module('fx-front').controller('createUserController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8159/asc/';

    $scope.createUser = function () {
        if ($scope.new_user == null) {
            alert('Форма не заполнена');
            return;
        }
        $http.post(contextPath + 'api/v1/reg_user', $scope.new_user)
            .then(function successCallback(response) {
                console.log("$scope.new_user.login = " + $scope.new_user.login);
                console.log("$scope.new_user.password = " + $scope.new_user.password);
                console.log("$scope.new_user.firstName = " + $scope.new_user.firstName);
                console.log("$scope.new_user.lastName = " + $scope.new_user.lastName);
                console.log("$scope.new_user.middleNames = " + $scope.new_user.middleNames);
                console.log("$scope.new_user.email = " + $scope.new_user.email);
                console.log("$scope.new_user.phone = " + $scope.new_user.phone);
                $scope.new_user = null;
                alert('Пользователь успешно создан');
                $location.path('/');
            }, function failureCallback(response) {
                alert(response.data.messages);
            });
    }
});