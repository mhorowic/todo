angular.module('todoApp').controller('PriorityController', ['$scope', '$routeParams', 'api',
    function ($scope, $routeParams, api) {
        $scope.alerts = [];

        $scope.priorityId = $routeParams.priorityId;

        if ($scope.priorityId == null) {
            $scope.priorityId = 0;
        }

        api.priorities.search().then(function (result) {
            $scope.priorities = result;
        }, function () {
            // handle error
        });

        api.priorities.read($scope.priorityId).then(function (result) {
            $scope.priority = result;
        }, function () {
            // handle error
            $scope.addAlert('Unable to load priority with id ' + $scope.priorityId);
        });


        $scope.addAlert = function (message) {
            $scope.alerts.push({
                type: 'danger',
                msg: message
            });
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };

    }
]);