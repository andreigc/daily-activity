var myModule = angular.module('dailyAppServices', []);
myModule.factory('Authentication', function() {
  this.isLogged = function(){
	  return !!this.sessionId;
  };
  this.getSessionId = function(){
	  return this.sessionId;
  }
  this.setSessionId = function(sId){
	  this.sessionId = sId;
  }
  return this;
});