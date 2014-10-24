var dailyAgendaApp =  angular.module('dailyAgendaApp',['ngCookies']);

dailyAgendaApp.config(function ($httpProvider) {
    $httpProvider.defaults.withCredentials = true;
});

dailyAgendaApp.directive('ngHoverTask', function() {
	  return {
	    link: function(scope, element, attributes) {
	       var id  = attributes.id;
	       var type = attributes.type;
	       element.bind('mouseenter', function() {
	    	  if(type==2){
	    		  angular.element(document.querySelector('.new-subtask-'+id)).removeClass('hidden');
	    	  }
	       })
	        element.bind('mouseleave', function() {
	          if(type==2){
	        	  angular.element(document.querySelector('.new-subtask-'+id)).addClass('hidden');
	         }
	      })
	    }
	  }
});


dailyAgendaApp.directive('ngHoverCategory', function() {
	  return {
	    link: function(scope, element, attributes) {
	       var id  = attributes.id;
	       element.bind('mouseenter', function() {
	    	   angular.element(document.querySelector('.new-task-'+id)).removeClass('hidden');
	       })
	        element.bind('mouseleave', function() {
	           angular.element(document.querySelector('.new-task-'+id)).addClass('hidden');
	      })
	    }
	  }
});

dailyAgendaApp.controller('TaskListController',['$scope','$http','$cookies',function($scope,$http,$cookies){
	$cookies.sessionId=1;
	$http.get("rest/tasks/get/multiple?userId=1").success(function(data){ $scope.categories = data;});
	
	$scope.orderProp = '-priority';
	
	
}]);