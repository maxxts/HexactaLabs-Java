booksApp.directive('registerForm', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/registerForm.html',
		link : function(scope, el, attrs) {
			scope.onDoneClick = function() {
				if (scope.newUser.pass1 == scope.newUser.pass2) {
					// Valido
					scope.newUser = {};
					scope.registerForm.$setPristine();
					scope.$destroy();
				} else {
					// Invalido
					scope.newUser.pass1 = "";
					scope.newUser.pass2 = "";
				}

			};
		}
	};
});