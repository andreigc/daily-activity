var dailyAgendaApp =  angular.module('dailyAgendaApp',['ngCookies']);

dailyAgendaApp.config(function ($httpProvider) {
    $httpProvider.defaults.withCredentials = true;
});

dailyAgendaApp.controller('TaskListController',['$scope','$http','$cookies',function($scope,$http,$cookies){
	$cookies.sessionId=1;
	$http.get("rest/tasks/get/multiple?userId=1").success(function(data){ $scope.categories = data;});
}]);