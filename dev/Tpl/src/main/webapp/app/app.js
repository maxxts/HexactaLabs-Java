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
			
		function test(name, email) {
			
			var nameRegex = /^[A-Z][A-Z0-9]{2,15}$/i;
			var emailRegex = /^[A-Z]$/;
			
			console.log(emailRegex.test(email));
			
			if(name.length < 3 || name.length > 16 || email.length == 0) {
				return(false);
			}
			else {
				return(true);
			}
		}

		  $scope.validateAndSubmit = function() {
			  
		  var name = $scope.user.name;
		  var email = $scope.user.email;
		  
		  var isValid = test(name, email);
		  
		  //Validacion de datos ingresados, despúes mandar al WS
		  if( isValid == true ) {
			  $location.path("/ok");
		  }
		  
		  //Dar mensajes de error
		  else {
	    	  //console.log(isValid);  
		  }
			  
		  }
	    }]
	}
});

booksApp.run(function($rootScope,$http) {
	$rootScope.books = [];
});