booksApp.controller('bookListCtrl', function ($scope,$location,$rootScope,$http) {
	$scope.linkToCreateBook=function(){
		$location.path("/createBook");
	};
	$scope.linkToEditBook=function(bookId){
		$location.path("/editBook/"+bookId);
	};
	$scope.linkToDeleteBook=function(bookId){
		$location.path("/deleteBook/"+bookId);
	/*	var jsonBook = angular.toJson(bookId);
    	$http.delete('/Tpl/rest/books', jsonBook).success(function(data, status, headers, config){
    		if(status = 200)
    		{
    	    	//Ok message and go back
    	    	alert('ok');
    	    	$location.path("/");
    		}
    	}).error(function(data, status, headers, config){
    		console.log("An Error occurred while trying to delete a book");
    	}); --------------------------> ESTO FUE UNA PRUEBA A VER SI ANDABA ASI EL DELETE, PERO SE VE QUE NO
    */
    };
    
	$scope.linkToLendBook=function(bookId){
		$location.path("/lendBook/"+bookId);
	};
	
	$http({
		method : 'GET',
		url: '/Tpl/rest/books',
		headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
	}).success(function(data, status, headers, config){

		if(status = 200)
		{
			$rootScope.books = [];
			$rootScope.books = data;
			$scope.books = $rootScope.books;
		}

	}).error(function(data, status, headers, config){
		console.log("An Error occurred while trying to get all books");
	});
	
	
});