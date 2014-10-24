var dailyAppcontrollers = angular.module('dailyAppControllers', []);

dailyAppcontrollers.controller('TaskListController', [
		'$scope',
		'$http',
		'$cookies',
		'$location',
		function($scope, $http, $cookies,$location) {
			$cookies.sessionId = 1;
			$http.get("rest/tasks/get/multiple?userId=1").success(
					function(data) {
						$scope.categories = data;
					});

			$scope.orderProp = '-priority';

			$scope.go = function (path) {
				  $location.path(path);
				};
			
		} ]);

dailyAppcontrollers.controller('TaskNewController', [ '$scope', '$http',
		'$cookies', function($scope, $http, $cookies) {
			$cookies.sessionId = 1;
		} ]);