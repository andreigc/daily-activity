var dailyAppControllers = angular.module('dailyAppControllers', []);

dailyAppControllers.controller('TaskListController', [
		'$scope',
		'$http',
		'$location','$timeout',
		function($scope, $http, $location,$timeout) {
			
			$scope.completedTypes = [ {
				name : 'All',
				value : 0
			}, {
				name : 'Completed',
				value : 1
			}, {
				name : 'Unfinished',
				value : 2
			} ];
			$scope.completedFilter = $scope.completedTypes[2];
			
			
			$scope.loadData = function(){
				$http.get("rest/tasks/get/multiple?userId=1&completionType="+$scope.completedFilter.value,{headers: {'sessionId':1}}).success(
			
					function(data) {
						$scope.categories = data;
					});
				$scope.orderProp = '-priority';
			}
			$scope.loadData();
			
			
			
			
			$scope.showDeleteModal = function(task) {
				$('#deleteModal').modal('show');
				$scope.toDeleteTask = task;
			}
			
			$scope.deleteToDeleteTask = function(){
				$('#deleteModal').modal('hide');
				$http['delete']('rest/tasks/delete?taskId='+$scope.toDeleteTask.id,{headers: {'sessionId':1}}).success(function(data){
					$scope.loadData();
					angular.element(document.querySelector('.alert-deleted-success')).removeClass('hidden');
					
					$timeout(function(){angular.element(document.querySelector('.alert-deleted-success')).addClass('hidden');},2000);
					
				})
			}
			
		}]);


dailyAppControllers.controller('TaskNewController', [ '$scope', '$routeParams',
		'$http','$location', function($scope, $routeParams, $http,$location) {
			$scope.task = {}
			if ($routeParams.parentId) {
				$scope.task.parentId = $routeParams.parentId;
				
			} 
			
			$scope.task.categoryId=$routeParams.categoryId;
			
			$http.get("rest/categories/get",{headers: {'sessionId':1}}).success(function(data) {
				$scope.categories = data;
				for(i=0;i<$scope.categories.length;i++){
					if($scope.categories[i].id==$routeParams.categoryId){
						$scope.currentCategoryName = $scope.categories[i].name;
					}
				}
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
				$scope.task.priority = $scope.priority.value;
			}
			
			$scope.updateTaskType = function(){
				$scope.task.taskType = $scope.taskType.value;
			}
			
			$scope.submit = function() {
				
				$http.put("rest/tasks/create",$scope.task,{headers: {'sessionId':2}}).
				  success(function(data, status, headers, config) {
					  $location.path('/tasks');
				  }).error(function(data, status, headers, config) {
					    alert("Creating new task failed");
			      });
				
		      };

		} ]);