booksApp.controller('createBookCtrl', function($scope,$location,$rootScope,$http) {
    $scope.newBook = {};

    $scope.save = function(aBook) {
    	var jsonBook = angular.toJson(aBook);
    	$http.post('/Tpl/rest/createBook', jsonBook).success(function(data, status, headers, config){
    		if(status = 200)
    		{
    	    	//Ok message and go back
    	    	alert('ok');
    	    	$location.path("/");
    		}
    	}).error(function(data, status, headers, config){
    		console.log("An Error occurred while trying to store a book");
    	});
    };

    $scope.reset = function() {
      $scope.newBook = {};
    };

    $scope.reset();
    
    $scope.backToHome = function(){
    	$location.path("/");
    };
    
  });