var dailyAgendaApp = angular.module('dailyAgendaApp', ['dailyAppControllers', 'dailyAppFilters', 'dailyAppDirectives','ngRoute']);

dailyAgendaApp.config([ '$routeProvider', function($routeProvider) {

	$routeProvider.when('/tasks', {
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
	}).otherwise({
		redirectTo : '/tasks'
	});
} ]);