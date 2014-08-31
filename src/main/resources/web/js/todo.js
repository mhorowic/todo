var todoApp = angular.module('todoApp', ['ngRoute', 'ui.bootstrap', 'todoControllers', 'restangular']);

todoApp.config(['$routeProvider', 'RestangularProvider',
    function($routeProvider, RestangularProvider) {
        $routeProvider.when('/', {
            templateUrl: 'tasks.html',
            controller: 'TaskController'
        }).when('/tasks', {
            redirectTo: "/"
        }).when('/tasks/:taskId', {
            templateUrl: 'task.html',
            controller: 'TaskController'
        }).when('/tasks/add', {
            templateUrl: 'task.html',
            controller: 'TaskController'
        }).when('/priorities', {
            templateUrl: 'priorities.html',
            controller: 'PriorityController'
        }).when('/priorities/:priorityId', {
            templateUrl: 'priority.html',
            controller: 'PriorityController'
        }).when('/priorities/add', {
            templateUrl: 'priority.html',
            controller: 'PriorityController'
        }).when('/error', {
            templateUrl: 'error.html'
        }).otherwise({
            redirectTo: '/error'
        });
    }
]);

todoApp.factory('api', ['Restangular',
    function(Restangular) {
        return {
            priorities: {
                search: function() {
                    return Restangular.all('Todo/priorities').getList();
                },
                save: function(entity) {
                    return Restangular.all('Todo/priorities').post(entity);
                },
                update: function(entity) {
                    return entity.put().$object;
                },
                read: function(id) {
                    return Restangular.one("Todo/priorities", id).get();
                },
                remove: function(id) {
                    return Restangular.one('Todo/priorities', id).delete();
                }
            },
            tasks: {
                search: function() {
                    return Restangular.all('Todo/tasks').getList();
                },
                save: function(entity) {
                    return Restangular.all('Todo/tasks').post(entity);
                },
                update: function(entity) {
                    return entity.put().$object;
                },
                read: function(id) {
                    return Restangular.one("Todo/tasks", id).get();
                },
                remove: function(id) {
                    return Restangular.one('Todo/tasks', id).delete();
                }
            }
        };
    }
]);

todoApp.service('todoService', ['api',
    function(api) {
        var priorities = undefined;
        var tasks = undefined;

        this.fetchPriorities = function() {
            if (!priorities) {
                api.priorities.search();
            }
        }
    }
]);

todoApp.service('taskService', ['api',
    function(api) {
        var tasks = [];

        this.getTasks = function() {
            return tasks;
        }

        this.setTasks = function(data) {
            tasks = data;
        }

        this.clearTasks = function() {
            tasks = [];
        }

    }
]);

todoApp.directive('tableAction', function(){
    return {
        restrict: 'E',
        scope: {
            deleteQuestion: '@',
            deleteAction: '&',
            editTemplate: '@',
            editAction: '&'
        },
        templateUrl: 'directive/table-action.html'
    };
});