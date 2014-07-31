booksApp.controller('bookListCtrl', function ($scope,$location,$rootScope,$http) {
	
	$scope.linkToCreateBook=function(){
		$location.path("/createBook");
	};
	$scope.linkToEditBook=function(bookId){
		$location.path("/editBook/"+bookId);
	};
	$scope.linkToDeleteBook=function(bookId){
		$location.path("/deleteBook/"+bookId);
    /*	$rootScope.books.splice(bookId, 1);
    	console.log("Intenta borrar");
    	$location.path("/");
   */ };
    
	$scope.linkToLendBook=function(bookId){
		$location.path("/lendBook/"+bookId);
	};
	
	$scope.modifyModal=function(book){
		$scope.selectedBook = book;
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

booksApp.controller('commentController', function(){
	this.comment = {};
	this.addComment = function(book){
		alert("HOLA");
		//TODO: cambiar esto cuando ya esten implementados los comentarios en el backend
	
		if (!book.comments)
			book.comments = [];
		
		book.comments.push(this.comment);
		this.comment = {};
	};
});