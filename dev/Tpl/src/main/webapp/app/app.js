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
        when('/logIn/', {
            templateUrl: 'app/views/logInPage.html',
            controller: 'logInCtrl'
        }).
		otherwise({
			redirectTo: '/'
		});
}]);

booksApp.run(function($rootScope,$http) {
	$rootScope.books = [];
});