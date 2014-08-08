var booksApp = angular.module('booksApp', ['ngRoute','ui.bootstrap']);

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

booksApp.directive('userIngreso',function() {
	return {
		restrict: 'E',
		templateUrl: 'app/views/loginView.html',
		controller: ['$scope', '$http', '$location', function($scope, $http, $location) {
		
			$scope.user = {};	
	
			$scope.validateUser = function(aUser) {
				  			  
				var isValid = test(aUser.name, aUser.pass);
					  
				if( isValid == true ) {
					var jsonUser = angular.toJson(aUser);
					$http.post('/Tpl/rest/login', jsonUser).success(
							function(data, status, headers, config) {
								if (status = 200) {
									// Ok message and go back
									console.log("Creation Completed.\nUser name: "
											+ $scope.user.name);
									$location.path("/");
								}
							}).error(function(data, status, headers, config) {
						console.log("An Error occurred while trying to store the user:" + jsonUser);
					});
				}
				else {
					console.log("Usuario o contraseña invalida");  
				}
						  
			}
				
			// TODO: Use angular validation
			function test(name, pass) {
				
				pass = (typeof pass === 'undefined') ? ' ' : pass;
				
				var nameRegex = /^[A-Z][A-Z0-9]{2,15}$/i; //nombre de usuario empieza con una letra de 3 a 16 caracteres y puede tener numeros
				var passRegex = /^[A-Z0-9][A-Z0-9]{5,11}$/i; //password de 6 a 12 caracteres y puede tener numeros.

				if( (nameRegex.test(name) == true) && (passRegex.test(pass) == true) ) {
					return(true);
				}
				else {
					$scope.user = {};
					return(false);
				}
			}

		}]
	}
});

booksApp.run(function($rootScope,$http) {
	$rootScope.books = [];
});

