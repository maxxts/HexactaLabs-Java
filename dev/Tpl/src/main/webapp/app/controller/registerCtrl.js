booksApp.controller('registerCtrl', function($scope, $location, $rootScope) {

	document.getElementById('user').focus();
	
	//Start of alerts
	//Use for controllers that need alerts
	
	$scope.alerts = [];
	
	$scope.addAlert = function(alertStrongMsg, alertMsg, alertType) {
		if ($scope.alerts.length < 1) {
			$scope.alerts.push({
				strong: alertStrongMsg,
				msg : alertMsg,
				type : alertType
			});
		}
	};

	$scope.closeAlert = function(index) {
		$scope.alerts.splice(index, 1);
	};
	//End of alerts
	
	$scope.backToHome = function() {
		if ($scope.pass == $scope.pass2) {
			$location.path("/");
		} else {
			$scope.addAlert("Cuidado!","Las password no coinciden.", "danger");
			$scope.pass = "";
			$scope.pass2 = "";
			document.getElementById('pass').focus();
		}
	};
});