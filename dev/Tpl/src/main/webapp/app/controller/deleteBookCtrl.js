booksApp.controller('deleteBookCtrl', function ($scope,$location,$rootScope) {
	$scope.books = $rootScope.books;
	
	$scope.backToHome = function(){
    	$location.path("/");
    };
    
    $scope.borrar = function(bookId){
    	$rootScope.books.splice(bookId, 1);
    	console.log("Intenta borrar");
    	$location.path("/");
    };
});