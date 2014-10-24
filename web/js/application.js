var dailyAgendaApp = angular.module('dailyAgendaApp', [ 'ngCookies', 'ngRoute',
		'dailyAppControllers', 'dailyAppFilters', 'dailyAppDirectives' ]);

dailyAgendaApp.config(function($httpProvider) {
	$httpProvider.defaults.withCredentials = true;
});

dailyAgendaApp.config([ '$routeProvider', function($routeProvider) {

	$routeProvider.when('/tasks', {
		templateUrl : 'partials/task-list.html',
		controller : 'TaskListController'
	}).when('/tasks/create/parent/:parentId', {
		templateUrl : 'partials/task-create.html',
		controller : 'TaskNewController'
	}).when('/tasks/create/', {
		templateUrl : 'partials/task-create.html',
		controller : 'TaskNewController'
	}).otherwise({
		redirectTo : '/tasks'
	});
} ]);