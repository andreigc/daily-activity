var dailyAgendaApp = angular.module('dailyAgendaApp', ['dailyAppControllers', 'dailyAppFilters', 'dailyAppDirectives','dailyAppServices','ngRoute']);


dailyAgendaApp.controller("MainController",['$scope','$http','$location','Authentication',function($scope,$http,$location,Authentication){
	
	
	$scope.currentUser = function(){
		return Authentication.getUsername();
	}
	
	$scope.isLoggedIn = function(){
		var userId = Authentication.getUserId();
		return Authentication.isLogged();
	}
	
	$scope.isMainPage = function(){
		return $location.path() == "/";
	}
	
	$scope.logout = function(event){
		event.preventDefault();	
		$http.post('rest/auth/logout',{},{headers: {'sessionId':Authentication.getSessionId()}}).success(function(data){
			Authentication.clearSession();
			$location.path("/login");
		})
	}
	
}])

dailyAgendaApp.run(function ($rootScope, $location,Authentication) {

$rootScope.$on('$routeChangeStart', function (event, next, current) {
	  if(next){
		  if (next.$$route.originalPath !== '/login' && next.$$route.originalPath !== '/logout' && next.$$route.originalPath !== '/register' && !Authentication.isLogged()) {
			  $location.path('/login');
		  }
		  if ((next.$$route.originalPath === '/login'  ||  next.$$route.originalPath === '/register') && Authentication.isLogged()){
			  $location.path('/tasks');
		  }
	  }
  });
});

dailyAgendaApp.config([ '$routeProvider', function($routeProvider) {
	
	
	$routeProvider.when('/login',{
		templateUrl: 'partials/login.html',
		controller: 'LoginController'
	}).when('/register',{
		templateUrl: 'partials/register.html',
		controller: 'RegisterController'
	}).when('/tasks', {
		templateUrl : 'partials/task-list.html',
		controller : 'TaskListController'
	}).when('/tasks/create/category/:categoryId/parent/:parentId', {
		templateUrl : 'partials/task-create.html',
		controller : 'TaskNewController'
	}).when('/tasks/create/category/:categoryId/', {
		templateUrl : 'partials/task-create.html',
		controller : 'TaskNewController'
	}).when('/tasks/edit/:taskId',{
		templateUrl: 'partials/task-edit.html',
		controller: 'TaskEditController'
	});

} ]);