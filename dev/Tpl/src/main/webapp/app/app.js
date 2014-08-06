var booksApp = angular.module('booksApp', ['ngRoute']);

booksApp.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
	$routeProvider.
		when('/', {	
			templateUrl: 'app/views/bookListPage.html',
			controller: 'bookListCtrl' 
		}).
	   	when('/createBook', { 
	   		templateUrl: 'app/views/createBookPage.html', 
	   		controller: 'createBookCtrl'
	   	}).
		when('/editBook/:bookId', {
			templateUrl: 'app/views/editBookPage.html',
			controller: 'editBookCtrl'
		}).
        when('/lendBook/:bookid', {
            templateUrl: 'app/views/lendBookPage.html',
            controller: 'lendBookCtrl'
        }).
        when('/deleteBook/:bookid', {
            templateUrl: 'app/views/deleteBookPage.html',
            controller: 'deleteBookCtrl'
        }).
        when('/register', {
            templateUrl: 'app/views/registerPage.html',
            controller: 'registerCtrl'
        }).
		otherwise({
			redirectTo: '/'
		});
}]);

booksApp.directive('userLogin',function() {
	return {
		restrict: 'E',
		templateUrl: 'app/views/loginView.html',
		controller: ['$scope', '$http', '$location', function($scope, $http, $location) {
			
		function test(nombre) {
			alert(nombre);
		}

		  $scope.validateAndSubmit = function() {
			  
		  var name = $scope.user.name;
		  var email = $scope.user.email;
		  
		  test(name);
		  
		  //Validacion de datos ingresados, despúes mandar al WS
		  if( name == "asd") {
			  $location.path("/ok");
		  }
		  
		  //Dar mensajes de error
		  else {
	    	  console.log($scope.user.name);  
		  }
			  
		  }
	    }]
	}
});

booksApp.run(function($rootScope,$http) {
	$rootScope.books = [];
});