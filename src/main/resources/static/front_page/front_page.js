angular.module('fx-front').controller('frontPageController', function ($scope, $http) {
    const contextPath = 'http://localhost:8159/asc';

    <!-- Slide show on header page - start -->
    let slideIndex = 1;

    $scope.plusDivs =  function(n) {
        showDivs(slideIndex += n);
    }

    $scope.showDivs = function (n) {
        var i;
        var x = document.getElementsByClassName("csFrontPageSlideShowSlides");
        if (n > x.length) {slideIndex = 1}
        if (n < 1) {slideIndex = x.length}
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        x[slideIndex-1].style.display = "block";
    }


    // $scope.showDivs(slideIndex);
    // $scope.showDivs(1);



    <!-- Slide show on header page - end -->

    <!-- Slide show buttons on header page - start -->

    //   let slideIndex = 1;

    $scope.plusSlides = function (n) {
        $scope.showSlides(slideIndex += n);
    }

    $scope.currentSlide = function (n) {
        $scope.showSlides(slideIndex = n);
    }

    $scope.showSlides =  function(n) {
        var i;
        var slides = document.getElementsByClassName("csFrontPageSlideShowSlides");
        var dots = document.getElementsByClassName("dot");
        if (n > slides.length) {slideIndex = 1}
        if (n < 1) {slideIndex = slides.length}
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides[slideIndex-1].style.display = "block";
        dots[slideIndex-1].className += " active";
    }

    // $scope.showSlides(slideIndex);

    <!-- Slide show buttons on header page - end -->


});