var login = angular.module('login',[]);
login.controller('loginCtrl',['$scope',function($scope){
    $scope.login = function(){
    	$http({
    		url:'auth/oauth/token',
    		method:'post',
    		headers:{
    			'Accept':'application/json',
    			'content-type':'application/x-www-formurlencoded',
    			
    		},
    	})
    }
}])
