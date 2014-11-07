var dailyAppControllers = angular.module('dailyAppControllers', []);


dailyAppControllers.controller('RegisterController',['$scope','$http','$location','Authentication',function($scope,$http,$location,Authentication){
	
	$scope.newUser={};
	
}])

dailyAppControllers.controller('LoginController',['$scope','$http','$location','Authentication',function($scope,$http,$location,Authentication){
	
	
	$scope.credentials={};
	
	$scope.updateEncodedPassword = function(){
		var hash = CryptoJS.MD5($scope.password);
		$scope.credentials.encodedPassword =hash.toString(CryptoJS.enc.Hex)
	};
	
	
	$scope.submitCredentials = function(){
		$http.post('rest/auth/login',$scope.credentials).success(function(data){
			Authentication.setSessionId(data.sessionId);
			Authentication.setUserId(data.user.id);
			if(Authentication.isLogged()){
				$location.path("tasks");
			}
		});
	};
	
}])

dailyAppControllers.controller('TaskListController', [
		'$scope',
		'$http',
		'$location','$timeout','Authentication',
		function($scope, $http, $location,$timeout,Authentication) {
			
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
			
			$scope.selectedDate = new Date();
			
			$scope.loadData = function(){
				var baseUrl = "rest/protected/tasks/get/multiple";
				var paramsUrl = "?userId="+Authentication.getUserId();
				paramsUrl+="&completionType="+$scope.completedFilter.value;
				paramsUrl+="&startDateMillis="+$scope.selectedDate.getTime();
				$http.get(baseUrl+paramsUrl,{headers: {'sessionId':Authentication.getSessionId()}}).success(
					function(data) {
						$scope.categories = data;
					});
				$scope.orderProp = '-priority';
			}
			$scope.loadData();
			
			$scope.openEditTask = function(taskId){
				var path = 'tasks/edit/';
				path+=taskId;
				$location.path(path);
			}
			
			$scope.openCreateNewTask = function(categoryId,taskId){
				var path = '/tasks/create/category/';
				path+=categoryId;
				if(taskId){
					path+='/parent/';
					path+=taskId;
				}
				$location.path(path);
			}
			
			$scope.showDeleteModal = function(task) {
				$('#deleteModal').modal('show');
				$scope.toDeleteTask = task;
			}
			
			$scope.deleteToDeleteTask = function(){
				$('#deleteModal').modal('hide');
				$http['delete']('rest/protected/tasks/delete?taskId='+$scope.toDeleteTask.id,{headers: {'sessionId':Authentication.getSessionId()}}).success(function(data){
					$scope.loadData();
					angular.element(document.querySelector('.alert-deleted-success')).removeClass('hidden');
					
					$timeout(function(){angular.element(document.querySelector('.alert-deleted-success')).addClass('hidden');},2000);
					
				})
			}
			
		}]);


dailyAppControllers.controller('TaskNewController', [ '$scope', '$routeParams',
		'$http','$location','Authentication', function($scope, $routeParams, $http,$location,Authentication) {
			$scope.task = {}
			if ($routeParams.parentId) {
				$scope.task.parentId = $routeParams.parentId;
				
			} 
			
			$scope.task.categoryId=$routeParams.categoryId;
			$scope.task.startDate = new Date();
			$scope.task.completionGrade=1;
			
			$http.get("rest/protected/categories/get",{headers: {'sessionId':Authentication.getSessionId()}}).success(function(data) {
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
				
				$http.put("rest/protected/tasks/create",$scope.task,{headers: {'sessionId':Authentication.getSessionId()}}).
				  success(function(data, status, headers, config) {
					  $location.path('/tasks');
				  }).error(function(data, status, headers, config) {
					    alert("Creating new task failed");
			      });
				
		      };

		} ]);


dailyAppControllers.controller('TaskEditController',['$scope','$routeParams','$http','$location','Authentication',function($scope,$routeParams,$http,$location,Authentication){
	
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
	
	var baseUrl = "rest/protected/tasks/get/single";
	var paramsUrl = "?taskId="+$routeParams.taskId;
	$http.get(baseUrl+paramsUrl,{headers: {'sessionId':Authentication.getSessionId()}}).success(
		function(data) {
			$scope.task = data;
			
			$scope.percentCompleted = $scope.task.completionGrade-1;
			$scope.task.completionGrade = $scope.percentCompleted + 1;
			$scope.priority = $scope.priorities[$scope.task.priority-1];
		});

	$scope.update = function() {
		$scope.task.completionGrade = $scope.percentCompleted + 1;
		$scope.task.priority = $scope.priority.value;
	}
	
	$scope.submit = function(){
		$http.post('rest/protected/tasks/update',$scope.task,{headers: {'sessionId':Authentication.getSessionId()}}).success(function(data){
			$location.path("tasks");
		})
	}
	
}])