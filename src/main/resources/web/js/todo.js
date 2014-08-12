var todoApp = angular.module('todoApp', [ 'ngRoute', 'ui.bootstrap', 'todoControllers', 'restangular' ]);

todoApp.config([ '$routeProvider', 'RestangularProvider', function($routeProvider, RestangularProvider) {
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

todoApp.factory('api', ['restangular', function(restangular){
    return {
        priorities:{
            search: function(){
                 Restangular.all('Todo/priorities').getList();
            }, save: function(entity){
                 Restangular.all('Todo/priorities').post(entity);
            }, update: function(entity){
                 entity.put().$object;
            }, read: function(id){
                 Restangular.one("Todo/priorities", id).get();
            }, remove: function(id){
                 Restangular.one('Todo/priorities', id).delete();
            }
        }, tasks:{
            search: function(){
                Restangular.all('Todo/tasks').getList();
            }, save: function(entity){
                Restangular.all('Todo/tasks').post(entity);
            }, update: function(entity){
                entity.put().$object;
            }, read: function(id){
                Restangular.one("Todo/tasks", id).get();
            }, remove: function(id){
                Restangular.one('Todo/tasks', id).delete();
            }
        }
    }
}]);

todoApp.service('todoService',[ 'api', function(api){
   var priorities = undefined;
   var tasks = undefined;

   this.fetchPriorities = function(){
       if(!priorities){
            api.priorities.search();
       }
   }
}]);