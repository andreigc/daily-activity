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
  this.setUserId = function(userId){
	  $window.sessionStorage.userId = userId;
  }
  this.getUserId = function(){
	  return $window.sessionStorage.userId;
  }
  this.clearSession = function(){
	  $window.sessionStorage.sessionId = ""
	  $window.sessionStorage.userId = -1;
  }
  return this;
}]);