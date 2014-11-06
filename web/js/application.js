var dailyAgendaApp = angular.module('dailyAgendaApp', ['dailyAppControllers', 'dailyAppFilters', 'dailyAppDirectives','dailyAppServices','ngRoute']);


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
	}).when('/logout',{
		templateUrl: 'partials/logout.html',
		controller: 'LogoutController'
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