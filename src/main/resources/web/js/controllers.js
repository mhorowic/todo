var todoControllers = angular.module('todoControllers', [ 'restangular', 'ui.bootstrap' ]);

todoControllers.controller('PriorityController', [ '$scope', '$routeParams',
		'Restangular', function($scope, $routeParams, Restangular) {
		    $scope.alerts = [];

			$scope.priorityId = $routeParams.priorityId;

			if($scope.priorityId == null){
			    $scope.priorityId = 0;
			}

			Restangular.all('Todo/priorities').getList().then(function(result) {
				$scope.priorities = result;
			}, function() {
				// handle error
			});

            Restangular.one("Todo/priorities", $scope.priorityId).get().then(function(result) {
                $scope.priority = result;
            }, function(){
                // handle error
                $scope.addAlert('Unable to load priority with id ' + $scope.priorityId);
            });


            $scope.addAlert = function(message) {
                $scope.alerts.push({type: 'danger', msg: message});
              };

              $scope.closeAlert = function(index) {
                $scope.alerts.splice(index, 1);
              };

		} ]);

todoControllers.controller('TaskController', ['$scope', function($scope) {

}]);