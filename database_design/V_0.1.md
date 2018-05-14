------------------------------------ system task config ----------------------------------
1. chooce 1 : relational data stucture below to keep the task work flow graph
   chooce 2 : use seriliable file to keep the graph the task work flow graph
   // 1 : many db access, but more availble to change, quick access for every step
   // 2. better to store the SESSION(achieved by Redis in mricroservice), but also need to retrive graph to get current step
   //suggestion : chooce 1

2. to deal with AND/OR, "group" concept is produced, "AND" relation inside the group, "OR" relation between groups
-------------------------------------------------------------------------------------------

# task_prototye
	* id : int(9) | primary key
	* name : varchar(90)
	* description : text  
	* root_node : int(9) | referenced from task_prototype_node.id (O2O) 
	* is_active : varchar(1)


# task_prototype_node
	* id : int(9) | priamry key
	* name : varchar(90) 
	* description : text


# task_prototype_referer_group
	* id : int(9) | primary key
	* attached_node : int(9) | referenced from task_prototype_node.id (M2O)


# task_prototype_node_referer
	* id : int(9) | primary key
	* belong_group : int(9) | referenced from task_prototype_referer_group.id (M2O)
	* referer_position_id : int(9) | referenced from hr_position_id	(M2O)
	* request_action : varchar(45) | enum 


# task_prototype_edge
	* id  : int(9) 	| primary key
	* node_from  : int(9) | index | referenced from task_prototype_node.id (M2M) 
	* node_to	 : int(9) | index |	referenced from task_prototype_node.id (M2M)
	* has_criteria : varchar(1)  


# task_prototype_criteria_group
	* id : int(9) | parimary key
	* attached_edge : int(9) | index |  referenced from task_prototype_edge.id (M2O)


# task_prototype_edge_criteria
	* id : int(9) | primary key
	* belong_group : int(9) | index |  referenced from task_prototype_criteria_group.id (M2O) 
	* criteria_attribute : varchar(45) | enum 
	* comparison_operator : varchar(5) | enum
	* critteria_value  : string(45) 




----------------------------------- user microservice --------------------------------
1. manager the access control
	// use "Resource-based access control" idea to achieve the access control
	// user attaches to roles, permissions attached to the role -> get user's permission by retrieve the permissions
	// to speed up, after account login -> save all permissions in SESSION(Redis)
	// the idea is referenced from Shiro (a famous access control framework)
--------------------------------------------------------------------------------------

# user_info
	* id : int(9) | primary key
	* name : varchar(60) 
	* email : varchar(60) | unique | used as login account
	* phone : varchar(30)
	* avatar : blob | binary
	* password : varchar(255) | save code encrypted by MD5 
	* pwd_salt : varchar(30) |  random string, as salt for MD5
	* created_time : datatime
	* last_login_time : datetime
	* is_active : varchat(1)


# user_role
	* id : int(9) | primary key
	* name : varchar(90) | unique
	* description : text
	* is_active : varchar(1)


# user_permission
	* id : int(9) | primary key 
	* name : varchar(90) 
	* resource_type : varchar(30) | enum 
	* resource_url : varchar(60)
	* permission_expression : varchar(150)


# user_relation_info_role
	* user_id : int(9) | primary key
	* user_role_id : int(9) | primary key


# user_relation_role_permission
	* user_role_id : int(9) | primary key
	* user_permissio_id : int(9) | primary key


// add user directly to permission

----------------------------------- HR microservice --------------------------------

# hr_department
	* id : int(9) | primary key
	* name : varchar(90) | unique
	* address : varchar(255)
	* phone : varchar(30)


# hr_enum_position
	* id : int(9) | primary key
	* name : varchar(90) | unique
	* specific_department : int(9) | referenced from hr_department.id 


# hr_user_position (hr_relation_user_department_position)
    •id
	* user_id : int(9) | primary key | referenced from user_info.id
	* department_id : int(9) | primary key | referenced from hr_department.id
	* position_id : int(9) | primary key | referenced from hr_department.id

# hr_employee_document
	* id : int(11) | primary key
	* name : varchar(60)
	* user_id : int(9) | referenced from user_info.id (M2O)
	* document_type : varchar(30) | enum
	* hard_copy_only : varchar(1)
	* created_time : datetime

# hr_document_consult_history
	* id : int(13) | primary key
	* consult_type : varchar(30) | enum (creation/modification/read_only)
	* requestor_id : int(9) | referenced from user_info.id
	* approver_id : int(9) | referenced from user_info.id
	* comment : text
	* consult_time : datatime

....
 salary , timesheet , part_time , full_time, salary_calculation_type .. 

------------------------------------ inventory microservice ----------------------------------------

# inventory_commidity
	* id : int(9) | primary key
	* name : varchar(60)
	* sku_number : varchar(60) | unique | index
	* commidity_type : varchar(30) | enum | raw/production
	* quantity_unit : varchar(30) | enum | not null
	* purchase_period : int(6) | unit:day | not null
	* processing_period : int(6)  | unit:day | not null | in processing com, need processing table to calculate avg


# inventory_supplier (vendor)
	* id : int(9) | primary key
	* supplier_name : varchar(60) | unique | not null
	* address : varchar(150)
	* description : text
	* business license : blob


# inventory_supplier_contact
	* id : int(9) | primary key
	* belong_supplier : int(9) | referenced from inventory_supplier.id (M2O)
	* name : varchar(30) 
	* phone_number : varchar(30) 
	* email_address : varchar(30)


# inventory_relation_commidity_supplier
	* commidity_id : int(9) | primary key
	* supplier_id : int(9) | primary key


# inventory_entry 
	* id : int(11) | primary key
	* batch_number : varchar(30) | unique | index
	* commidity_id : int(9) | referenced from inventory_commidity.id
	* supplier_id : int(9) | referenced from inventory_supplier.id
	* quantity : int(9) | not null
	* entry_time : datatime
	* receive_user :  int(9) | referenced from user_info.id
	* account_transation : int(17) | referenced from account_transaction.id


# inventory_commidity_details
	* id : int(15) | primary key
	* commidity_id : int(9) | referenced from inventory_commidity.id
	* serial_number : varchar(30) | unique | index
	* entry_id : int(11) | referenced from inventory_entry.id
	* commidity_status : varchar(30) | enum | availble/picked/broken


# inventory_picking
	* id : int(15) | primary key
	* commidity_details_id : int(9) | referenced from inventory_commidity_details.id
	* picked_time : datatime
	* picked_user : int(9) | referenced from user_info.id
	* approved_user : int(9) | referenced from user_info.id
	* picking_purpose : varchar(30) | enum | sell/processing/cleaning
	* account_transation : int(17) | referenced from account_transaction.id



------------------------------------ accountancy microservice ----------------------------------------

# account_enum_title
	* id : int(9) | primary key
	* name : varchar(30) | unique
	* title_level : int(1)


# account_transaction
	* id : int(17) | primary key
	* debit_title : int(9) | referenced from account_enum_title.id
	* credit_tile : int(9) | referenced from account_enum_title.id
	* explanation : varchar(150) 
	* create_time : datatime
	* create_user : int(9) | referenced from user_info.id
	* transation_status : varchar(30) | enum | pending/comformed
	* checked_user : int(9) | referenced from user_info.id


	---------------------------- invoice microservice -------------------------------------------------
	sell process

	wfl
