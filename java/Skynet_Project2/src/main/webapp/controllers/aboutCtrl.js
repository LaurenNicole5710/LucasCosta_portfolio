var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */

myApp.controller('AboutController', function($scope) {
	
	$scope.message = "Now I'm in the about page, from script.js";
});

