var dailyAppDirectives = angular.module('dailyAppDirectives', []);

dailyAppDirectives.directive('ngHoverSubTask',function(){
	return{
		link: function(scope,element,attributes){
			var id = attributes.id;
			element.bind('mouseenter', function() {
				angular.element(document.querySelector('.delete-icon-subtask-'+id)).removeClass('hidden');
			})
			element.bind('mouseleave', function() {
				angular.element(document.querySelector('.delete-icon-subtask-'+id)).addClass('hidden');
			})
		}
	}
})

dailyAppDirectives.directive('ngHoverTask', function() {
	return {
		link : function(scope, element, attributes) {
			var id = attributes.id;
			var type = attributes.type;
			element.bind('mouseenter', function() {
				if (type == 2) {
					angular.element(
							document.querySelector('.new-subtask-' + id))
							.removeClass('hidden');
				}
				angular.element(document.querySelector('.delete-icon-task-'+id)).removeClass('hidden');
			})
			element.bind('mouseleave', function() {
				if (type == 2) {
					angular.element(
							document.querySelector('.new-subtask-' + id))
							.addClass('hidden');
				}
				angular.element(document.querySelector('.delete-icon-task-'+id)).addClass('hidden');
			})
		}
	}
});


//from: http://stackoverflow.com/questions/14012239/password-check-directive-in-angularjs
dailyAppDirectives.directive('equals', function() {
	  return {
	    restrict: 'A', // only activate on element attribute
	    require: '?ngModel', // get a hold of NgModelController
	    link: function(scope, elem, attrs, ngModel) {
	      if(!ngModel) return; // do nothing if no ng-model

	      // watch own value and re-validate on change
	      scope.$watch(attrs.ngModel, function() {
	        validate();
	      });

	      // observe the other value and re-validate on change
	      attrs.$observe('equals', function (val) {
	        validate();
	      });

	      var validate = function() {
	        // values
	        var val1 = ngModel.$viewValue;
	        var val2 = attrs.equals;

	        // set validity
	        ngModel.$setValidity('equals', ! val1 || ! val2 || val1 === val2);
	      };
	    }
	  }
	});

