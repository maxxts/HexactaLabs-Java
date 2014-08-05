booksApp.controller('bookListCtrl', function ($scope,$location,$rootScope,$http) {
	
	$scope.linkToCreateBook=function(){
		$location.path("/createBook");
	};
	$scope.linkToEditBook=function(bookId){
		$location.path("/editBook/"+bookId);
	};
	$scope.linkToDeleteBook=function(bookId){
		$location.path("/deleteBook/"+bookId);
    };
 
	$scope.linkToLendBook=function(bookId){
		$location.path("/lendBook/"+bookId);
	};
	
	$scope.modifyModal=function(book){
		$scope.selectedBook = book;
	};
	
	$scope.loadBooks = function(){
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
	}
	$scope.loadBooks();
	
	$scope.comment = {};
	$scope.addComment = function(book){
		//TODO: cambiar esto cuando ya esten implementados los comentarios en el backend
		$scope.comment.book = book.id;
		if (!book.comments)
			book.comments = [];
		
		var jsonComment = angular.toJson($scope.comment);
		console.log(jsonComment);
		$http.post('/Tpl/rest/comments', jsonComment).success(function(data, status, headers, config){
    		if(status = 200){
    			console.log("Comment Creation Completed.");
    		}
    	}).error(function(data, status, headers, config){
    		console.log("An Error occurred while trying to store a comment");
    	}) ;
		
		book.comments.push($scope.comment);
		$scope.loadBooks();
		
		$scope.comment = {};
	};
	
	$scope.limpiarComentarios = function(){
		$scope.comment = {};
	};
	
});

booksApp.controller('commentController', function($scope,$rootScope,$http){

	
});
