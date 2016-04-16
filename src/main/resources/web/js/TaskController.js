angular.module('todoApp').controller('TaskController', ['$scope', '$routeParams', 'taskService', 'api',
    function ($scope, $routeParams, taskService, api) {
        $scope.alerts = [];
        $scope.tasks = [];
        $scope.inProgress = false;

        loadTasks = function () {
            if (taskService.getTasks().length == 0) {
                api.tasks.search().then(function (result) {
                    $scope.tasks = result;
                    taskService.setTasks(result);
                    if ($scope.tasks.length == 0) {
                        $scope.addAlert('No tasks found', 'info');
                    } else {
                        $scope.addAlert($scope.tasks.length + ' tasks found', 'info');
                    }
                }, function () {
                    $scope.addAlert('An error occurred while fetching tasks', 'danger');
                });
            } else {
                $scope.tasks = taskService.getTasks();
            }
        };

        refreshTasks = function () {
            taskService.clearTasks();
            loadTasks();
        };

        $scope.addAlert = function (message, type) {
            $scope.alerts.push({
                type: type,
                msg: message
            });
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };

        $scope.deleteTask = function (id) {
            $scope.inProgress = true;
            api.tasks.remove(id).then(function (result) {
                $scope.inProgress = false;
                $scope.addAlert('Task has been deleted', 'success');
                refreshTasks();
            }, function () {
                $scope.inProgress = false;
                $scope.addAlert('An error occurred while deleting task', 'danger');
            });
        };

        $scope.editTask = function (id) {
            console.log('edit task with id ' + id);
        };

        loadTasks();
    }
]);