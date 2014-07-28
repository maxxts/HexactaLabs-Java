booksApp.controller('lendBookCtrl', function ($scope,$location,$rootScope) {
	$scope.books = $rootScope.books;
	
	$scope.backToHome = function(){
    	$location.path("/");
    };
    
    $scope.reset = function(){
    	$location.path("/");
    };
    
});