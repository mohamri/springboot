angular.module('myApp', []).
  controller('SimpleTemplate', function($scope) {
    $scope.valueA = 5;
    $scope.valueB = 7;

    $scope.addValues = function(v1, v2, e) {
      $scope.valueC = v1 + v2;
    };
  });