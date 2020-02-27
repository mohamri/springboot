<!doctype html>
<html ng-app="myApp">
  <head>
    <title>AngularJS Configuration and Run Blocks</title>
  </head>
  <body>
    <div ng-controller="controllerA">
      <hr>
      <h2>Config Time:</h2>
      {{configTime}}
      <hr>
      <h2>Run Time:</h2>
      {{runTime}}
    </div><hr>
    <script src="http://code.angularjs.org/1.3.8/angular.min.js"></script>
    <script src="js/config_run_blocks.js"></script>
  </body>
</html>