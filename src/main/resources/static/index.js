(function () {
    angular
        .module('fx-front', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'front_page/front_page.html',
                controller: 'frontPageController',
                requireLogin: false
            })

            .when('/order_request', {
                templateUrl: 'order_request/order_request.html',
                controller: 'orderRequestController',
                requireLogin: true

            })

            .when('/order_list', {
                templateUrl: 'order_list/order_list.html',
                controller: 'orderListController'
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

            .when('/promo_page/:categoryId', {
                templateUrl: 'promo_page/promo_page.html',
                controller: 'promotionController'
            })

            .when('/about', {
                templateUrl: 'about/about.html',
                controller: 'aboutController'
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

    $rootScope.isUserIsUser = function () {
        if (userLogin == 'user') {
            //  todo: Доделать DTO для user и admin при контроле ввода выпадающих меню
            return true;
        } else {
            return false;

        }
    }


    $rootScope.isNotLogged = function () {
        if (userLogin != 'user') {
            return true;
        } else {
            return false;
        }
    }

    $scope.markSelectorBackgroundAsUndefined = function (selectorId) {
        document.getElementById(selectorId).style.backgroundColor = "#dc7c7c";
    }

    $scope.markSelectorBackgroundAsDefined = function (selectorId) {
        document.getElementById(selectorId).style.backgroundColor = "#c8f6b4";
    }

});