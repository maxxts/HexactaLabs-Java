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
		otherwise({
			redirectTo: '/'
		});
}]);

booksApp.directive('userLogin',function() {
	return {
		restrict: 'E',
		templateUrl: 'app/views/loginView.html',
		controller: ['$scope', '$http', '$location', function($scope, $http, $location) {
			
		function test(name, pass) {
			
			pass = (typeof pass === 'undefined') ? ' ' : pass;
			
			var nameRegex = /^[A-Z][A-Z0-9]{2,15}$/i; //nombre de usuario empieza con una letra de 3 a 16 caracteres y puede tener numeros
			var passRegex = /^[A-Z0-9][A-Z0-9]{5,11}$/i; //password de 6 a 12 caracteres y puede tener numeros.

			if( (nameRegex.test(name) == true) && (passRegex.test(pass) == true) ) {
				console.log(pass);
				return(true);
			}
			else {
				return(false);
			}
		}

		  $scope.validateAndSubmit = function() {
			  
			  var name = $scope.user.name;
			  var pass = $scope.user.pass;
			  
			  var isValid = test(name, pass);
			  
			  //Validacion de datos ingresados, despúes mandar al WS
			  if( isValid == true ) {
				  $location.path("/ok");
			  }
			  
			  //Dar mensajes de error
			  else {
		    	  console.log(isValid);  
			  }
				  
		  }
	    }]
	}
});

booksApp.run(function($rootScope,$http) {
	$rootScope.books = [];
});