angular.module('fx-front').controller('frontPageController', function ($scope, $http) {
    const contextPath = 'http://localhost:8159/asc';

    $scope.loadAllCategoriesList = function (pageIndex = 1) {

        $http({
            url: contextPath + '/api/v1/categories/' + 'all_categories',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.allCategoriesPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.allCategoriesPage.totalPages);
            console.log("$scope.allCategoriesPage = " + $scope.allCategoriesPage);
        }, function failureCallback(response) {
            console.log(response);
            alert(response.data.messages);
        });
    }


    //     $http.get(contextPath + 'api/v1/models/' + 'all_models')
    //         .then(function (response) {
    //             console.log(response);
    //             $scope.allModels = response.data;
    //             // console.log($scope.allModels);
    //         })
    // }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    console.log("run loadAllCategoriesList() ");
    $scope.loadAllCategoriesList();

});