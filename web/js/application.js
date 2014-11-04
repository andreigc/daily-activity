var dailyAgendaApp = angular.module('dailyAgendaApp', ['dailyAppControllers', 'dailyAppFilters', 'dailyAppDirectives','ngRoute']);


dailyAgendaApp.run(function ($rootScope, $location) {

	  $rootScope.$on('$routeChangeStart', function (event, next, current) {
	    if (next !== '/login') {
	      $location.path('/login');
	    }
	  });
	  
	});

dailyAgendaApp.config([ '$routeProvider', function($routeProvider) {

	$routeProvider.when('/login',{
		templateUrl: 'partials/login.html',
		controller: 'LoginController'
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