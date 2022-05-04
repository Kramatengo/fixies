(function () {
    angular
        .module('fx-front', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'front_page/front_page.html',
                controller: 'frontPageController'
            })

            .when('/order_request', {
                templateUrl: 'order_request/order_request.html',
                controller: 'orderRequestController'
            })

            .when('/order_status/:orderId', {
                templateUrl: 'order_status/order_status.html',
                controller: 'orderStatusController'
            })

            .when('/order_edit/:orderId', {
                templateUrl: 'order_edit/order_edit.html',
                controller: 'orderEditController'
            })

            .when('/panel', {
                templateUrl: 'panel/panel.html',
                controller: 'panelController'
            })

            .when('/create_user', {
                templateUrl: 'create_user/create_user.html',
                controller: 'createUserController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.serviceUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.serviceUser.token;
        }
    }
})();


angular.module('fx-front').controller('indexController', function ($rootScope, $scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8159/asc';

    let userLogin = '';

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {


                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.serviceUser = {login: $scope.user.login, token: response.data.token};
                    userLogin = $localStorage.serviceUser.login;
                    console.log("$localStorage.serviceUser.login = " + $localStorage.serviceUser.login);
                    $scope.user.login = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
                console.log('$scope.user.login = ' + $scope.user.login);
                console.log('$scope.user.password = ' + $scope.user.password);
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        window.location.reload();
        if ($scope.user.login) {
            $scope.user.login = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.serviceUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.serviceUser) {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isUserAdmin = function () {
        if (userLogin != 'admin') {
            return true;
        } else {
            return false;
        }
    }

    $rootScope.isUserMaster = function () {
        if (userLogin != 'master') {
            return true;
        } else {
            return false;
        }
    }

    $rootScope.isUserSuperUser = function () {
        if (userLogin != 'superuser') {
            return true;
        } else {
            return false;
        }
    }


});