booksApp.controller('editBookCtrl', function ($scope,$location,$rootScope,$routeParams) {
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
    
    $scope.bookId = $routeParams;
    $scope.currentBook = null;
    
    $http({
		method : 'GET',
		url: '/Tpl/rest/bookWS/getBook/'+$scope.bookId,
		headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
	}).success(function(data, status, headers, config){

		if(data.success)
		{
			$scope.currentBook = data;
		}

	}).error(function(data, status, headers, config){
		console.log("An Error occurred while trying to get all books");
	});
});