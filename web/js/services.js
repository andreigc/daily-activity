var myModule = angular.module('dailyAppServices', []);
myModule.factory('Authentication',['$window',function($window) {
  this.isLogged = function(){
	  return !!$window.sessionStorage.sessionId;
  };
  this.getSessionId = function(){
	  return $window.sessionStorage.sessionId;
  }
  this.setSessionId = function(sId){
	  $window.sessionStorage.sessionId = sId;
  }
  return this;
}]);