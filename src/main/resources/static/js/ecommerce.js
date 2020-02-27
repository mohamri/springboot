var eApp = angular.module('eApp', []);
eApp.controller('eController', 
		function($scope, $http, $window) {
	$scope.products = [];
	$scope.allProductStyle = {'visibility': 'visible'};
	$scope.singleProductStyle =  {'visibility': 'hidden'};
	
	<!-- Function to search for all products -->
	$scope.searchProduct = function(searchedProduct) {
		$scope.allProductStyle['visibility'] = 'visible';
		$scope.singleProductStyle['visibility'] = 	'hidden';
		if(searchedProduct == null || searchedProduct.length == 0) {
			$http({
                method : 'GET',
                url : 'http://localhost:8080/sales/all',
            }).then(function successCallback(response) {
                $scope.products = response.data;
            }, function errorCallback(response) {
                console.log(response.statusText);
            });
		}
	};
	
	<!-- Triggered after clicking a single product -->
	$scope.display = function(product) {
		$scope.allProductStyle['visibility'] = 'hidden';
		$scope.singleProductStyle['visibility'] = 	'visible';
		$scope.selectedProduct = product;
	}
	
	<!-- Triggered when a mouse goes over a small image -->
	$scope.mouseOverSmallImage = function(sImageName) {
		$scope.selectedProduct.imageName = sImageName;
	}
	
	
});