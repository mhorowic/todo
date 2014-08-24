var todoControllers = angular.module('todoControllers', ['restangular', 'ui.bootstrap']);

todoControllers.controller('PriorityController', ['$scope', '$routeParams', 'Restangular',
    function($scope, $routeParams, Restangular) {
        $scope.alerts = [];

        $scope.priorityId = $routeParams.priorityId;

        if ($scope.priorityId == null) {
            $scope.priorityId = 0;
        }

        Restangular.all('Todo/priorities').getList().then(function(result) {
            $scope.priorities = result;
        }, function() {
            // handle error
        });

        Restangular.one("Todo/priorities", $scope.priorityId).get().then(function(result) {
            $scope.priority = result;
        }, function() {
            // handle error
            $scope.addAlert('Unable to load priority with id ' + $scope.priorityId);
        });


        $scope.addAlert = function(message) {
            $scope.alerts.push({
                type: 'danger',
                msg: message
            });
        };

        $scope.closeAlert = function(index) {
            $scope.alerts.splice(index, 1);
        };

    }
]);

todoControllers.controller('TaskController', ['$scope', '$routeParams', 'taskService', 'api',
    function($scope, $routeParams, taskService, api) {
        $scope.alerts = [];
        $scope.tasks = [];

        $scope.loadTasks = function() {
            if (taskService.getTasks().length == 0) {
                api.tasks.search().then(function(result) {
                    $scope.tasks = result;
                    taskService.setTasks(result);
                    if ($scope.tasks.length == 0) {
                        $scope.addAlert('No tasks found', 'info');
                    } else {
                        $scope.addAlert($scope.tasks.length + ' tasks found', 'info');
                    }
                }, function() {
                    $scope.addAlert('An error occurred while fetching tasks', 'danger');
                });
            } else {
                $scope.tasks = taskService.getTasks();
            }
        };

        $scope.addAlert = function(message, type) {
            $scope.alerts.push({
                type: type,
                msg: message
            });
        };

        $scope.closeAlert = function(index) {
            $scope.alerts.splice(index, 1);
        };

        $scope.loadTasks();
    }
]);