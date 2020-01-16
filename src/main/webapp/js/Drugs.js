/**
 * 
 */
var MaxMed = angular.module('MaxMed', ['ngRoute']);
MaxMed.config(function($routeProvider, $locationProvider){
	$routeProvider
	.when('/', {
		templateUrl: 'Welcome.html'
	})
	.when('/Welcome', {
		templateUrl: 'Welcome.html'
	})
	.when('/search', {
		templateUrl: 'search.html',
		controller: 'DrugController'
	})
	.when('/interactions',{
		templateUrl: 'interactions.html',
		controller: 'InteractionController'
	})
	.when('/resume', {
		templateUrl: 'resume.html',
		controller: 'ResumeController'
	})
	.when('/notify', {
		templateUrl: 'notify.html'
	})
	.otherwise({
		templateUrl: 'search.html'
	});
	
});




MaxMed.controller('DrugController', function($scope, $http){
	$scope.appName = 'MaxMed, by Brady Bostic';
	$scope.showSearch = true;
	

	
	
	$scope.searchDrugs = function(){
		
		$http.get($scope.radioURL + $scope.search)
		.then(function(response){
			$scope.drugs = response.data;
		});
	}
	
	$scope.openModal = function (d){
		$scope.drug = d;
	}
	
	$scope.selectDrug = function(d){
		$scope.drug = d;
	}
	
	
	
});


MaxMed.controller('InteractionController', function($scope, $http){
	$scope.showSearch = true;
	
	


	
	$scope.searchDrugs = function(){
		$http.get('./rest/v1/Interaction?drug=' + $scope.search)
		.then(function(response){
			$scope.drugs = response.data;
		});
	}
	
	$scope.compareDrugs = function(){
		var array;
		if($scope.search.includes(" and ")){
			array = $scope.search.split(" and ",2);
		}else if($scope.search.includes(" & ")){
			array = $scope.search.split(" & ",2);
		}
		$http.get('./rest/v1/Compare?drug1=' + array.slice(0,1) + '&drug2=' + array.slice(1))
		.then(function(response){
			$scope.drugs = response.data;
		});
		
	}
	
	
	
});

MaxMed.controller('ResumeController', function($scope, $http){
	
	

	
	$scope.sendEmail = function(){
		$http.post('./rest/v1/email?address=' + $scope.recipient)
		.then(function(response){
			console.log('email sent!');
		})
	}
	
});

