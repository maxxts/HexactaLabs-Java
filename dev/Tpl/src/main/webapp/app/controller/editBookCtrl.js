booksApp.controller('editBookCtrl', function ($scope,$location,$rootScope,$routeParams, $http) {
	$scope.books = $rootScope.books;
	
	
	$scope.backToHome = function(){
    	$location.path("/");
    };
    
    $scope.save = function(aBook) {
    	$scope.newBook = angular.copy(aBook);
    	$rootScope.books[$rootScope.books.length] = aBook;
    };

    $scope.reset = function() {
    	$scope.newBook = {};
    };

    $scope.reset();
    
    $scope.bookId = $routeParams.bookId;
    $scope.currentBook = null;
    
    
    $http({
		method : 'GET',
		url: '/Tpl/rest/books/'+$scope.bookId,
		headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
	}).success(function(data, status, headers, config){
				
		if(status = 200)
		{
			$scope.currentBook = data;
			console.log("Book's Country: " + $scope.currentBook.country);
		}

	}).error(function(data, status, headers, config){
		console.log("An Error occurred while trying to get book:" + $scope.bookId);
	});
});