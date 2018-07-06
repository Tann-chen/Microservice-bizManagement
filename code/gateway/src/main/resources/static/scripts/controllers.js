'use strict';
var ROOT = "http://localhost:8080";
angular.module('Controllers', []).
	controller('navCtrl',['$scope',function($scope){
		$scope.navs = [
			{ link:'#/dashboard', text:'Dashboard', icon:'ti-stats-up'},
			{ link:'#/user', text:'User', icon:'ti-user'},
            { link:'#/permissions', text:'Users Permissions', icon:'ti-panel'},
            { link:'#/access', text:'Role', icon:'ti-stamp'}         
		];
	}])
	.controller('dashCtrl',['$scope','$rootScope',function($scope,$rootScope){
		$rootScope.index = 0;

	}])
	.controller('userCtrl',['$scope','$rootScope','$http',function($scope,$rootScope,$http){
		 $rootScope.index = 1;
         $scope.editForm = false;
         $scope.employees =[];
		 $http({
             url:ROOT+"/users/user",
            method:'get',
        }).success(function(info){
            $scope.employees = info.data.content;
            $scope.total = info.data.totalElements;
            $scope.tempPage = $scope.employees.slice(0,10);
            $scope.currentPage =1;





            $scope.change = function(){
            	if($scope.currentPage!=1){
            		$scope.tempPage = $scope.employees.slice(($scope.currentPage-1)*10);
            	}
            }
        });

        // 增加
        // name: not null email not null password not null
        $scope.addUser = function(){
            for(var i=0;i<$scope.employees.length;i++){
                if($scope.addUserName==$scope.employees[i].name){
                    $scope.errMsg = '用户名已存在';
                }
            }
            if($scope.addUserName==undefined||$scope.addUserName==""||$scope.addUserPwd==""||$scope.addUserPwd_1==""||$scope.addUserPwd==undefined||$scope.addUserPwd_1==undefined){
                if($scope.addUserName==undefined){
                    $scope.errMsg = "用户名不能为空";  
                }
                if($scope.addUserPwd==undefined||$scope.addUserPwd_1==undefined){
                    $scope.errMsg="用户密码不能为空";
                }if($scope.addUserEmail==""||$scope.addUserEmail==undefined){
                    $scope.errMsg = "用户eamil不能为空";
                }
            }
            if($scope.addUserPwd==$scope.addUserPwd_1){
                $http({
                    url:ROOT+'/users/user',
                    method:'post',
                    headers: { 'Content-Type': 'application/json' },
                    data:{
                        "name": $scope.addUserName,
                        "email": $scope.addUserEmail,
                        "password" : $scope.addUserPwd,
                        "phone": $scope.addUserPhone,
                        "avatar": $scope.addUserAvatar
                    }
                }).success(function(info){
                    $scope.employees = info.data.content;
                }); 
            }else{
                $scope.errMsg="两次输入密码不一致";
            }

        }

        // 修改
        $scope.editUser = function(id,key){
            $scope.editForm = true;
            $scope.editedUserId = id;
            $scope.editedKey = key;
        }

        $scope.updateUser = function(){
                var id = $scope.editedUserId;
                $http({
                url:ROOT+'/users/user/'+id,
                method:'put',
                data:{
                    'id':id,
                    'name': $scope.userName,
                    'email': $scope.userEmail,
                    'password' : $scope.userPwd,
                    'phone': $scope.userPhone,
                    'avatar': null,
                },
                headers: { 'Content-Type': 'application/json' },
            }).success(function(info){
                $scope.employees[$scope.editedKey].name = info.data.name;
                $scope.employees[$scope.editedKey].email = info.data.email;
                $scope.employees[$scope.editedKey].phone = info.data.phone;
                $scope.employees[$scope.editedKey].avatar = info.data.avatar;
                $scope.employees[$scope.editedKey].jobStatus = info.data.jobStatus;
                $scope.editForm = false;
            }).error(function(err){
                console.log(err.code);
            });  
        }


        // 删除用户
        $scope.deleteUser = function(id){

            $http({
                url:ROOT+'/users/user/'+id,
                method:'delete',
            }).success(function(info){
                $scope.employees = info.data.content;
            }).error(function(err){
                alert("删除失败");
            });
        }
	}])
    .controller('permissionCtrl',['$scope','$rootScope','$http',function($scope,$rootScope,$http){
        $rootScope.index=2;
        $scope.employees = [];
        $scope.currentId = 0;

        // 获取user list
        $http({
            url:'test/test2.json',
            // url:ROOT+'/users/user',
            method:'get',
        }).success(function(info){
            $scope.employees = info.data.content;
            $scope.total = info.data.totalElements;
            $scope.tempPage = $scope.employees.slice(0,10);
            $scope.currentPage =1;
            $scope.change = function(){
                if($scope.currentPage!=1){
                    $scope.tempPage = $scope.employees.slice(($scope.currentPage-1)*10);
                }
            }

            // 默认显示第一个user的permissions
            var id = $scope.employees[0].id;
            $http({
                url:ROOT+'/users/user/'+id+'/permissions',
                method:'get',
            }).success(function(info){
                // default User
                $scope.defUserPermis = info.data;
                console.log($scope.defUserPermis);
            }).error(function(err){
                console.log(err);
            });


        }).error(function(err){
            console.log(err);
        });


        // 获取指定user的permissions
        $scope.getUserPermi = function(id) {
            setCurrentID(id);
            $http({
                url:ROOT+'/users/user/'+id+'/permissions',
                method:'get',
            }).success(function(info){
                $scope.defUserPermis = info.data;
            }).error(function(err){

            });
        }

        function setCurrentID(id){
            $scope.currentId = id;
        }

        $scope.updatePermission = function(key,defUserPermi){
            var id = $scope.currentId;
                console.log(defUserPermi);
                $http({
                    url:ROOT+'/users/user/'+id+'/module/'+key,
                    method:'put',
                    headers: { 'Content-Type': 'application/json' },
                    data:{
                        permissions:defUserPermi
                    }
                }).success(function(info){

                }).error(function(err){

                });
        }




    }])
    .controller('accessCtrl',['$scope','$rootScope','$compile','$http','$window',function($scope,$rootScope,$compile,$http,$window){
        $rootScope.index=3;
        $scope.roles = [];
        $scope.currentPage =1;
        $scope.editShow=false;
        $scope.currentId = 0;
        // 请求所有role 和所有 permission 
        $http({
            url:ROOT+'/users/role',
            method:'get',
        }).success(function(info){
                $scope.roles = info.data.role_list;
                $scope.mods = info.data.module_list;
                $scope.total = $scope.roles.length;
                $scope.tempPage = $scope.roles.slice(0,10);
                $scope.currentPage =1;


                $scope.change = function(){
                    if($scope.currentPage!=1){
                        $scope.tempPage = $scope.roles.slice(($scope.currentPage-1)*10);
                    }
                }

                // 默认请求第一条数据的permission
                $http({
                    url:ROOT+'/users/role/'+$scope.roles[0].id+'/permissions',
                    method:'get',
                }).success(function(info){
                    $scope.permissions = info.data;
                }).error(function(err){
                    console.log(err);
                });

        }).error(function(err){
                console.log(err);
        });
        




        // 添加role
        $scope.roleName = "";
        $scope.roleDetail="";
        $scope.addRole = function(){
            for(var i=0;i<$scope.roles.length;i++){
                if($scope.roleName==$scope.roles[i].role){
                    $scope.Add_errMsg = "已存在名为"+$scope.roleName+"的Role,请修改后再提交!";
                }
            }
            $http({
                url:ROOT+'/users/role',
                method:'post',
                data:{
                    'role':$scope.roleName,
                    'description':$scope.roleDetail,
                },
                headers: { 'Content-Type': 'application/json' },
            }).success(function(info){
                $scope.roles = info.data;
            }).error(function(err){
                console.log(err);
            });
        }


        //  编辑role
        $scope.editRole = function(id){
            $scope.editShow =true;
            $scope.comfirmEditId = id;
        }

        $scope.comfirmEdit = function(){
            for(var i=0;i<$scope.roles.length;i++){
                if($scope.needEditRole==$scope.roles[i].role){
                    $scope.Edit_errMsg = "已存在名为"+$scope.roleName+"的Role,请修改后再提交!";
                }
            }

            $http({
                url:ROOT+'/users/role/'+$scope.comfirmEditId,
                method:'put',
                headers: { 'Content-Type': 'application/json' },
                data:{
                    'id':$scope.comfirmEditId,
                    'role':$scope.needEditRole,
                    'description':$scope.needEditDetail,
                }
            }).success(function(info){
                $window.location.reload();
            }).error(function(err){
                console.log(err);
            });
        }


        // 删除一个role
        $scope.deleteRole = function(id){
            $http({
                url:ROOT+'/users/role/'+id,
                method:'DELETE',
            }).success(function(info){
                $scope.roles = info.data.role_list;
                $scope.currentPage=1;
            }).error(function(err){
                console.log(err);
            });
        }


        // 查找一个role 的permission
        $scope.getPermissions = function(id){

            setCurrentId(id);

            $http({
                url:ROOT+'users/role/'+id+'/permissions',
                method:'get',
            }).success(function(info){
                $scope.permissions = info.data;
            }).error(function(err){
                console.log(err);
            });
        }

        function setCurrentId(id){
            $scope.currentId = id;
        }

        // update permission 
        $scope.updatePermission = function(key,permission){
            var id = $scope.currentId;
            console.log(permission);
                $http({
                    url:ROOT+'/users/user/'+id+'/module/'+key,
                    method:'put',
                    headers: { 'Content-Type': 'application/json' },
                    data:{
                        permissions:permission
                    }
                }).success(function(info){

                }).error(function(err){

                });
        }

    }])
    .controller('loginCtrl',['$scope','$http','$cookies','jwtHelper','$timeout','$window','$rootScope','$httpParamSerializer',function($scope,$http,$cookies,jwtHelper,$timeout,$window,$rootScope, $httpParamSerializer){

    //    清除缓存
    // $cookies.remove("access_token");

    if($cookies.get("access_token")){
        $http.defaults.headers.common.Authorization = 'Bearer ' + $cookies.get("access_token");
        getOrganization();
        $rootScope.isLoggedIn = true;
    }else{
        // console.log("no access token");
        $rootScope.isLoggedIn = false;
    }
    $scope.login = function(){
         $scope.loginData = {
            grant_type: 'password',
            username: $scope.username,
            password: $scope.password,
            scope   : 'angular_app'        
        };
        obtainAccessToken($scope.loginData);
    }
    // $scope.refreshAccessToken = function(){
    //     obtainAccessToken($scope.refreshData);
    // }

    $scope.logout = function(){
        logout($scope.loginData);
    }

    if($cookies.get("remenber")=="yes"){
        var validity = $cookies.get("validity");
        if(validity>10)validity -= 10;
        $setTimeout(function() {$scope.refreshAccessToken();}, validity*1000);
    }

    function obtainAccessToken(params){
        if (params.username != null){
            if (params.remember != null){
                $cookies.put("remember","yes");
            }
            else {
                $cookies.remove("remember");
            }
        }

        $http({
            url:ROOT+"/auth/oauth/token",
            method:'post',
            headers:{
                'Accept':'application/json',
                'content-type':'application/x-www-form-urlencoded',
                'Authorization':'Basic YW5ndWxhcl9hcHA6RVJQIUAjYW5ndWxhcg=='
            },
            data:$httpParamSerializer(params)
        }).success(function(data){
                $http.defaults.headers.common.Authorization= 'Bearer ' + data.access_token;
                var expireDate = new Date (new Date().getTime() + (1000 * data.expires_in));
                $cookies.put("access_token", data.access_token,{'expires': expireDate});
                $cookies.put("validity", data.expires_in);
                $cookies.put("scope",data.scope);
                $cookies.put("refresh_token",data.refresh_token);
                $rootScope.isLoggedIn=true;
                getOrganization();
                $('#signIn').modal('hide');
                $window.location.reload();
        }).error(function(err){
             console.log(err);
        });
    }
    function getOrganization(){
        var token = $cookies.get("access_token");
        //JWT
         var payload = jwtHelper.decodeToken(token);
         $scope.user_name = payload.user_name;
        $scope.organization = payload.organization;
    }
    
    function logout(params) {
                $cookies.remove("access_token");
                $cookies.remove("validity");
                $cookies.remove("remember");
                $window.location.reload();
    }
}]);



  