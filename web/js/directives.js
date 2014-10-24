var dailyAppDirectives = angular.module('dailyAppDirectives', []);

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
			})
			element.bind('mouseleave', function() {
				if (type == 2) {
					angular.element(
							document.querySelector('.new-subtask-' + id))
							.addClass('hidden');
				}
			})
		}
	}
});

dailyAppDirectives.directive('ngHoverCategory', function() {
	return {
		link : function(scope, element, attributes) {
			var id = attributes.id;
			element.bind('mouseenter', function() {
				angular.element(document.querySelector('.new-task-' + id))
						.removeClass('hidden');
			})
			element.bind('mouseleave', function() {
				angular.element(document.querySelector('.new-task-' + id))
						.addClass('hidden');
			})
		}
	}
});