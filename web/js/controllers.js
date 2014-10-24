var dailyAppcontrollers = angular.module('dailyAppControllers', []);

dailyAppcontrollers.controller('TaskListController', [
		'$scope',
		'$http',
		'$cookies',
		'$location',		function($scope, $http, $cookies, $location) {
			$cookies.sessionId = 1;
			$http.get("rest/tasks/get/multiple?userId=1").success(
					function(data) {
						$scope.categories = data;
					});

			$scope.orderProp = '-priority';

			$scope.go = function(path) {
				$location.path(path);
			};

		} ]);

dailyAppcontrollers.controller('TaskNewController', [ '$scope', '$routeParams', '$http', '$cookies', function($scope, $routeParams, $http, $cookies) {
			$scope.task = {}
			if ($routeParams.parentId) {
				$scope.task.parentId = $routeParams.parentId;
			}
			$cookies.sessionId = 1;
			$http.get("rest/categories/get").success(
					function(data) {
						$scope.categories = data;
						$scope.category=$scope.categories[0];
						$scope.task.categoryId = $scope.category.id;
					});
			
			$scope.update = function() {
				$scope.task.categoryId = $scope.category.id;
				}
			
		} ]);