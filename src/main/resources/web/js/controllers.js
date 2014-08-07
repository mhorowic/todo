var todoControllers = angular.module('todoControllers', []);

todoControllers.controller('PriorityController', [ '$scope', '$routeParams',
		function($scope, $routeParams) {
			$scope.param1 = $routeParams.priorityId;

		} ]);

todoControllers.controller('TaskController', function($scope) {

});