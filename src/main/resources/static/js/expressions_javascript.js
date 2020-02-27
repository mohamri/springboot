angular.module('myApp', [])
  .controller('myController', function($scope) {
    $scope.Math = window.Math;
    $scope.myArr = [1];
    $scope.removedArr = [];
    
    //A function that adds to an array
    $scope.addToArray = function() {
    	$scope.myArr.push(Math.floor(Math.random()*100 + 1));
    }
    
    //A function that pushes into removeArr
    $scope.removeFromArr = function() {
    	$scope.removedArr.push($scope.myArr.shift());
    }
  });