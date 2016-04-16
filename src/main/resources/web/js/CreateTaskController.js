angular.module('todoApp').controller('CreateTaskController', ['$scope', '$routeParams', 'taskService', 'api',
    function ($scope, $routeParams, taskService, api) {
        $scope.alerts = [];
        $scope.tasks = [];
        $scope.inProgress = false;

        loadPriorities = function () {
            api.priorities.search().then(function (result) {
                $scope.priorities = result;
            }, function () {
                $scope.addAlert('An error occurred while loading priorities', 'danger');
            });
        };

        $scope.createTask = function () {
            $scope.alerts = [];
            $scope.inProgress = true;
            var task = {
                name: $scope.taskName,
                description: $scope.taskDescription,
                dueDate: $scope.taskDueDate,
                priorityId: $scope.taskPriority.id
            };
            api.tasks.save(task).then(function (result) {
                $scope.inProgress = false;
                $scope.addAlert('Task has been saved', 'success');
            }, function () {
                $scope.inProgress = false;
                $scope.addAlert('An error occurred while saving task', 'danger');
            });
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

        loadPriorities();
    }
]);