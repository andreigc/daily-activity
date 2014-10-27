var dailyAppcontrollers = angular.module('dailyAppControllers', []);

dailyAppcontrollers.controller('TaskListController', [
		'$scope',
		'$http',
		'$cookies',
		'$location',
		function($scope, $http, $cookies, $location) {
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

dailyAppcontrollers.controller('TaskNewController', [ '$scope', '$routeParams',
		'$http', '$cookies', function($scope, $routeParams, $http, $cookies) {
			$scope.task = {}
			if ($routeParams.parentId) {
				$scope.task.parentId = $routeParams.parentId;
				
			} 
			$cookies.sessionId = 1;
			$http.get("rest/categories/get").success(function(data) {
				$scope.categories = data;
				$scope.category = $scope.categories[0];
				$scope.task.categoryId = $scope.category.id;
			});

			$scope.priorities = [ {
				name : 'low',
				value : 1
			}, {
				name : 'important',
				value : 2
			}, {
				name : 'urgent',
				value : 3
			} ];
			$scope.priority = $scope.priorities[0];
			$scope.task.priority = $scope.priority.value;
			
			$scope.taskTypes = [{
				name : 'Standalone',
				value: 1
			},{
				name: 'Container',
				value: 2
			}]
			if (!$routeParams.parentId) {
				$scope.taskType = $scope.taskTypes[0];
				$scope.task.taskType = $scope.taskType.value;
			}else{
				$scope.task.taskType = 3;
			}

			$scope.update = function() {
				$scope.task.categoryId = $scope.category.id;
				$scope.task.priority = $scope.priority.value;
			}
			
			$scope.updateTaskType = function(){
				$scope.task.taskType = $scope.taskType.value;
			}

		} ]);