booksApp.controller('registerCtrl', function ($scope,$location,$rootScope) {

	$scope.backToHome = function(){
		if($scope.pass == $scope.pass2){
			$location.path("/");
		} else {
		alert("Las contraseñas no coinciden!");
		$scope.pass="";
		$scope.pass2="";
		document.getElementById('pass').focus();
		}
    };
    
});