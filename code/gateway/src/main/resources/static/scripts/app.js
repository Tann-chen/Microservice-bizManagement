var Admin = angular.module('Admin', ['ngRoute', 'Controllers','ui.bootstrap','ngResource','ngCookies','angular-jwt']);


Admin.config(['$routeProvider',function($routeProvider){
    $routeProvider
        .when('/dashboard',{
            templateUrl:'./assets/views/dashboard.html',
            controller: 'dashCtrl'
        })
        .when('/user',{
            templateUrl:'./assets/views/user.html',
            controller:'userCtrl'
        })
        .when('/permissions',{
            templateUrl:'./assets/views/permissions.html',
            controller:'permissionCtrl'
        })
        .when('/access',{
            templateUrl:'./assets/views/access.html',
            controller:'accessCtrl'
        })
        .otherwise({
            redirectTo: '/dashboard',
        });

}]);


Admin.run(['$rootScope','$http',function ($rootScope,$http) {

    //侧边栏索引
    $rootScope.index =0;
    $rootScope.isLoggedIn = false;

    $http({
        url:ROOT+"/users/user/current",
        // url:'test/current.json',
        method:'get',
    }).success(function(info){
        $rootScope.currentName = info.data.name;
        $rootScope.currentEmail = info.data.email;
        $rootScope.currentPhone = info.data.phone;
        $rootScope.currentStatus = info.data.jobStatus;
        $rootScope.currentAvatar = info.data.avatar;
        if($rootScope.currentStatus==null){
            $rootScope.currentStatus = "";
        }
    }).error(function(err){
        console.log(err);
    });
}]);


