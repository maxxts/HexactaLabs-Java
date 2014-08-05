booksApp.controller('logInCtrl', function ($scope,$location,$rootScope) {
//	$scope.books = $rootScope.books;
	
	$scope.backToHome = function(){
    	$location.path("/");
    };
    
});