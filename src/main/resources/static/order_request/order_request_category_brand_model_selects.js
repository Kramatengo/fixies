angular.module('fx-front').controller('orderRequestController', function ($scope, $http, $routeParams) {
    const contextPath = 'http://localhost:8159/asc';

    let categoryStringJson;
    let categoryParsedJson;
    let categoryIdFromJson;
    let stringJsonForSelectedBrand;
    let parsedJsonForSelectedBrand;
    let brandIdFromJson;


    $scope.getCategoriesForOrderPage = function () {

        $http({
            url: contextPath + '/api/v1/categories/' + 'all_categories_for_order_page',
            method: 'GET',
        }).then(function (response) {

            console.log(response);
            $scope.orderPageCategoriesData = response.data;
        });
    }

    $scope.getBrandsForOrderPage = function () {

        $http({
            url: contextPath + '/api/v1/brands/' + 'all_brands_for_order_page',
            method: 'GET',
        }).then(function (response) {

            console.log(response);
            $scope.orderPageBrandsData = response.data;
        });
    }

    $scope.getModelsForOrderPage = function () {

        $http({
            url: contextPath + '/api/v1/models/' + 'all_models_for_order_page',
            method: 'GET',
        }).then(function (response) {

            console.log(response);
            $scope.orderPageModelsData = response.data;
        });
    }


    $scope.getBrandsBySelectedCategory = function () {
        categoryStringJson = JSON.stringify($scope.selectedCategory);
        categoryParsedJson = JSON.parse(categoryStringJson);
        categoryIdFromJson = categoryParsedJson.id;
        console.log("categoryStringJson = " + categoryStringJson);
        console.log(" categoryParsedJson = " +  categoryParsedJson);
        console.log("id from Json = " + categoryParsedJson.id);
        console.log("categoryIdFromJson = " + categoryIdFromJson);

        $http({
            url: contextPath + '/api/v1/models/' + 'brands_by_selected_category/' + categoryIdFromJson,
            method: 'GET',
        }).then(function (response) {

            console.log(response);
            $scope.orderPageBrandsData = response.data;

        });
    }

$scope.getModelsBySelectedBrand = function () {
    stringJsonForSelectedBrand = JSON.stringify($scope.selectedBrand);
    parsedJsonForSelectedBrand = JSON.parse(stringJsonForSelectedBrand);
    brandIdFromJson = parsedJsonForSelectedBrand.id;
    console.log("stringJsonForSelectedBrand = " + stringJsonForSelectedBrand);
    console.log("parsedJsonForSelectedBrand = " + parsedJsonForSelectedBrand);
    console.log("id from Json = " + parsedJsonForSelectedBrand.id);
    console.log("brandIdFromJson = " + brandIdFromJson);

    $http({
        url: contextPath + '/api/v1/models/' + 'models_by_selected_brand/' + brandIdFromJson + '/' + categoryIdFromJson,
        method: 'GET',
    }).then(function (response) {

        console.log(response);
        $scope.orderPageModelsData = response.data;

    });



}



    // $scope.getModelsForOrderPage = function () {
    //
    //     $http({
    //         url: contextPath + '/api/v1/models/' + 'all_models_for_order_page',
    //         method: 'GET',
    //     }).then(function (response) {
    //
    //         console.log(response);
    //         $scope.orderPageModelsData = response.data;
    //     });
    // }
    //


    // $scope.getCategoriesForOrderPage();
$scope.getModelsForOrderPage();
});