var todoApp = angular.module('todoApp', [ 'ngRoute', 'todoControllers' ]);

todoApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'tasks.html',
		controller : 'PriorityController'
	}).when('/tasks', {
		redirectTo : "/"
	}).when('/tasks/:taskId', {
		templateUrl : 'task.html',
		controller : 'TaskController'
	}).when('/tasks/add', {
		templateUrl : 'task.html',
		controller : 'TaskController'
	}).when('/priorities', {
		templateUrl : 'priorities.html',
		controller : 'PriorityController'
	}).when('/priorities/:priorityId', {
		templateUrl : 'priority.html',
		controller : 'PriorityController'
	}).when('/priorities/add', {
		templateUrl : 'priority.html',
		controller : 'PriorityController'
	}).when('/error', {
		templateUrl : 'error.html'
	}).otherwise({
		redirectTo : '/error'
	});
} ]);
