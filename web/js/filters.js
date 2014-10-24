angular.module('dailyAppFilters', []).filter('priority', function() {
	return function(input) {
		switch (input) {
		case 1:
			return 'low'
		case 2:
			return 'important'
		case 3:
			return 'urgent'
		default:
			return 'urgent'
		}
		
	};
});